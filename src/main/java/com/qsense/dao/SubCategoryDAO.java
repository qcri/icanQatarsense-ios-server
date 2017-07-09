package com.qsense.dao;

import java.util.List;

import com.qsense.entity.CategoryEntity;
import com.qsense.entity.SubCategoryEntity;

/**
 * SubCategory DAO
 * 
 * @author Kushal
 */
public interface SubCategoryDAO extends CommonDAO<SubCategoryEntity> {
	
	public List<SubCategoryEntity> getAllSubCategoriesByCategory(CategoryEntity categoryEntity);

	public SubCategoryEntity getByName(String string);

	public List<SubCategoryEntity> getAllIsManuallyAllowed();
	
}
