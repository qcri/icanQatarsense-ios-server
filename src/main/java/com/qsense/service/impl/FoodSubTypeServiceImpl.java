package com.qsense.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.adapter.FoodSubTypeEntityTOMapper;
import com.qsense.dao.FoodSubTypeDAO;
import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.FoodTypeEntity;
import com.qsense.service.FoodSubTypeService;
import com.qsense.transfer.FoodSubTypeTO;
import com.qsense.util.logger.QSenseLogger;


@Transactional
@Service("FoodSubTypeService")
public class FoodSubTypeServiceImpl implements FoodSubTypeService{
	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private FoodSubTypeDAO foodSubTypeDAO;
	
	@Autowired
	private FoodSubTypeEntityTOMapper foodSubTypeEntityTOMapper;
	
	@Override
	public List<FoodSubTypeTO> getAll() {
		List<FoodSubTypeEntity> foodSubTypeList = foodSubTypeDAO.getAll();
		try{
			return foodSubTypeEntityTOMapper.toDTO(foodSubTypeList);
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public List<FoodSubTypeTO> getAllByFoodTypeId(Long foodTypeId) {
		FoodTypeEntity foodType = new FoodTypeEntity();
		foodType.setId(foodTypeId);
		List<FoodSubTypeEntity> foodSubTypeList = foodSubTypeDAO.getAllByFoodType(foodType);
		return foodSubTypeEntityTOMapper.toDTO(foodSubTypeList);
	}

	@Override
	public FoodSubTypeTO getById(Long id) {
		FoodSubTypeEntity foodSubType = foodSubTypeDAO.getById(id);
		return foodSubTypeEntityTOMapper.toDTO(foodSubType);
	}

}
