package com.qsense.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qsense.entity.MessageEntity;
import com.qsense.service.UserService;
import com.qsense.transfer.MessageSimpleTO;
import com.qsense.transfer.MessageTO;
import com.qsense.util.TimeUtils;

@Component
public class MessageEntityTOMapper implements
		EntityTOMapper<MessageEntity, MessageTO> {
	
	@Autowired
	private AppGroupEntityTOMapper appGroupEntityTOMapper;
	
	@Autowired
	private UserEntityTOMapper userEntityTOMapper;
	
	@Autowired
	private RoleEntityTOMapper roleEntityTOMapper;
	
	@Autowired
	private UserService userService;

	@Override
	public MessageEntity toEntity(MessageTO dto) {
		MessageEntity messageEntity = new MessageEntity();		
		messageEntity.setContent(dto.getContent());
		messageEntity.setTitle(dto.getTitle());
		if(dto.getGroup() != null){
			messageEntity.setGroup(appGroupEntityTOMapper.toEntity(dto.getGroup()));
		}
		if(dto.getRole() != null){
			messageEntity.setRole(roleEntityTOMapper.toEntity(dto.getRole()));
		}
		messageEntity.setId(dto.getId());		
		return messageEntity;
	}

	@Override
	public MessageTO toDTO(MessageEntity entity) {
		MessageTO messageTO = new MessageTO();	
		messageTO.setContent(entity.getContent());
		if(entity.getGroup() != null){
			messageTO.setGroup(appGroupEntityTOMapper.toDTO(entity.getGroup()));
		}
		messageTO.setId(entity.getId());
		messageTO.setPostedAt(entity.getPostedAt());
		if(entity.getPostedBy() != null){
			messageTO.setPostedBy(userService.getUserById(entity.getPostedBy()).getUserName());
		}
		if(entity.getRole() != null){
			messageTO.setRole(roleEntityTOMapper.toDTO(entity.getRole()));
		}
		messageTO.setTitle(entity.getTitle());	
		return messageTO;
	}

	@Override
	public List<MessageEntity> toEntity(Collection<MessageTO> dtos) {
		List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();		
		for (MessageTO messageTO : dtos) {			
			messageEntities.add(toEntity(messageTO));
		}
		return messageEntities;
	}

	@Override
	public List<MessageTO> toDTO(Collection<MessageEntity> entities) {
		List<MessageTO> messageTOs = new ArrayList<MessageTO>();
		for (MessageEntity messageEntity : entities) {
			messageTOs.add(toDTO(messageEntity));
		}
		return messageTOs;
	}
	
	public MessageSimpleTO toSimpleDTO(MessageEntity entity) {
		MessageSimpleTO messageSimpleTO = new MessageSimpleTO();
		messageSimpleTO.setId(entity.getId());
		messageSimpleTO.setContent(entity.getContent());
		messageSimpleTO.setGroupName(entity.getGroup().getName());
		messageSimpleTO.setPostedAt(TimeUtils.parseFormattedDate(entity.getPostedAt(), "MM/dd/yy HH:mm"));
		messageSimpleTO.setPostedBy(userService.getUserById(entity.getPostedBy()).getUserName());
		messageSimpleTO.setRoleName(entity.getRole().getName());
		messageSimpleTO.setTitle(entity.getTitle());		
		return messageSimpleTO;
	}
	
	public List<MessageSimpleTO> toSimpleDTOs(List<MessageEntity> entities) {
		List<MessageSimpleTO> messageSimpleTOs = new ArrayList<MessageSimpleTO>();
		for (MessageEntity e: entities) {
			messageSimpleTOs.add(toSimpleDTO(e));
		}
		return messageSimpleTOs;
	}
		
	

	

	
	
	
}
