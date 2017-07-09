package com.qsense.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.qsense.dao.UserObservationDAO;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserObservationEntity;
import com.qsense.entity.UserSessionEntity;
import com.qsense.transfer.LeaderboardTO;
import com.qsense.util.logger.QSenseLogger;

/**
 * UserObservation Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("UserObservationDAO")
public class UserObservationDAOImpl extends CommonDAOImpl<UserObservationEntity>
		implements UserObservationDAO {
	QSenseLogger logger = QSenseLogger.getLogger(getClass());

	@Override
	public Class<UserObservationEntity> getModelClass() {
		return UserObservationEntity.class;
	}

	/* (non-Javadoc)
	 * @see com.qsense.dao.UserObservationDAO#getAllObservationsForDashboard(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public List getAllObservationsForDashboard(Long userId, String dateInGMT, String nextDateInGMT) {
		logger.info("Begin : UserObservationDAOImpl.getAllObservationsForDashboard");
		initCommonAttributes();
		Query createNativeQuery;
			
		createNativeQuery = entityManager
				.createNativeQuery(

									"SELECT sub_category.name,sub_category.color,sub_category.units,"
											+ " SUM( IF(start_timestamp >= \'"+ dateInGMT  + "\' AND end_timestamp < \'"+nextDateInGMT+ "\', "
													+ "	(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp  ) ), "
															+ "IF(start_timestamp < \'"+ dateInGMT+ "\' AND end_timestamp > \'"+dateInGMT
																	+ "\' AND end_timestamp <= \'"+nextDateInGMT
																	+ "\' , (TIMESTAMPDIFF(SECOND, \'"+dateInGMT+"\' ,end_timestamp  )), "
																			+ "IF(start_timestamp < \'"+ nextDateInGMT + "\' AND start_timestamp >= \'"+dateInGMT
																					+ "\' AND end_timestamp > \'"+nextDateInGMT + "\', "
																				+ "(TIMESTAMPDIFF(SECOND, start_timestamp, \'"+nextDateInGMT+"\' )),"
																			+ "NULL)))) AS duration,"
											+ " sub_category.display_name AS display_name "
									+ "FROM user_observation, sub_category "
									+ "WHERE sub_category.is_dashboard_visible = TRUE AND sub_category.category_id = 1"
											+ " AND user_id = "+userId +" AND user_observation.sub_category_id = sub_category.id "
											+ " AND end_timestamp >= \'" +dateInGMT
												+ "\' AND start_timestamp < \'"+nextDateInGMT+ "\'"
									+ " GROUP BY sub_category_id "
									+ " HAVING duration IS NOT NULL;"
							
					/*		"SELECT name, color, units, sum(value1), display_name from("
							+ "SELECT a.name, a.color, a.units, SUM(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp)) AS value1, a.display_name "
							+ "FROM user_observation, sub_category a, category c WHERE user_id="+ userId 
							+" AND c.name = 'ACTIVITY' AND a.is_dashboard_visible =1 AND a.category_id = c.id"
							+" AND a.id = sub_category_id AND start_timestamp >= \'"+ dateInGMT
							+ "\' AND end_timestamp < \'"+nextDateInGMT
							+ "\' GROUP BY sub_category_id "
							
							+ " union all "
							
							+ "SELECT a.name, a.color, a.units, SUM(TIMESTAMPDIFF(SECOND, \'"+dateInGMT+"\' ,end_timestamp)) AS value1, a.display_name "
							+ "FROM user_observation, sub_category a, category c WHERE user_id="+ userId 
							+" AND c.name = 'ACTIVITY' AND a.is_dashboard_visible =1 AND a.category_id = c.id"
							+" AND a.id = sub_category_id AND start_timestamp < \'"+ dateInGMT
							+ "\' AND end_timestamp > \'"+dateInGMT
							+ "\' AND end_timestamp <= \'"+nextDateInGMT
							+ "\' GROUP BY sub_category_id "
							
							
							+ " union all "
	
							+ "SELECT a.name, a.color, a.units, SUM(TIMESTAMPDIFF(SECOND, start_timestamp, \'"+nextDateInGMT+"\')) AS value1, a.display_name "
							+ "FROM user_observation, sub_category a, category c WHERE user_id="+ userId 
							+" AND c.name = 'ACTIVITY' AND a.is_dashboard_visible =1 AND a.category_id = c.id"
							+" AND a.id = sub_category_id AND start_timestamp < \'"+ nextDateInGMT
							+ "\' AND start_timestamp >= \'"+dateInGMT
							+ "\' AND end_timestamp > \'"+nextDateInGMT
							+ "\' GROUP BY sub_category_id "
							+ ")x group by name;"
							*/	
						);
			
			
		List resultList = createNativeQuery.getResultList();
		logger.info("End : UserObservationDAOImpl.getAllObservationsForDashboard");
		return resultList;
	}

	@Override
	public Double getStepCountForDashboardStatus(UserEntity user, Date dateInGMT, Date nextDateInGMT, SubCategoryEntity subCategoryEntity){
		logger.info("Begin : UserObservationDAOImpl.getStepCountForDashboardStatus");
		initCommonAttributes();
		CriteriaQuery<Double> sumCriteria = builder.createQuery( Double.class );
		Root<UserObservationEntity> root1 = sumCriteria.from( UserObservationEntity.class );
		Path<Double> path = root1.get("field1");		
		sumCriteria.select(builder.sum(path));
		Predicate predicate1 = builder.equal(root1.get("user"),user);
		Predicate predicate2 = builder.equal(root1.get("subCategory"),subCategoryEntity);
		Predicate predicate3 = builder.greaterThanOrEqualTo(root1.<Date>get("startTimestamp"), dateInGMT);
		Predicate predicate4 = builder.lessThan(root1.<Date>get("startTimestamp"), nextDateInGMT);
		sumCriteria.where( predicate1,predicate2,predicate3,predicate4);		
		
		Double totalRecords = entityManager.createQuery( sumCriteria ).getSingleResult();
		logger.info("End : UserObservationDAOImpl.getStepCountForDashboardStatus");
		return totalRecords;
	}
	
	@Override
	public Double getLuminescenceForDashboardStatus(UserEntity user, Date dateInGMT, Date nextDateInGMT, SubCategoryEntity subCategoryEntity){
		logger.info("Begin : UserObservationDAOImpl.getLuminescenceForDashboardStatus");
		initCommonAttributes();
		CriteriaQuery<Double> avgCriteria = builder.createQuery( Double.class );
		Root<UserObservationEntity> root1 = avgCriteria.from( UserObservationEntity.class );
		Path<Double> path = root1.get("field3");
		avgCriteria.select(builder.avg(path));
		Predicate predicate1 = builder.equal(root1.get("user"),user);
		Predicate predicate2 = builder.equal(root1.get("subCategory"),subCategoryEntity);
		Predicate predicate3 = builder.greaterThanOrEqualTo(root1.<Date>get("startTimestamp"), dateInGMT);
		Predicate predicate4 = builder.lessThan(root1.<Date>get("startTimestamp"), nextDateInGMT);
		avgCriteria.where( predicate1,predicate2,predicate3,predicate4);		
		
		Double totalRecords = entityManager.createQuery( avgCriteria ).getSingleResult();
		logger.info("End : UserObservationDAOImpl.getLuminescenceForDashboardStatus");
		return totalRecords;
	
	}
	
	@Override
	public Object getStepCountForLeaderboard(Long groupId,
			String dateInGMT, String nextDateInGMT, Long subCategoryId){
		
		logger.info("Begin : UserObservationDAOImpl.getStepCountForLeaderboard");
		initCommonAttributes();
		Query createNativeQuery;
		createNativeQuery = entityManager
				.createNativeQuery("SELECT min(t.steps),  max(t.steps), avg(t.steps) "
						+ "FROM("
						+ "SELECT user_id, sub_category_id, sum(field1) AS steps"
						+ " FROM user_observation, user u where start_timestamp >= \'"+ dateInGMT
						+ "\' AND start_timestamp < \'"+nextDateInGMT
						+ "\' AND u.group_id = "+groupId
						+ " AND u.id= user_id AND sub_category_id = "+subCategoryId
						+ " group by sub_category_id, user_id"
						+ ") t;"
						);
		 Object resultList = createNativeQuery.getSingleResult();
		
		logger.info("End : UserObservationDAOImpl.getStepCountForLeaderboard");
		return resultList;
	}
	
	@Override
	public List getStepCountForLeaderboardForEveryGroup(String dateInGMT, String nextDateInGMT, Long subCategoryId){
		logger.info("Begin : UserObservationDAOImpl.getStepCountForLeaderboardForEveryGroup");
		initCommonAttributes();
		Query createNativeQuery;
		createNativeQuery = entityManager
				.createNativeQuery("SELECT  ag.name, min(t.steps),  max(t.steps), avg(t.steps) "
										+ " FROM("
											+ " SELECT user_id, sub_category_id, sum(field1) AS steps, u.group_id as group_id"
												+ " FROM user_observation, user u where start_timestamp >= \'"+ dateInGMT
														+ "\' AND start_timestamp < \'"+nextDateInGMT
														+ "\'AND u.id= user_id AND sub_category_id = "+subCategoryId
														+ " group by sub_category_id, user_id"
										+ " ) t , app_group ag where ag.id = group_id group by group_id;"
								);
		List resultList = createNativeQuery.getResultList();
		logger.info("End : UserObservationDAOImpl.getStepCountForLeaderboardForEveryGroup");
		return resultList;
	}
	
	
	@Override
	public List<LeaderboardTO> getActivitiesForLeaderboard(Long userId, Long groupId,
			String dateInGMT, String nextDateInGMT, String leaderboardVisibleActivityIdsString) {
		
		logger.info("Begin : UserObservationDAOImpl.getActivitiesForLeaderboard");
		initCommonAttributes();
		Query createNativeQuery;
			createNativeQuery = entityManager
					.createNativeQuery(
			//In this query we are not joining to the sub_category table
							
							"SELECT t1.sub_category_id AS activity_id, t1.isUser, "
									   + " MIN(t1.duration) AS min_duration, MAX(t1.duration) AS max_duration, AVG(t1.duration) AS avg_duration,"
									   + " COUNT(DISTINCT (user_id)) AS distinct_users"
									   		+ " FROM( "
									   				+ "	SELECT user_id, sub_category_id, "
									   					+ " SUM( IF(start_timestamp >= \'"+ dateInGMT  + "\' AND end_timestamp < \'"+nextDateInGMT+ "\', "
									   					+ "	(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp  ) ), "
									   						+ "IF(start_timestamp < \'"+ dateInGMT+ "\' AND end_timestamp > \'"+dateInGMT
															+ "\' AND end_timestamp <= \'"+nextDateInGMT
															+ "\' , (TIMESTAMPDIFF(SECOND, \'"+dateInGMT+"\' ,end_timestamp  )), "
																	+ "IF(start_timestamp < \'"+ nextDateInGMT + "\' AND start_timestamp >= \'"+dateInGMT
																			+ "\' AND end_timestamp > \'"+nextDateInGMT + "\', "
																					+ "(TIMESTAMPDIFF(SECOND, start_timestamp, \'"+nextDateInGMT+"\' )),"
																							+ "NULL)))) AS duration,"
														+ " IF(user_id = "+ userId +", 1, 0) AS isUser "
																+ "	FROM user_observation, user u "
														+ " WHERE "
														+ " u.group_id = "+groupId 
														+ " AND u.id = user_id"
														+ " AND sub_category_id IN ("+ leaderboardVisibleActivityIdsString
														+ ") AND end_timestamp >= \'" +dateInGMT
														+ "\' AND start_timestamp < \'"+nextDateInGMT+ "\'"
											+ " GROUP BY sub_category_id , user_id) AS t1"
											+ " WHERE t1.duration is not null"
									+ " GROUP BY sub_category_id , t1.isUser;"
											
									
			//This code is to be used whenever we join by sub_category table also				
//							"SELECT t1.name AS activity_name, t1.isUser, "
//							   + " MIN(t1.duration) AS min_duration, MAX(t1.duration) AS max_duration, AVG(t1.duration) AS avg_duration,"
//							   + " COUNT(DISTINCT (user_id)) AS distinct_users"
//							   		+ " FROM( "
//							   				+ "	SELECT user_id, sub_category_id, "
//							   					+ " SUM( IF(start_timestamp >= \'"+ dateInGMT  + "\' AND end_timestamp < \'"+nextDateInGMT+ "\', "
//							   					+ "	(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp  ) ), "
//							   						+ "IF(start_timestamp < \'"+ dateInGMT+ "\' AND end_timestamp > \'"+dateInGMT
//													+ "\' AND end_timestamp <= \'"+nextDateInGMT
//													+ "\' , (TIMESTAMPDIFF(SECOND, \'"+dateInGMT+"\' ,end_timestamp  )), "
//															+ "IF(start_timestamp < \'"+ nextDateInGMT + "\' AND start_timestamp >= \'"+dateInGMT
//																	+ "\' AND end_timestamp > \'"+nextDateInGMT + "\', "
//																			+ "(TIMESTAMPDIFF(SECOND, start_timestamp, \'"+nextDateInGMT+"\' )),"
//																					+ "NULL)))) AS duration,"
//												+ "	sub_category.name AS name, "
//												+ " IF(user_id = "+ userId +", 1, 0) AS isUser"
//														+ "	FROM user_observation, user u, sub_category "
//												+ " WHERE "
//												+ " u.group_id = "+groupId 
//												+ "	AND sub_category.category_id = "+categoryId
//												+ " AND u.id = user_id"
//												+ "	AND user_observation.sub_category_id = sub_category.id "
//												+ " AND sub_category.is_leaderboard_visible= TRUE "
//												+ " AND end_timestamp >= \'" +dateInGMT
//												+ "\' AND start_timestamp < \'"+nextDateInGMT+ "\'"
//									+ " GROUP BY sub_category_id , user_id) AS t1"
//									+ " WHERE t1.duration is not null"
//							+ " GROUP BY sub_category_id , t1.isUser;"
							
							
							
							
						
							
							
//							//Get activity duration for the group
//							"SELECT tb1.name, min_duration, max_duration,avg_duration,display_name,user_duration "
//							+ "FROM ("
//							+"SELECT a.name, min(t1.duration2)as min_duration, max(t1.duration2) as max_duration, avg(t1.duration2) "
//							+ "as avg_duration, a.display_name "
//							+ "FROM("
//								+ "SELECT user_id, sub_category_id, sum(t2.duration) AS duration2 "
//								+ "FROM ("
//									+ "SELECT user_id, sub_category_id, sum(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp)) AS duration "
//											+ "FROM user_observation, user u WHERE  start_timestamp >= \'"+ dateInGMT
//											+ "\' AND end_timestamp < \'"+nextDateInGMT
//											+ "\' AND u.group_id = "+groupId
//											+ " AND u.id= user_id GROUP BY sub_category_id, user_id "
//											+ ""
//									+ " UNION ALL "
//									+ ""
//									+ "SELECT user_id, sub_category_id, sum(TIMESTAMPDIFF(SECOND, \'"+dateInGMT+"\' ,end_timestamp)) AS duration "
//											+ "FROM user_observation, user u WHERE  start_timestamp < \'"+ dateInGMT
//											+ "\' AND end_timestamp > \'"+dateInGMT
//											+ "\' AND end_timestamp <= \'"+nextDateInGMT
//											+ "\' AND u.group_id = "+groupId
//											+ " AND u.id= user_id GROUP BY sub_category_id, user_id"
//											+ ""
//									+ " UNION ALL "
//									+ ""
//									+ "SELECT user_id, sub_category_id, sum(TIMESTAMPDIFF(SECOND, start_timestamp, \'"+nextDateInGMT+"\')) AS duration "
//											+ "FROM user_observation, user u WHERE  start_timestamp < \'"+ nextDateInGMT
//											+ "\' AND start_timestamp >= \'"+dateInGMT
//											+ "\' AND end_timestamp > \'"+nextDateInGMT
//											+ "\' AND u.group_id = "+groupId
//											+ " AND u.id= user_id GROUP BY sub_category_id, user_id"
//								+ ")AS t2 GROUP BY user_id, sub_category_id"
//							+") AS t1, sub_category a "
//							+ "WHERE a.id = t1.sub_category_id AND a.category_id = "+categoryId
//							+ " GROUP BY sub_category_id) AS tb1"
//							
//							+ " LEFT JOIN "
//					//Get activity duration for the user
//							+"(SELECT name, sum(value1) AS user_duration FROM("
//							+ "SELECT a.name, a.color, a.units, SUM(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp)) AS value1, a.display_name "
//							+ "FROM user_observation, sub_category a, category c WHERE user_id="+ userId 
//							+" AND c.name = 'ACTIVITY' AND a.category_id = c.id"
//							+" AND a.id = sub_category_id AND start_timestamp >= \'"+ dateInGMT
//							+ "\' AND end_timestamp < \'"+nextDateInGMT
//							+ "\' GROUP BY sub_category_id "
//							
//							+ " union all "
//							
//							+ "SELECT a.name, a.color, a.units, SUM(TIMESTAMPDIFF(SECOND, \'"+dateInGMT+"\' ,end_timestamp)) AS value1, a.display_name "
//							+ "FROM user_observation, sub_category a, category c WHERE user_id="+ userId 
//							+" AND c.name = 'ACTIVITY' AND a.category_id = c.id"
//							+" AND a.id = sub_category_id AND start_timestamp < \'"+ dateInGMT
//							+ "\' AND end_timestamp > \'"+dateInGMT
//							+ "\' AND end_timestamp <= \'"+nextDateInGMT
//							+ "\' GROUP BY sub_category_id "
//							
//							
//							+ " union all "
//
//							+ "SELECT a.name, a.color, a.units, SUM(TIMESTAMPDIFF(SECOND, start_timestamp, \'"+nextDateInGMT+"\')) AS value1, a.display_name "
//							+ "FROM user_observation, sub_category a, category c WHERE user_id="+ userId 
//							+" AND c.name = 'ACTIVITY' AND a.category_id = c.id"
//							+" AND a.id = sub_category_id AND start_timestamp < \'"+ nextDateInGMT
//							+ "\' AND start_timestamp >= \'"+dateInGMT
//							+ "\' AND end_timestamp > \'"+nextDateInGMT
//							+ "\' GROUP BY sub_category_id "
//							+ ")x group by name) AS tb2 ON tb1.name = tb2.name ;"
						);
		
		List resultList = createNativeQuery.getResultList();
		logger.info("End : UserObservationDAOImpl.getActivitiesForLeaderboard");
		return resultList;
	}
	
	@Override
	public List<LeaderboardTO> getLeaderboardObservationByActivity(Long subCategoryId,String dateInGMT, String nextDateInGMT) {
		
		logger.info("Begin : UserObservationDAOImpl.getLeaderboardObservationByActivity");
		initCommonAttributes();
		Query createNativeQuery;
			createNativeQuery = entityManager
					.createNativeQuery(
							
							//Get activity duration for the group
							"SELECT t1.group_name AS group_name, MIN(t1.duration) AS min_duration, MAX(t1.duration) AS max_duration, AVG(t1.duration) AS avg_duration "
									+ "FROM("
											+ "SELECT user_id, group_id, app_group.name as group_name,"
													+ " SUM( IF(start_timestamp >= \'"+ dateInGMT  + "\' AND end_timestamp < \'"+nextDateInGMT+ "\', "
									   					+ "	(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp  ) ), "
										   						+ "IF(start_timestamp < \'"+ dateInGMT+ "\' AND end_timestamp > \'"+dateInGMT
																+ "\' AND end_timestamp <= \'"+nextDateInGMT
																+ "\' , (TIMESTAMPDIFF(SECOND, \'"+dateInGMT+"\' ,end_timestamp  )), "
																		+ "IF(start_timestamp < \'"+ nextDateInGMT + "\' AND start_timestamp >= \'"+dateInGMT
																				+ "\' AND end_timestamp > \'"+nextDateInGMT + "\', "
																						+ "(TIMESTAMPDIFF(SECOND, start_timestamp, \'"+nextDateInGMT+"\' )),"
																								+ "NULL"
																		+ ")"
																+ ")"
															+ ")"
														+ ") AS duration "
														+ "FROM user_observation, user, app_group "
														+ "WHERE user_observation.sub_category_id = "+subCategoryId+" AND user.id = user_id "
																+ "AND user.group_id = app_group.id "
																+ " AND end_timestamp >= \'" +dateInGMT
																+ "\' AND start_timestamp < \'"+nextDateInGMT+ "\'"
														+ "GROUP BY user_id "
														+ "HAVING duration IS NOT NULL"
									+ ") AS t1 "
							+ "GROUP BY group_id;"
							
							
//							"SELECT a.name, a.display_name, ag.name as group_name, min(t2.duration)as min_duration, max(t2.duration) as max_duration, avg(t2.duration) as avg_duration"
//							+ " FROM("
//								+ " SELECT user_id, group_id, sub_category_id, sum(t1.duration) AS duration"
//						                + " FROM("
//												+ " SELECT user_id, sub_category_id, u.group_id as group_id, sum(TIMESTAMPDIFF(SECOND, start_timestamp, end_timestamp)) AS duration "
//												+ " FROM user_observation, user u WHERE  start_timestamp >= \'"+ dateInGMT 
//												+ "\' AND end_timestamp < \'"+nextDateInGMT
//												+ "\' AND u.id= user_id AND sub_category_id = "+subCategoryId 
//												+ " GROUP BY  user_id, u.group_id"
//						                        + " UNION ALL"
//						                        + " SELECT user_id, sub_category_id, u.group_id as group_id,sum(TIMESTAMPDIFF(SECOND,  \'"+dateInGMT+"\'  ,end_timestamp)) AS duration" 
//						                        + " FROM user_observation, user u WHERE  start_timestamp < \'"+ dateInGMT
//						                        + "\' AND end_timestamp > \'"+dateInGMT
//						                        + "\' AND end_timestamp <= \'"+nextDateInGMT
//						                        + "\' AND u.id= user_id AND sub_category_id = "+subCategoryId 
//						                        + " GROUP BY  user_id, u.group_id"
//						                        + " UNION ALL "
//						                        + " SELECT user_id, sub_category_id,u.group_id as group_id, sum(TIMESTAMPDIFF(SECOND, start_timestamp,  \'"+nextDateInGMT+"\')) AS duration "
//						                        + " FROM user_observation, user u WHERE  start_timestamp < \'"+ nextDateInGMT
//					                        	+ "\' AND start_timestamp >= \'"+dateInGMT
//					                        	+ "\' AND end_timestamp > \'"+nextDateInGMT
//					                        	+ "\' AND u.id= user_id AND sub_category_id = "+subCategoryId 
//					                        	+ " GROUP BY  user_id, u.group_id"
//			                        	+ " )AS t1 GROUP BY user_id, sub_category_id, group_id"
//	                       + " ) AS t2, sub_category a, app_group ag WHERE a.id = t2.sub_category_id AND ag.id = t2.group_id GROUP BY sub_category_id,  t2.group_id;"
						);
		
		List resultList = createNativeQuery.getResultList();
		logger.info("End : UserObservationDAOImpl.getLeaderboardObservationByActivity");
		return resultList;
	}
	
	@Override
	public UserObservationEntity getLastProcessedRecordBySession(UserEntity userEntity, List<SubCategoryEntity> subCategoryEntities, UserSessionEntity userSessionEntity) {
		logger.info("Begin : UserObservationDAOImpl.getLastProcessedRecordBySession");
		UserObservationEntity userObservationEntity = null;
		initCommonAttributes();
		Predicate predicate1 = builder.equal(root.get("user"),userEntity);
		Predicate predicate2 = builder.in(root.get("subCategory")).value(subCategoryEntities);
		Predicate predicate3 = builder.equal(root.get("userSession"),userSessionEntity);
		criteria.where(predicate1, predicate2, predicate3);
		criteria.orderBy(builder.desc(root.get("id")));
		List<UserObservationEntity> results = getResults(1);
		if(results!=null && results.size()>0){
			userObservationEntity = results.get(0);
		}
		logger.info("End : UserObservationDAOImpl.getLastProcessedRecordBySession");
		return userObservationEntity;
	}
	
	@Override
	public UserObservationEntity getLastProcessedRecordBySession(UserEntity userEntity, UserSessionEntity userSessionEntity) {
		logger.info("Begin : UserObservationDAOImpl.getLastProcessedRecordBySession");
		UserObservationEntity userObservationEntity = null;
		initCommonAttributes();
		Predicate predicate1 = builder.equal(root.get("user"),userEntity);
		Predicate predicate2 = builder.equal(root.get("userSession"),userSessionEntity);
		criteria.where(predicate1, predicate2);
		criteria.orderBy(builder.desc(root.get("id")));
		List<UserObservationEntity> results = getResults(1);
		if(results!=null && results.size()>0){
			userObservationEntity = results.get(0);
		}
		logger.info("End : UserObservationDAOImpl.getLastProcessedRecordBySession");
		return userObservationEntity;
	}
	
	@Override
	public Long getActiveUsers(String dateInGMT, String nextDateInGMT, Long groupId){
		
		logger.info("Begin : UserObservationDAOImpl.getActiveUsers");
		initCommonAttributes();
		Query createNativeQuery;
		createNativeQuery = entityManager
				.createNativeQuery(

						"SELECT count(distinct(user_id)) FROM user_observation "
						+ "where ("
									+ "(start_timestamp >= \'"+ dateInGMT + "\' AND start_timestamp < \'"+nextDateInGMT+ "\') "
										+ " OR "
									+ "(end_timestamp >= \'"+ dateInGMT + "\' AND end_timestamp < \'"+nextDateInGMT+ "\') "
							+ ") AND user_id IN ("
							+ "SELECT id from user where group_id="+groupId
							+");"
							
/*						"SELECT g.name, count(distinct(user_id)) FROM user_observation, "
						+ "user u, app_group g where u.id = user_id and u.group_id= g.id and "
						+ "start_timestamp >= \'"+ dateInGMT
						+ "\' AND end_timestamp < \'"+nextDateInGMT
						+ "\' group by u.group_id;"*/
						);
		Object users = createNativeQuery.getSingleResult();
		Long result = 0L;
		if(users!=null){
			result = Long.parseLong(users.toString());
		}
		logger.info("End : UserObservationDAOImpl.getActiveUsers");
		return result;
	}

	@Override
	public List<UserObservationEntity> getAllByUserId(Long userId, Date fromDate) {
		logger.info("Begin : UserObservationDAOImpl.getAllByUserIdAndFromDate");
		initCommonAttributes();
		Predicate predicate1 = builder.equal(root.get("user"),userId);
		Predicate predicate2 = builder.greaterThanOrEqualTo(root.<Date>get("startTimestamp"),fromDate);
		criteria.where(predicate1,predicate2);
		List<UserObservationEntity> userObservationsList = getResults();
		logger.info("Begin : UserObservationDAOImpl.getAllByUserIdAndFromDate");
		return userObservationsList;
	}

	@Override
	public List<UserObservationEntity> getBySubCategoryIdAndUserId(Long subCategoryId, Long userId) {
		logger.info("Begin : UserObservationDAOImpl.getBySubCategoryId");
		initCommonAttributes();
		Predicate predicate1 = builder.equal(root.get("user"),userId);
		Predicate predicate2 = builder.equal(root.get("subCategory"),subCategoryId);
		criteria.where(predicate1,predicate2);
		List<UserObservationEntity> userObservationsEntities = getResults();
		logger.info("Begin : UserObservationDAOImpl.getBySubCategoryId");
		return userObservationsEntities;
	}

	@Override
	public List getByUserId(Long userId) {
		logger.info("Begin : UserObservationDAOImpl.getByUserId");
		initCommonAttributes();
		Query createNativeQuery;
		createNativeQuery = entityManager
				.createNativeQuery(

						"select ga.sub_category_id, sum(value), ug.goal_id, ug.start_time "
						+ "from goal_attributes ga, user_goal ug "
						+ "where ug.goal_id = ga.goal_id and ug.goal_id in ("
						+ "select ug.goal_id "
						+ "from user_goal ug, goal g, duration d "
						+ "where ug.user_id="+ userId +" and ug.goal_id=g.id and g.duration_id=d.id "
						+ "and NOW() BETWEEN ug.start_time AND TIMESTAMPADD(DAY, d.number_of_days, ug.start_time)) group by sub_category_id;"
							
/*						"SELECT g.name, count(distinct(user_id)) FROM user_observation, "
						+ "user u, app_group g where u.id = user_id and u.group_id= g.id and "
						+ "start_timestamp >= \'"+ dateInGMT
						+ "\' AND end_timestamp < \'"+nextDateInGMT
						+ "\' group by u.group_id;"*/
						);
		List subCategoryEntities = createNativeQuery.getResultList();
		logger.info("End : UserObservationDAOImpl.getByUserId");
		return subCategoryEntities;
	}

	@Override
	public List<UserEntity> getTopContributers() {
		logger.info("Begin : UserObservationDAOImpl.getTopContributers");
		initCommonAttributes();
		Query createNativeQuery;
		createNativeQuery = entityManager
				.createNativeQuery(
						"select user_id,sub_category_id, (end_timestamp-start_timestamp) as top from user_observation GROUP BY sub_category_id ORDER BY top DESC;"
						);
		List resultList = createNativeQuery.getResultList();
		logger.info("End : UserObservationDAOImpl.getTopContributers");
		return resultList;
	}
	
}
