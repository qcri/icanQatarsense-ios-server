package com.qsense.util;

import java.util.Comparator;

import com.qsense.entity.UserFoodObservationEntity;

public class UserFoodObservationComparator implements Comparator<UserFoodObservationEntity> {
	/**
	 * Comparator to sort UserFoodObservationEntity list or array in order of
	 * time and if the time is same then sort on the basis of sessionRecordId
	 */

	@Override
	public int compare(UserFoodObservationEntity entity1,
			UserFoodObservationEntity entity2) {
		int result =  entity1.getTime().compareTo(entity2.getTime());
		if(result == 0){
			if(entity1.getSessionRecordId()!=null && entity2.getSessionRecordId()!=null){
				result = entity1.getSessionRecordId().compareTo(entity2.getSessionRecordId());
			}
		}
		return result;
	}
}
