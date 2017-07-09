package com.qsense.util;

import java.util.Comparator;

import com.qsense.entity.UserObservationEntity;

public class UserObservationComparator implements Comparator<UserObservationEntity> {
	/**
	 * Comparator to sort UserObservationEntity list or array in order of
	 * startTimestamp and if the startTimestamp is same then sort on the basis of sessionRecordId
	 */

	@Override
	public int compare(UserObservationEntity a1, UserObservationEntity a2) {
		int result =  a1.getStartTimestamp().compareTo(a2.getStartTimestamp());
		if(result == 0){
			if(a1.getSessionRecordId()!=null && a2.getSessionRecordId()!=null){
				result = a1.getSessionRecordId().compareTo(a2.getSessionRecordId());
			}
		}
		return result;
	}
}
