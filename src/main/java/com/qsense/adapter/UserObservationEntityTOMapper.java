package com.qsense.adapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.UserFoodObservationEntity;
import com.qsense.entity.UserObservationEntity;
import com.qsense.transfer.FoodSubTypeTO;
import com.qsense.transfer.ObservationUnitTO;
import com.qsense.transfer.UserFoodObservationTO;
import com.qsense.transfer.UserObservationTO;

@Component
public class UserObservationEntityTOMapper implements
		EntityTOMapper<UserObservationEntity, UserObservationTO> {

	@Override
	public UserObservationTO toDTO(UserObservationEntity entity) {
		UserObservationTO userObservationTO = new UserObservationTO();
		
		userObservationTO.setStartTimestamp(entity.getStartTimestamp().toString());
		userObservationTO.setEndTimestamp(entity.getEndTimestamp().toString());
		userObservationTO.setField1(entity.getField1());
		userObservationTO.setField2(entity.getField2());
		userObservationTO.setField3(entity.getField3());
		userObservationTO.setIsManual(entity.getIsManual());
		userObservationTO.setSubCategoryName(entity.getSubCategory().getName());
		return userObservationTO;
	}

	@Override
	public List<UserObservationTO> toDTO(
			Collection<UserObservationEntity> entities) {
		List<UserObservationTO> userObservationTO = new ArrayList<UserObservationTO>();
		for (UserObservationEntity userObservationEntity : entities) {
			userObservationTO.add(toDTO(userObservationEntity));
		}
		return userObservationTO;
	}

	@Override
	public UserObservationEntity toEntity(UserObservationTO dto)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserObservationEntity> toEntity(
			Collection<UserObservationTO> dtos) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
