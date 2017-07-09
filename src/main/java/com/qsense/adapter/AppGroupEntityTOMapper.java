package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qsense.entity.AppGroupEntity;
import com.qsense.transfer.AppGroupTO;

@Component
public class AppGroupEntityTOMapper implements
		EntityTOMapper<AppGroupEntity, AppGroupTO> {

	@Override
	public AppGroupEntity toEntity(AppGroupTO dto) {
		AppGroupEntity appGroupEntity = new AppGroupEntity();
		appGroupEntity.setName(dto.getName());
		appGroupEntity.setId(dto.getId());
		appGroupEntity.setDescription(dto.getDescription());
		return appGroupEntity;
	}

	@Override
	public AppGroupTO toDTO(AppGroupEntity entity) {
		AppGroupTO appGroupTO = new AppGroupTO();
		appGroupTO.setName(entity.getName());
		appGroupTO.setId(entity.getId());
		appGroupTO.setDescription(entity.getDescription());
		return appGroupTO;
	}

	@Override
	public List<AppGroupEntity> toEntity(Collection<AppGroupTO> dtos) {
		List<AppGroupEntity> appGroupEntities = new ArrayList<AppGroupEntity>();		
		for (AppGroupTO appGroupTO : dtos) {			
			appGroupEntities.add(toEntity(appGroupTO));
		}
		return appGroupEntities;
	}

	@Override
	public List<AppGroupTO> toDTO(Collection<AppGroupEntity> entities) {
		List<AppGroupTO> appGroupTOs = new ArrayList<AppGroupTO>();
		for (AppGroupEntity appGroupEntity : entities) {
			appGroupTOs.add(toDTO(appGroupEntity));
		}
		return appGroupTOs;
	}
		
	

	

	
	
	
}
