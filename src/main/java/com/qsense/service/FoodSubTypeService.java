package com.qsense.service;

import java.util.List;

import com.qsense.transfer.FoodSubTypeTO;

public interface FoodSubTypeService {	

	List<FoodSubTypeTO> getAll();

	List<FoodSubTypeTO> getAllByFoodTypeId(Long foodTypeId);

	FoodSubTypeTO getById(Long id);	

}