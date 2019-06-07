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
		bullstock.setId(bullstock.getOrganizationName() + bullstock.getTickerSymbol());
		stockRepository.save(bullstock);

	}

	@Transactional
	public Optional<DomainStock> getByTickerSymbol(String organization, String tickersymbol) {
		return stockRepository.findById(organization + tickersymbol);
	}

	@Transactional
	public void deleteStock(String organization, String tickersymbol) {
		stockRepository.deleteById(organization + tickersymbol);

	}

	@Transactional
	public boolean overwriteStock(String organization, String tickersymbol, DomainStock bullstock) {
		Optional<DomainStock> ods = stockRepository.findById(organization + tickersymbol);
		if (ods.isPresent()) {
			bullstock.setId(bullstock.getOrganizationName() + bullstock.getTickerSymbol());
			stockRepository.save(bullstock);
			return true;
		}
		return false;
	}

	@Transactional
	public List<DomainStock> getAllStocks() {
		return this.stockRepository.findAll();

	}

}
