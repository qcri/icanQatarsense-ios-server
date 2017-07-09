package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qsense.entity.SubCategoryEntity;
import com.qsense.transfer.SubCategoryTO;


@Component
public class SubCategoryEntityTOMapper implements
		EntityTOMapper<SubCategoryEntity, SubCategoryTO> {

	@Autowired
	private ObservationUnitEntityTOMapper observationUnitEntityTOMapper;
	
	@Override
	public SubCategoryEntity toEntity(SubCategoryTO dto) {
		SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
		subCategoryEntity.setId(dto.getId());
		subCategoryEntity.setName(dto.getName());
		subCategoryEntity.setDisplayName(dto.getDisplayName());
		subCategoryEntity.setObservationUnit(observationUnitEntityTOMapper.toEntity(dto.getObservationUnitTO()));
		return subCategoryEntity;
	}

	@Override
	public SubCategoryTO toDTO(SubCategoryEntity entity) {
		SubCategoryTO subCategoryTO = new SubCategoryTO();
		subCategoryTO.setId(entity.getId());
		subCategoryTO.setName(entity.getName());
		subCategoryTO.setDisplayName(entity.getDisplayName());
		subCategoryTO.setObservationUnitTO(observationUnitEntityTOMapper.toDTO(entity.getObservationUnit()));
		return subCategoryTO;
	}

	@Override
	public List<SubCategoryEntity> toEntity(Collection<SubCategoryTO> dtos) {
		List<SubCategoryEntity> subCategoryEntities = new ArrayList<SubCategoryEntity>();		
		for (SubCategoryTO subCategoryTO : dtos) {			
			subCategoryEntities.add(toEntity(subCategoryTO));
		}
		return subCategoryEntities;
	}

	@Override
	public List<SubCategoryTO> toDTO(Collection<SubCategoryEntity> entities) {
		List<SubCategoryTO> subCategoryTOs = new ArrayList<SubCategoryTO>();
		for (SubCategoryEntity subCategoryEntity : entities) {
			subCategoryTOs.add(toDTO(subCategoryEntity));
		}
		return subCategoryTOs;
	}
}
