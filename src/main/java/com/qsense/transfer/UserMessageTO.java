package com.qsense.transfer;

import java.util.Date;

import com.qsense.util.MessageStatusEnum;


/**
 * @author Kushalkant
 *
 */
public class UserMessageTO extends MessageTO {	
	
	private static final long serialVersionUID = -4897389971601014682L;

	private boolean isRead;	
		
	private Date readTime;
	
	private MessageStatusEnum messageStatus;

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

	/**
	 * @return the isRead
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public MessageStatusEnum getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatusEnum messageStatus) {
		this.messageStatus = messageStatus;
	}

}
