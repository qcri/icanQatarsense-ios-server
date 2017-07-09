package com.qsense.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.adapter.RoleEntityTOMapper;
import com.qsense.dao.RoleDAO;
import com.qsense.entity.RoleEntity;
import com.qsense.service.RoleService;
import com.qsense.transfer.RoleTO;
import com.qsense.util.logger.QSenseLogger;


@Transactional
@Service("RoleService")
public class RoleServiceImpl implements RoleService{
	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private RoleEntityTOMapper roleEntityTOMapper;
			
	@Override
	public List<RoleTO> getAll() {
		List<RoleEntity> all = roleDAO.getAll();		
		return roleEntityTOMapper.toDTO(all);		
	}
	
	@Override
	public List<RoleTO> getAllWithoutAdmin() {
		List<RoleEntity> all = roleDAO.getAll();		
		return roleEntityTOMapper.toDTOWithoutAdmin(all);		
	}
	
}
