package com.qsense.dao;

import com.qsense.entity.CategoryEntity;
import com.qsense.util.CategoryTypeEnum;

/**
 * Role DAO
 * 
 * @author Kushal
 */
public interface CategoryDAO extends CommonDAO<CategoryEntity> {
	CategoryEntity getByName(CategoryTypeEnum categoryName);
}
