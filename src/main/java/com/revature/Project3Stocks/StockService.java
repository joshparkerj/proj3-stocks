package com.revature.Project3Stocks;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;

	public void setStockRepository(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Transactional
	public void create(DomainStock bullstock) {
		bullstock.setId(bullstock.getOrganizationName(), bullstock.getTickerSymbol());
		stockRepository.save(bullstock);
	}

	@Transactional
	public Optional<DomainStock> getByTickerSymbol(String organization, String tickersymbol) {
		return stockRepository.findById(new StockKey(organization, tickersymbol));
	}

	@Transactional
	public void deleteStock(String organization, String tickersymbol) {
		stockRepository.deleteById(new StockKey(organization, tickersymbol));
	}

	@Transactional
	public void overwriteStock(DomainStock foundStock, DomainStock updatedStock) {
		if (foundStock.equals(updatedStock)) {
			System.out.println("line 38");
			foundStock.overwrite(updatedStock);
			stockRepository.save(foundStock);
			return;
		}
		for (DomainStock ds : getAllStocks()) {
			if (ds.equals(updatedStock)) {
				ds.combine(updatedStock);
				stockRepository.save(ds);
				stockRepository.delete(foundStock);
				return;
			}
		}
	}

	@Transactional
	public List<DomainStock> getAllStocks() {
		return this.stockRepository.findAll();

	}

}
