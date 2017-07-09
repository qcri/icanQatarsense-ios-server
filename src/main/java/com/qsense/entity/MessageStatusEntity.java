
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.qsense.util.MessageStatusEnum;


/**
 * Message Status Entity
 * 
 * @author Kushal
 */

@Entity
@Table(name = "message_status")
public class MessageStatusEntity extends BaseEntity{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 369854688944378246L;
	
	@Column(name= "name")
	@Enumerated(EnumType.STRING)
	private MessageStatusEnum name;

	public MessageStatusEnum getName() {
		return name;
	}

	public void setName(MessageStatusEnum name) {
		this.name = name;
	}


}
