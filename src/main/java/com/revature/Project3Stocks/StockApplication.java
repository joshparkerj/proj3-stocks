package com.revature.Project3Stocks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/stock")
public class StockApplication extends SpringBootServletInitializer {

	@Autowired
	private StockService stockService;

	@PostMapping
	public void create(@RequestBody DomainStock bullstock, HttpServletResponse response) {
		if (bullstock == null || bullstock.getTickerSymbol() == null || bullstock.getOrganizationName() == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		for (DomainStock ds : stockService.getAllStocks()) {
			if (ds.equals(bullstock)) {
				bullstock.combine(ds);
				stockService.overwriteStock(ds, bullstock);
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				return;
			}
		}
		stockService.create(bullstock);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	@GetMapping("/{organization}")
	public List<Map<String, Object>> getAll(@PathVariable("organization") String organization) {
		List<Map<String, Object>> filteredList = new LinkedList<Map<String, Object>>();
		for (DomainStock ds : stockService.getAllStocks()) {
			if (ds.getOrganizationName().equals(organization)) {
				Map<String, Object> nhm = new HashMap<String, Object>();
				nhm.put("organizationName", ds.getOrganizationName());
				nhm.put("tickerSymbol", ds.getTickerSymbol());
				nhm.put("companyName", ds.getCompanyName());
				nhm.put("amountSpent", ds.getAmountSpent());
				nhm.put("shares", ds.getShares());
				filteredList.add(nhm);
			}
		}
		return filteredList;
	}

	@GetMapping("/{organization}/{tickersymbol}")
	public Map<String, Object> getByTickerSymbol(@PathVariable("organization") String organization,
			@PathVariable("tickersymbol") String tickersymbol) {
		Map<String, Object> mso = new HashMap<String, Object>();
		Optional<DomainStock> ods = stockService.getByTickerSymbol(organization, tickersymbol);
		if (ods.isPresent()) {
			DomainStock ds = ods.get();
			mso.put("organizationName", ds.getOrganizationName());
			mso.put("tickerSymbol", ds.getTickerSymbol());
			mso.put("companyName", ds.getCompanyName());
			mso.put("amountSpent", ds.getAmountSpent());
			mso.put("shares", ds.getShares());
		}
		return mso;
	}

	@DeleteMapping("/{organization}/{tickersymbol}")
	public void delete(@PathVariable("organization") String organization,
			@PathVariable("tickersymbol") String tickersymbol, HttpServletResponse response) {
		try {
			stockService.deleteStock(organization, tickersymbol);
		} catch (EmptyResultDataAccessException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@PutMapping("/{organization}/{tickersymbol}")
	public void overwrite(@PathVariable("organization") String organization,
			@PathVariable("tickersymbol") String tickersymbol, @RequestBody DomainStock updatedRecord,
			HttpServletResponse response) {
		Optional<DomainStock> requestedRecord = stockService.getByTickerSymbol(organization, tickersymbol);
		if (!requestedRecord.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		DomainStock foundRecord = requestedRecord.get();
		stockService.overwriteStock(foundRecord, updatedRecord);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder stockapp) {
		return stockapp.sources(StockApplication.class).properties("spring.config.name: application.stock)");
	}

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application.stock");
		SpringApplication.run(StockApplication.class, args);
	}

}
