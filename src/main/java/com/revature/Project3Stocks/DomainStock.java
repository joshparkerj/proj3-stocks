package com.revature.Project3Stocks;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class DomainStock {

	@Id
	private String id;

	private String tickerSymbol;
	private String companyName;
	private String organizationName;
	private Double amountSpent;
	private Double shares;

	@Override
	public String toString() {
		return "DomainStock [tickerSymbol=" + tickerSymbol + ", companyName=" + companyName + ", organizationName="
				+ organizationName + ", amountSpent=" + amountSpent + ", shares=" + shares + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountSpent == null) ? 0 : amountSpent.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((shares == null) ? 0 : shares.hashCode());
		result = prime * result + ((tickerSymbol == null) ? 0 : tickerSymbol.hashCode());
		return result;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DomainStock(String tickerSymbol, String companyName, String organizationName, Double amountSpent,
			Double shares, String id) {
		super();
		this.tickerSymbol = tickerSymbol;
		this.companyName = companyName;
		this.organizationName = organizationName;
		this.amountSpent = amountSpent;
		this.shares = shares;
		this.id = id;
	}

	public DomainStock() {
		super();
	}

}
