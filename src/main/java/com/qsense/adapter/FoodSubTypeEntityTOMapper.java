package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.transfer.FoodSubTypeTO;
import com.qsense.transfer.FoodTypeTO;

@Component
public class FoodSubTypeEntityTOMapper implements
		EntityTOMapper<FoodSubTypeEntity, FoodSubTypeTO> {

	@Override
	public FoodSubTypeEntity toEntity(FoodSubTypeTO dto) {
		FoodSubTypeEntity foodSubTypeEntity = new FoodSubTypeEntity();
		foodSubTypeEntity.setName(dto.getName());
		foodSubTypeEntity.setId(dto.getId());
		foodSubTypeEntity.setIconSource(dto.getIconSource());
		foodSubTypeEntity.setDisplayName(dto.getDisplayName());
		return foodSubTypeEntity;
	}

	@Override
	public FoodSubTypeTO toDTO(FoodSubTypeEntity entity) {
		FoodSubTypeTO foodSubTypeTO = new FoodSubTypeTO();
		foodSubTypeTO.setName(entity.getName());
		foodSubTypeTO.setId(entity.getId());
		foodSubTypeTO.setIconSource(entity.getIconSource());
		foodSubTypeTO.setDisplayName(entity.getDisplayName());
		
		FoodTypeTO foodTypeTO = new FoodTypeTO();
		foodTypeTO.setId(entity.getFoodType().getId());
		foodTypeTO.setName(entity.getFoodType().getName());
		foodTypeTO.setPrice(entity.getFoodType().getPrice());
		foodSubTypeTO.setFoodTypeTO(foodTypeTO);
		
		return foodSubTypeTO;
	}

	@Override
	public List<FoodSubTypeEntity> toEntity(Collection<FoodSubTypeTO> dtos) {
		List<FoodSubTypeEntity> foodSubTypeEntities = new ArrayList<FoodSubTypeEntity>();		
		for (FoodSubTypeTO foodSubTypeTO : dtos) {			
			foodSubTypeEntities.add(toEntity(foodSubTypeTO));
		}
		return foodSubTypeEntities;
	}

	@Override
	public List<FoodSubTypeTO> toDTO(Collection<FoodSubTypeEntity> entities) {
		List<FoodSubTypeTO> foodSubTypeTOs = new ArrayList<FoodSubTypeTO>();
		for (FoodSubTypeEntity foodSubTypeEntity : entities) {
			foodSubTypeTOs.add(toDTO(foodSubTypeEntity));
		}
		return foodSubTypeTOs;
	}
}
