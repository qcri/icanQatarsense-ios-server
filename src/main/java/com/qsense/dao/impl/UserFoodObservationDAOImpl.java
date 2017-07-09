package com.qsense.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.qsense.dao.UserFoodObservationDAO;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserFoodObservationEntity;
import com.qsense.entity.UserObservationEntity;
import com.qsense.util.FoodTypeEnum;
import com.qsense.util.logger.QSenseLogger;


/**
 * User Food Observation Access Object Implementation
 * 
 * @author Sanjay
 */
@Repository("UserFoodObservationDAO")
public class UserFoodObservationDAOImpl extends CommonDAOImpl<UserFoodObservationEntity> implements UserFoodObservationDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<UserFoodObservationEntity> getModelClass() {
		return UserFoodObservationEntity.class;
	}

	@Override
	public List<UserFoodObservationEntity> getAllByUserIdAndFromDate(
			Long userId, Date fromDate) {
		initCommonAttributes();
		logger.info("Begin : UserFoodObservationDAOImpl.getAllByUserIdAndFromDate");
		Predicate predicate1 = builder.equal(root.get("user"),userId);
		Predicate predicate2 = builder.greaterThanOrEqualTo(root.<Date>get("time"),fromDate);
		criteria.where(predicate1,predicate2);
		List<UserFoodObservationEntity> results = getResults();
		logger.info("End : UserFoodObservationDAOImpl.getAllByUserIdAndFromDate");
		return results;
	}
	
	@Override
	public Double getFoodCountByUserAndFoodType(UserEntity user, String dateInGMT, String nextDateInGMT, FoodTypeEnum foodType){
		logger.info("Begin : UserFoodObservationDAOImpl.getFoodCountByUserAndFoodType");
		initCommonAttributes();
		Query createNativeQuery;
		createNativeQuery = entityManager
				.createNativeQuery("SELECT "
						+ "SUM(food_serving_size)FROM user_food_observation, food_type ft, food_sub_type fst "
						+ "WHERE user_id = " + user.getId()
						+ " AND food_id = fst.id AND fst.food_type_id = ft.id AND "
						+ "ft.name = \'" + foodType.toString()
						+ "\' AND time >= \'" + dateInGMT 
						+ "\' and time < \'" + nextDateInGMT
						+ "\';"
						);
		
		Object resultList = createNativeQuery.getSingleResult();
		
		logger.info("End : UserFoodObservationDAOImpl.getFoodCountByUserAndFoodType");
		if(resultList != null){
			return Double.parseDouble(resultList.toString());
		}
		return 0.0;
	}
	
	
	//Return food count by user grouped by foodType
	@Override
	public List getFoodCountByUser(UserEntity user, String dateInGMT, String nextDateInGMT){
		logger.info("Begin : UserFoodObservationDAOImpl.getFoodCountByUser");
		initCommonAttributes();
		Query createNativeQuery;
		createNativeQuery = entityManager
				.createNativeQuery("SELECT ft.name, SUM(food_serving_size) AS value FROM user_food_observation ufo, "
						+ "food_sub_type fst, food_type ft "
						+ "WHERE fst.food_type_id = ft.id AND fst.id = ufo.food_id AND "
						+ "ufo.user_id= "+user.getId()
						+ " AND time >= \'" + dateInGMT 
						+ "\' and time < \'" + nextDateInGMT
						+ "\' group by ft.name ;"
						);
		
		List resultList = createNativeQuery.getResultList();
		
		logger.info("End : UserFoodObservationDAOImpl.getFoodCountByUser");
		return resultList;
	}

}
