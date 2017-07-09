package com.qsense.transfer;

import com.qsense.util.FoodTypeEnum;


public class FoodTypeTO extends CommonTO {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2253912982359237034L;
	private FoodTypeEnum name;
	private Long price;

	public FoodTypeEnum getName() {
		return name;
	}

	public void setName(FoodTypeEnum name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	} 
	
}
