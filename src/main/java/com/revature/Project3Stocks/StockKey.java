package com.revature.Project3Stocks;

import java.io.Serializable;

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

	@Override
	public boolean equals(Object possibleStockKey) {
		if (possibleStockKey.getClass().equals(StockKey.class)) {
			StockKey sk = (StockKey) possibleStockKey;
			return (organizationName.equals(sk.getOrganizationName())
					&& tickerSymbol.contentEquals(sk.getTickerSymbol()));
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = tickerSymbol.hashCode();
		return 31 * result + organizationName.hashCode();
	}

	public static StockKey from(String organization, String tickersymbol2) {
		return new StockKey(organization, tickersymbol2);
	}

}
