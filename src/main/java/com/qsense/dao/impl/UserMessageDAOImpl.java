package com.qsense.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.qsense.dao.UserMessageDAO;
import com.qsense.entity.MessageEntity;
import com.qsense.entity.MessageStatusEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserMessageEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * UserMessage Access Object Implementation
 * 
 * @author Neeraj
 */
@Repository("UserMessageDAO")
public class UserMessageDAOImpl extends CommonDAOImpl<UserMessageEntity> implements UserMessageDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<UserMessageEntity> getModelClass() {
		return UserMessageEntity.class;
	}
	
	@Override
	public List<UserMessageEntity> getByUserIdAndMessageIds(UserEntity user, List<MessageEntity> messageEntities)
	{
		initCommonAttributes();
		logger.info("Begin : UserMessageDAO.getByUserIdAndMessageIds");
		Predicate predicate1 = builder.equal(root.get("user"),user);
		Predicate predicate2 = builder.in(root.get("message")).value(messageEntities);
		criteria.where(predicate1,predicate2);
		List<UserMessageEntity> results = getResults();
		logger.info("End : UserMessageDAO.getByUserIdAndMessageIds");
		return results;
	}
	
	@Override
	public UserMessageEntity getByUserIdAndMessageId(UserEntity user, MessageEntity messageEntity)
	{
		initCommonAttributes();
		logger.info("Begin : UserMessageDAO.getByUserIdAndMessageId");
		Predicate predicate1 = builder.equal(root.get("user"), user);
		Predicate predicate2 = builder.equal(root.get("message"), messageEntity);
		criteria.where(predicate1,predicate2);
		UserMessageEntity result = getSingleResult();
		logger.info("End : UserMessageDAO.getByUserIdAndMessageId");
		return result;
	}

	@Override
	public List<UserMessageEntity> getByUserId(UserEntity user, int pageNumber, int itemsPerPage, boolean isDeleted) {
		initCommonAttributes();
		logger.info("Begin : UserMessageDAO.getByUserId");
		Predicate predicate1 = builder.equal(root.get("user"),user);
		Join<UserMessageEntity,MessageEntity> userMessageEntitiesJoin = root.join("message");
		Predicate predicate2 = builder.equal(root.get("isDeleted"),isDeleted);
		criteria.where(predicate1, predicate2);
		criteria.orderBy(builder.desc(userMessageEntitiesJoin.get("postedAt")));
		List<UserMessageEntity> results = entityManager.createQuery(criteria).setFirstResult((pageNumber*itemsPerPage)).setMaxResults(itemsPerPage).getResultList();
		logger.info("End : UserMessageDAO.getByUserId");
		return results;
	}
	
	@Override
	public Long getMessageCountByUserId(UserEntity user) {
		logger.info("Begin : UserMessageDAO.getMessageCountByUserId");
		CriteriaQuery<Long> countCriteria = builder.createQuery( Long.class );
		Root<UserMessageEntity> root1 = countCriteria.from( UserMessageEntity.class );
		Path<Object> path = root1.get("user");
		countCriteria.select(builder.count(path));
		Predicate predicate1 = builder.equal(path,user);
		countCriteria.where( predicate1);		
		Long totalRecords = entityManager.createQuery( countCriteria ).getSingleResult();
		logger.info("End : UserMessageDAO.getMessageCountByUserId");
		return totalRecords;
	}
	
	@Override
	public Long getUnreadMessageCountByUserId(UserEntity user) {
		logger.info("Begin : UserMessageDAO.getUnreadMessageCountByUserId");
		CriteriaQuery<Long> countCriteria = builder.createQuery( Long.class );
		Root<UserMessageEntity> root1 = countCriteria.from( UserMessageEntity.class );
		Path<Object> path = root1.get("user");
		countCriteria.select(builder.count(path));
		Predicate predicate1 = builder.equal(path,user);
		Predicate predicate2 = builder.equal(root1.get("isRead"),false);
		countCriteria.where( predicate1,predicate2);		
		Long totalRecords = entityManager.createQuery( countCriteria ).getSingleResult();
		logger.info("End : UserMessageDAO.getUnreadMessageCountByUserId");
		return totalRecords;
	}
	
}
