package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qsense.entity.ObservationUnitEntity;
import com.qsense.transfer.ObservationUnitTO;

@Component
public class ObservationUnitEntityTOMapper implements
		EntityTOMapper<ObservationUnitEntity, ObservationUnitTO> {

	@Override
	public ObservationUnitEntity toEntity(ObservationUnitTO dto) {
		ObservationUnitEntity observationUnitEntity = new ObservationUnitEntity();
		observationUnitEntity.setName(dto.getName());
		observationUnitEntity.setDisplayName(dto.getDisplayName());
		return observationUnitEntity;
	}

	@Override
	public ObservationUnitTO toDTO(ObservationUnitEntity entity) {
		ObservationUnitTO observationUnitTO = new ObservationUnitTO();
		observationUnitTO.setName(entity.getName());
		observationUnitTO.setDisplayName(entity.getDisplayName());
		return observationUnitTO;
	}

	@Override
	public List<ObservationUnitEntity> toEntity(Collection<ObservationUnitTO> dtos) {
		List<ObservationUnitEntity> entities = new ArrayList<ObservationUnitEntity>();		
		for (ObservationUnitTO observationUnitTO : dtos) {			
			entities.add(toEntity(observationUnitTO));
		}
		return entities;
	}

	@Override
	public List<ObservationUnitTO> toDTO(Collection<ObservationUnitEntity> entities) {
		List<ObservationUnitTO> observationUnitTOs = new ArrayList<ObservationUnitTO>();
		for (ObservationUnitEntity observationUnitEntity : entities) {
				observationUnitTOs.add(toDTO(observationUnitEntity));
			
		}
		return observationUnitTOs;
	}
	
}
