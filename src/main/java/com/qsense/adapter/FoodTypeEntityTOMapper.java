package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qsense.entity.FoodTypeEntity;
import com.qsense.transfer.FoodTypeTO;

@Component
public class FoodTypeEntityTOMapper implements
		EntityTOMapper<FoodTypeEntity, FoodTypeTO> {

	@Override
	public FoodTypeEntity toEntity(FoodTypeTO dto) {
		FoodTypeEntity foodTypeEntity = new FoodTypeEntity();
		foodTypeEntity.setName(dto.getName());
		foodTypeEntity.setId(dto.getId());
		foodTypeEntity.setPrice(dto.getPrice());
		return foodTypeEntity;
	}

	@Override
	public FoodTypeTO toDTO(FoodTypeEntity entity) {
		FoodTypeTO foodTypeTO = new FoodTypeTO();
		foodTypeTO.setName(entity.getName());
		foodTypeTO.setId(entity.getId());
		foodTypeTO.setPrice(entity.getPrice());
		return foodTypeTO;
	}

	@Override
	public List<FoodTypeEntity> toEntity(Collection<FoodTypeTO> dtos) {
		List<FoodTypeEntity> foodTypeEntities = new ArrayList<FoodTypeEntity>();		
		for (FoodTypeTO foodTypeTO : dtos) {			
			foodTypeEntities.add(toEntity(foodTypeTO));
		}
		return foodTypeEntities;
	}

	@Override
	public List<FoodTypeTO> toDTO(Collection<FoodTypeEntity> entities) {
		List<FoodTypeTO> foodTypeTOs = new ArrayList<FoodTypeTO>();
		for (FoodTypeEntity foodTypeEntity : entities) {
			foodTypeTOs.add(toDTO(foodTypeEntity));
		}
		return foodTypeTOs;
	}
}
