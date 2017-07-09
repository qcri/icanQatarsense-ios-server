package com.qsense.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.adapter.FoodTypeEntityTOMapper;
import com.qsense.dao.FoodTypeDAO;
import com.qsense.entity.FoodTypeEntity;
import com.qsense.service.FoodTypeService;
import com.qsense.transfer.FoodTypeTO;
import com.qsense.util.logger.QSenseLogger;


@Transactional
@Service("FoodTypeService")
public class FoodTypeServiceImpl implements FoodTypeService{
	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private FoodTypeDAO foodTypeDAO;
	
	@Autowired
	private FoodTypeEntityTOMapper foodTypeEntityTOMapper;
	
	@Override
	public List<FoodTypeTO> getAll() {
		List<FoodTypeEntity> foodTypeList = foodTypeDAO.getAll();
		return foodTypeEntityTOMapper.toDTO(foodTypeList);
	}

}
