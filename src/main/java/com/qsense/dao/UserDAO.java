
package com.qsense.dao;

import java.util.List;

import com.qsense.entity.AppGroupEntity;
import com.qsense.entity.RoleEntity;
import com.qsense.entity.UserEntity;



/**
 * 
 * @author Neeraj
 *
 */
public interface UserDAO extends CommonDAO<UserEntity>
{
	UserEntity findUserByUserId(Long userId);
	
	UserEntity findUserByUserName(String userName);

	boolean verifyAuthToken(String authToken, String userid);

	Long verifyLogin(String userName, String password);

	void saveAuthToken(String authToken, String userName);
	
	void saveAuthToken(String authToken, String userName, String permanentDeviceId);
	
	List<UserEntity> getAllUsersByGroupAndRole(AppGroupEntity group,
			RoleEntity role);

	boolean getByUserName(String userName);

	boolean removeAuthToken(Long userId);

	List<UserEntity> getAllUnAssociatedParticipant();

	Long getCoinsForGroup(AppGroupEntity group);
	
}
