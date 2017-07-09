package com.qsense.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.dao.CategoryDAO;
import com.qsense.dao.SubCategoryDAO;
import com.qsense.entity.CategoryEntity;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.service.SubCategoryService;
import com.qsense.transfer.SubCategoryTO;
import com.qsense.util.CategoryTypeEnum;
import com.qsense.util.logger.QSenseLogger;


@Transactional
@Service("SubCategoryService")
public class SubCategoryServiceImpl implements SubCategoryService{
	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private SubCategoryDAO subCategoryDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<SubCategoryTO> getAllAdminDashboardVisible() {
		List<SubCategoryTO> subCategoryTOs= new ArrayList<SubCategoryTO>();
		List<SubCategoryEntity> subCatergoryEntities = new ArrayList<SubCategoryEntity>();
		SubCategoryTO subCategoryTO;
		SubCategoryEntity subCategoryEntityForStepCount = subCategoryDAO.getByName("STEP_COUNT");
		subCatergoryEntities.add(subCategoryEntityForStepCount);
		CategoryEntity categoryEntity = categoryDAO.getByName(CategoryTypeEnum.ACTIVITY);
		subCatergoryEntities.addAll(subCategoryDAO.getAllSubCategoriesByCategory(categoryEntity));

		//Mapping subcategory entities to TO
		for (SubCategoryEntity subCategoryEntity : subCatergoryEntities) {
			subCategoryTO = new SubCategoryTO();
			subCategoryTO.setId(subCategoryEntity.getId());
			subCategoryTO.setName(subCategoryEntity.getDisplayName()); //Added display name into name field
			subCategoryTOs.add(subCategoryTO);
		}
		
		
		return subCategoryTOs;
	}

	@Override
	public List<SubCategoryTO> getAllIsManuallyAllowed() {
		List<SubCategoryTO> subCategoryTOs= new ArrayList<SubCategoryTO>();
		
		List<SubCategoryEntity> subCatergoryEntities = new ArrayList<SubCategoryEntity>();
		
		subCatergoryEntities = subCategoryDAO.getAllIsManuallyAllowed();
		
		SubCategoryTO subCategoryTO;
		
		for (SubCategoryEntity subCategoryEntity : subCatergoryEntities) {
			subCategoryTO = new SubCategoryTO();
			subCategoryTO.setId(subCategoryEntity.getId());
			subCategoryTO.setName(subCategoryEntity.getName());
			subCategoryTOs.add(subCategoryTO);
		}
		
		return subCategoryTOs;
	}
}
