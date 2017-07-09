
package com.qsense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





/**
 * Class responsible for managing user messages
 * 
 * @author Neeraj
 */

@Entity
@Table(name = "user_message")
public class UserMessageEntity extends BaseEntity {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6342449685197281373L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;	
	
	@ManyToOne
	@JoinColumn(name = "message_id")
	private MessageEntity message;
	
	@ManyToOne
	@JoinColumn(name = "message_status_id")
	private MessageStatusEntity messageStatus;
	
	@Column(name= "is_deleted")
	private Boolean isDeleted ;

	@Column(name= "is_read")
	private Boolean isRead ;
	
	@Column(name = "read_time")
	private Date readTime;

	/**
	 * @return the user
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}

	/**
	 * @return the message
	 */
	public MessageEntity getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(MessageEntity message) {
		this.message = message;
	}

	/**
	 * @return the isRead
	 */
	public Boolean getIsRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the readTime
	 */
	public Date getReadTime() {
		return readTime;
	}

	/**
	 * @param readTime the readTime to set
	 */
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserMessageEntity [user=" + user + ", message=" + message
				+ ", isRead=" + isRead + ", readTime=" + readTime + "]";
	}

	public MessageStatusEntity getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatusEntity messageStatus) {
		this.messageStatus = messageStatus;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
