package com.qsense.dao.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.qsense.dao.SubCategoryDAO;
import com.qsense.entity.CategoryEntity;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * SubCategory Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("SubCategoryDAO")
public class SubCategoryDAOImpl extends CommonDAOImpl<SubCategoryEntity> implements SubCategoryDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<SubCategoryEntity> getModelClass() {
		return SubCategoryEntity.class;
	}
	
	public List<SubCategoryEntity> getAllSubCategoriesByCategory(CategoryEntity categoryEntity)
	{
		initCommonAttributes();
		logger.info("Begin : SubCategoryDAOImpl.getAllSubCategoriesByCategory");
		Predicate predicate1 = builder.equal(root.get("category"),categoryEntity);
		criteria.where(predicate1);
		criteria.orderBy(builder.asc(root.get("sortOrder")));
		List<SubCategoryEntity> results = getResults();
		logger.info("End : SubCategoryDAOImpl.getAllSubCategoriesByCategory");
		return results;
	}

	@Override
	public SubCategoryEntity getByName(String string) {
		initCommonAttributes();
		logger.info("Begin : SubCategoryDAOImpl.getByName");
		Predicate predicate1 = builder.equal(root.get("name"),string);
		criteria.where(predicate1);
		SubCategoryEntity results = getSingleResult();
		logger.info("End : SubCategoryDAOImpl.getByName");
		return results;
	}

	@Override
	public List<SubCategoryEntity> getAllIsManuallyAllowed() {
		initCommonAttributes();
		logger.info("Begin : SubCategoryDAOImpl.getByName");
		Predicate predicate1 = builder.equal(root.get("isManuallyAllowed"),1);
		criteria.where(predicate1);
		List<SubCategoryEntity> results = getResults();
		logger.info("End : SubCategoryDAOImpl.getByName");
		return results;
	}

}
