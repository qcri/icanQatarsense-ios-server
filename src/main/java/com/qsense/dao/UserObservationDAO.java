package com.qsense.dao;

import java.util.Date;
import java.util.List;

import com.qsense.entity.SubCategoryEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserObservationEntity;
import com.qsense.entity.UserSessionEntity;
import com.qsense.transfer.LeaderboardTO;

/**
 * UserActivity DAO
 * 
 * @author Neeraj
 */
public interface UserObservationDAO extends CommonDAO<UserObservationEntity> {
	List getAllObservationsForDashboard(Long userId, String date, String nextDateInGMT);
	
	Double getStepCountForDashboardStatus(UserEntity user, Date dateInGMT, Date nextDateInGMT, SubCategoryEntity subCategoryEntity);
	
	Double getLuminescenceForDashboardStatus(UserEntity user, Date dateInGMT, Date nextDateInGMT, SubCategoryEntity subCategoryEntity);
	
	List<LeaderboardTO> getActivitiesForLeaderboard(Long userId, Long groupId, String date, String nextDateInGMT, String leaderboardVisibleActivityIdsString);
	
	UserObservationEntity getLastProcessedRecordBySession(UserEntity userEntity, List<SubCategoryEntity> subCategoryEntities, UserSessionEntity userSessionEntity);
	
	UserObservationEntity getLastProcessedRecordBySession(UserEntity userEntity, UserSessionEntity userSessionEntity);
	
	Object getStepCountForLeaderboard(Long groupId,String dateInGMT, String nextDateInGMT, Long subCategoryId);
	
	Long getActiveUsers(String dateInGMT, String nextDateInGMT, Long groupId);
	
	List<LeaderboardTO> getLeaderboardObservationByActivity(Long subCategoryId,String dateInGMT, String nextDateInGMT);
	
	List getStepCountForLeaderboardForEveryGroup(String dateInGMT, String nextDateInGMT, Long subCategoryId);

	List<UserObservationEntity> getAllByUserId(Long userId, Date fromDate);

	List<UserObservationEntity> getBySubCategoryIdAndUserId(Long subCategoryId, Long userId);

	List getByUserId(Long userId);

	List<UserEntity> getTopContributers();
}
