package com.qsense.transfer;

import java.io.Serializable;

public class CoinsTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1659323042788093437L;
	
	private Long numberOfCoins;

	public Long getNumberOfCoins() {
		return numberOfCoins;
	}

	public void setNumberOfCoins(Long numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}
	
}
