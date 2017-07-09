package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qsense.entity.RoleEntity;
import com.qsense.transfer.RoleTO;
import com.qsense.util.RoleEnum;

@Component
public class RoleEntityTOMapper implements
		EntityTOMapper<RoleEntity, RoleTO> {

	@Override
	public RoleEntity toEntity(RoleTO dto) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setName(dto.getName());
		roleEntity.setId(dto.getId());
		roleEntity.setDescription(dto.getDescription());
		return roleEntity;
	}

	@Override
	public RoleTO toDTO(RoleEntity entity) {
		RoleTO roleTO = new RoleTO();
		roleTO.setName(entity.getName());
		roleTO.setId(entity.getId());
		roleTO.setDescription(entity.getDescription());
		return roleTO;
	}

	@Override
	public List<RoleEntity> toEntity(Collection<RoleTO> dtos) {
		List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();		
		for (RoleTO roleTO : dtos) {			
			roleEntities.add(toEntity(roleTO));
		}
		return roleEntities;
	}

	@Override
	public List<RoleTO> toDTO(Collection<RoleEntity> entities) {
		List<RoleTO> roleTOs = new ArrayList<RoleTO>();
		for (RoleEntity roleEntity : entities) {
				roleTOs.add(toDTO(roleEntity));
			
		}
		return roleTOs;
	}
	
	public List<RoleTO> toDTOWithoutAdmin(Collection<RoleEntity> entities) {
		List<RoleTO> roleTOs = new ArrayList<RoleTO>();
		for (RoleEntity roleEntity : entities) {
			if(!(roleEntity.getName()==RoleEnum.ADMIN) ){
				roleTOs.add(toDTO(roleEntity));
			}
			
		}
		return roleTOs;
	}
}
