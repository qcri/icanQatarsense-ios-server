package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.CategoryDAO;
import com.qsense.entity.CategoryEntity;
import com.qsense.util.CategoryTypeEnum;
import com.qsense.util.logger.QSenseLogger;


/**
 * Category Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("CategoryDAO")
public class CategoryDAOImpl extends CommonDAOImpl<CategoryEntity> implements CategoryDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<CategoryEntity> getModelClass() {
		return CategoryEntity.class;
	}

	@Override
	public CategoryEntity getByName(CategoryTypeEnum categoryName) {
		logger.info("Begin : CategoryDAOImpl.getByName");
		initCommonAttributes();
		criteria.where(builder.equal(root.get("name"), categoryName));
		CategoryEntity categoryEntity = getSingleResult();		
		logger.info("End : CategoryDAOImpl.getByName");
		return categoryEntity;
	}
}
