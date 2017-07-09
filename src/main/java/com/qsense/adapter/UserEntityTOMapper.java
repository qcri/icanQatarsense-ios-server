package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qsense.entity.UserEntity;
import com.qsense.transfer.UserSimpleTO;
import com.qsense.transfer.UserTO;

@Component
public class UserEntityTOMapper implements
		EntityTOMapper<UserEntity, UserTO> {
	
	@Autowired
	private AppGroupEntityTOMapper appGroupEntityTOMapper;
	
	@Autowired
	private RoleEntityTOMapper roleEntityTOMapper;

	@Override
	public UserEntity toEntity(UserTO dto) {
		UserEntity userEntity = new UserEntity();
		if(dto.getAssociatedParticipant() != null && dto.getAssociatedParticipant().getId() != null){
			userEntity.setAssociatedParticipant(toEntity(dto.getAssociatedParticipant()));
		}
		userEntity.setAuthToken(dto.getAuthToken());
		userEntity.setDeviceId(dto.getDeviceId());
		userEntity.setFirstName(dto.getFirstName());
		if(dto.getGroup() != null && dto.getGroup().getId() != null){
			userEntity.setGroup(appGroupEntityTOMapper.toEntity(dto.getGroup()));
		}
		
		userEntity.setId(dto.getId());
		userEntity.setLastName(dto.getLastName());
		userEntity.setPassword(dto.getPassword());
		if(dto.getRole() != null && dto.getRole().getId() != null){
			userEntity.setRole(roleEntityTOMapper.toEntity(dto.getRole()));
		}
		userEntity.setUserName(dto.getUserName());
		return userEntity;
	}

	@Override
	public UserTO toDTO(UserEntity entity) {
		UserTO userTO = new UserTO();
		if(entity.getAssociatedParticipant() != null){
			userTO.setAssociatedParticipant(toDTO(entity.getAssociatedParticipant()));
		}
		userTO.setAuthToken(entity.getAuthToken());
		userTO.setDeviceId(entity.getDeviceId());
		userTO.setFirstName(entity.getFirstName());
		if(entity.getGroup() != null){
			userTO.setGroup(appGroupEntityTOMapper.toDTO(entity.getGroup()));
		}
		
		userTO.setId(entity.getId());
		userTO.setLastName(entity.getLastName());
		userTO.setPassword(entity.getPassword());
		if(entity.getRole() != null){
			userTO.setRole(roleEntityTOMapper.toDTO(entity.getRole()));
		}
		userTO.setUserName(entity.getUserName());
		return userTO;
	}

	@Override
	public List<UserEntity> toEntity(Collection<UserTO> dtos) {
		List<UserEntity> userEntities = new ArrayList<UserEntity>();		
		for (UserTO userTO : dtos) {			
			userEntities.add(toEntity(userTO));
		}
		return userEntities;
	}

	@Override
	public List<UserTO> toDTO(Collection<UserEntity> entities) {
		List<UserTO> userTOs = new ArrayList<UserTO>();
		for (UserEntity userEntity : entities) {
			userTOs.add(toDTO(userEntity));
		}
		return userTOs;
	}
	
	public List<UserSimpleTO> toSimpleDTOs(List<UserEntity> entities) {
		List<UserSimpleTO> userSimpleTOs = new ArrayList<UserSimpleTO>();
		for (UserEntity e: entities) {
			userSimpleTOs.add(toSimpleDTO(e));
		}
		return userSimpleTOs;
	}
	
	public UserSimpleTO toSimpleDTO(UserEntity entity) {
		UserSimpleTO userSimpleTO = new UserSimpleTO();
		userSimpleTO.setId(entity.getId());
		userSimpleTO.setFirstName(entity.getFirstName());
		if(entity.getGroup() != null){
			userSimpleTO.setGroupName(entity.getGroup().getName());
		}		
		userSimpleTO.setLastName(entity.getLastName());
		if(entity.getRole() != null){
			userSimpleTO.setRoleName(entity.getRole().getName());
		}		
		userSimpleTO.setUserName(entity.getUserName());
		if(entity.getAssociatedParticipant() != null){
			userSimpleTO.setAssocParticipantName(entity.getAssociatedParticipant().getUserName());
		}
		return userSimpleTO;
	}
		
	

	

	
	
	
}
