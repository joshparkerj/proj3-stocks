package com.revature.Project3Stocks;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class DomainStock {

	@EmbeddedId
	private StockKey id;

	private String companyName;
	private Double amountSpent;
	private Double shares;

	public DomainStock() {
		super();
	}

	public DomainStock(StockKey id, String companyName, Double amountSpent, Double shares) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.amountSpent = amountSpent;
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "DomainStock [id=" + id + ", companyName=" + companyName + ", amountSpent=" + amountSpent + ", shares="
				+ shares + "]";
	}

	public StockKey getId() {
		return id;
	}

	public void setId(StockKey id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(Double amountSpent) {
		this.amountSpent = amountSpent;
	}

	public Double getShares() {
		return shares;
	}

	public void setShares(Double shares) {
		this.shares = shares;
	}

	public String getOrganizationName() {
		return id.getOrganizationName();
	}

	public String getTickerSymbol() {
		return id.getTickerSymbol();
	}

	public void setId(String organizationName, String tickerSymbol) {
		this.id = new StockKey(organizationName, tickerSymbol);
	}

}
