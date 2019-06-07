package com.revature.Project3Stocks;

import java.util.LinkedList;
import java.util.List;
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
		for (DomainStock ds : stockService.getAllStocks()) {
			if (ds.getId().equals(bullstock.getOrganizationName() + bullstock.getTickerSymbol())) {
				ds.setAmountSpent(bullstock.getAmountSpent() + ds.getAmountSpent());
				ds.setShares(ds.getShares() + bullstock.getShares());
				stockService.overwriteStock(ds.getOrganizationName(), ds.getTickerSymbol(), ds);
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				return;
			}
		}
		stockService.create(bullstock);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	@GetMapping("/{organization}")
	public List<DomainStock> getAll(@PathVariable("organization") String organization) {
		List<DomainStock> filteredList = new LinkedList<DomainStock>();
		for (DomainStock ds : stockService.getAllStocks()) {
			if (ds.getOrganizationName().equals(organization))
				filteredList.add(ds);
		}
		return filteredList;
	}

	@GetMapping("/{organization}/{tickersymbol}")
	public Optional<DomainStock> getByTickerSymbol(@PathVariable("organization") String organization,
			@PathVariable("tickersymbol") String tickersymbol) {
		// System.out.println(organization+tickersymbol);
		return stockService.getByTickerSymbol(organization, tickersymbol);
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
			@PathVariable("tickersymbol") String tickersymbol, @RequestBody DomainStock bullstock,
			HttpServletResponse response) {
		for (DomainStock ds : stockService.getAllStocks()) {
			if (ds.getId().equals(bullstock.getOrganizationName() + bullstock.getTickerSymbol())) {
				ds.setAmountSpent(bullstock.getAmountSpent() + ds.getAmountSpent());
				ds.setShares(ds.getShares() + bullstock.getShares());
				stockService.overwriteStock(ds.getOrganizationName(), ds.getTickerSymbol(), ds);
				stockService.deleteStock(organization, tickersymbol);
				return;
			}
		}
		if (!stockService.overwriteStock(organization, tickersymbol, bullstock))
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
