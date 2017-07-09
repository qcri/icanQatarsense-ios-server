package com.qsense.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.adapter.AppGroupEntityTOMapper;
import com.qsense.dao.AppGroupDAO;
import com.qsense.entity.AppGroupEntity;
import com.qsense.service.GroupService;
import com.qsense.transfer.AppGroupTO;
import com.qsense.util.logger.QSenseLogger;


@Transactional
@Service("GroupService")
public class GroupServiceImpl implements GroupService{
	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private AppGroupDAO groupDAO;
	
	@Autowired
	private AppGroupEntityTOMapper appGroupEntityTOMapper;
			
	@Override
	public List<AppGroupTO> getAll() {
		List<AppGroupEntity> all = groupDAO.getAll();
		return appGroupEntityTOMapper.toDTO(all);		
	}
	
}
