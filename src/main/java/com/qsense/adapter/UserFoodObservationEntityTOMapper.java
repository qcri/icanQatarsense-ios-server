package com.qsense.adapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.UserFoodObservationEntity;
import com.qsense.transfer.FoodSubTypeTO;
import com.qsense.transfer.ObservationUnitTO;
import com.qsense.transfer.UserFoodObservationTO;

@Component
public class UserFoodObservationEntityTOMapper implements
		EntityTOMapper<UserFoodObservationEntity, UserFoodObservationTO> {

	@Override
	public UserFoodObservationTO toDTO(UserFoodObservationEntity entity) {
		UserFoodObservationTO userFoodObservationTO = new UserFoodObservationTO();
		
		FoodSubTypeEntity foodSubTypeEntity = entity.getFoodSubType();
		FoodSubTypeTO foodSubType = new FoodSubTypeTO();
		foodSubType.setId(foodSubTypeEntity.getId());
		foodSubType.setName(foodSubTypeEntity.getName());
		foodSubType.setIconSource(foodSubTypeEntity.getIconSource());
		foodSubType.setDisplayName(foodSubTypeEntity.getDisplayName());
		
		ObservationUnitTO observationUnit = new ObservationUnitTO();
		observationUnit.setName(foodSubTypeEntity.getObservationUnit().getName());
		
		foodSubType.setObservationUnit(observationUnit);
		userFoodObservationTO.setFoodSubType(foodSubType);
		userFoodObservationTO.setTime(entity.getTime().toString());
		userFoodObservationTO.setFoodServingSize(entity.getFoodServingSize());
		userFoodObservationTO.setSessionId(entity.getUserSession().getId());
		userFoodObservationTO.setSessionRecordId(entity.getSessionRecordId());
		
		return userFoodObservationTO;
	}

	@Override
	public List<UserFoodObservationEntity> toEntity(
			Collection<UserFoodObservationTO> dtos) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserFoodObservationTO> toDTO(
			Collection<UserFoodObservationEntity> entities) {
		List<UserFoodObservationTO> userFoodObservationTO = new ArrayList<UserFoodObservationTO>();
		for (UserFoodObservationEntity userFoodObservationEntity : entities) {
			userFoodObservationTO.add(toDTO(userFoodObservationEntity));
		}
		return userFoodObservationTO;
	}

	@Override
	public UserFoodObservationEntity toEntity(UserFoodObservationTO dto)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
