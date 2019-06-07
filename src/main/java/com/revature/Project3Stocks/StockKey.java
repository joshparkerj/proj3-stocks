package com.revature.Project3Stocks;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class StockKey implements Serializable {

	private static final long serialVersionUID = 3940689878047005037L;
	private String organizationName;
	private String tickerSymbol;

	public StockKey() {
		super();
	}

	public StockKey(String organizationName, String tickerSymbol) {
		super();
		this.organizationName = organizationName;
		this.tickerSymbol = tickerSymbol;
	}

	@Override
	public String toString() {
		return "StockKey [organizationName=" + organizationName + ", tickerSymbol=" + tickerSymbol + "]";
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public boolean equals(String on2, String ts2) {
		return (organizationName.equals(on2) && tickerSymbol.equals(ts2));
	}

}
