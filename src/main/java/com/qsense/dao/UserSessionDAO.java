package com.qsense.dao;

import com.qsense.entity.UserEntity;
import com.qsense.entity.UserSessionEntity;


/**
 * 
 * @author Kushal
 *
 */
public interface UserSessionDAO extends CommonDAO<UserSessionEntity>
{
	 public UserSessionEntity getExclusiveSession(UserEntity userEntity, Long sessionId);
}
