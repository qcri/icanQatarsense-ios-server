package com.qsense.dao;

import java.util.List;

import com.qsense.entity.MessageEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserMessageEntity;

/**
 * UserMessage DAO
 * 
 * @author Neeraj
 */
public interface UserMessageDAO extends CommonDAO<UserMessageEntity> {
	public List<UserMessageEntity> getByUserIdAndMessageIds(UserEntity user, List<MessageEntity> messageEntities);
	public UserMessageEntity getByUserIdAndMessageId(UserEntity user, MessageEntity messageEntity);		
	public List<UserMessageEntity> getByUserId(UserEntity user, int pageNumber, int itemsPerPage, boolean isDeleted);
	public Long getMessageCountByUserId(UserEntity user);
	public Long getUnreadMessageCountByUserId(UserEntity user);
}
