package com.revature.Project3Stocks;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
@IdClass(StockKey.class)
public class DomainStock {

	@Id
	private String organizationName;

	@Id
	private String tickerSymbol;

	private String companyName;
	private Double amountSpent;
	private Double shares;

	public DomainStock() {
		super();
	}

	public DomainStock(String organizationName, String tickerSymbol, String companyName, Double amountSpent,
			Double shares) {
		super();
		this.organizationName = organizationName;
		this.tickerSymbol = tickerSymbol;
		this.companyName = companyName;
		this.amountSpent = amountSpent;
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "DomainStock [organizationName=" + organizationName + ", tickerSymbol=" + tickerSymbol + ", companyName="
				+ companyName + ", amountSpent=" + amountSpent + ", shares=" + shares + "]";
	}

	public StockKey getId() {
		return new StockKey(organizationName, tickerSymbol);
	}

	public void setId(StockKey id) {
		organizationName = id.getOrganizationName();
		tickerSymbol = id.getTickerSymbol();
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
		return organizationName;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setId(String organizationName, String tickerSymbol) {
		this.organizationName = organizationName;
		this.tickerSymbol = tickerSymbol;
	}

	public void combine(DomainStock s) {
		amountSpent += s.getAmountSpent();
		shares += s.getShares();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass().equals(DomainStock.class)) {
			DomainStock s = (DomainStock) o;
			if (s.getOrganizationName() == null || s.getTickerSymbol() == null)
				return false;
			return (s.getOrganizationName().equals(organizationName) && s.getTickerSymbol().equals(tickerSymbol));
		}
		if (o.getClass().equals(StockKey.class)) {
			StockKey s = (StockKey) o;
			return (s.getOrganizationName().equals(organizationName) && s.getTickerSymbol().equals(tickerSymbol));
		}
		return false;
	}

	public void overwrite(DomainStock u) {
		if (u.getAmountSpent() != null)
			amountSpent = u.getAmountSpent();
		if (u.getShares() != null)
			shares = u.getShares();
		if (u.getCompanyName() != null)
			companyName = u.getCompanyName();
	}

}
