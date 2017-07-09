
package com.qsense.transfer;

import java.util.Date;


public class MessageTO extends CommonTO {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4478951024919510243L;
	
	private RoleTO role;	
		
	private AppGroupTO group;	
	
	private String title ;
		
	private String content;
	
	private Date postedAt;
				
	private String postedBy;

	/**
	 * @return the role
	 */
	public RoleTO getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleTO role) {
		this.role = role;
	}

	/**
	 * @return the group
	 */
	public AppGroupTO getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(AppGroupTO group) {
		this.group = group;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the postedAt
	 */
	public Date getPostedAt() {
		return postedAt;
	}

	/**
	 * @param postedAt the postedAt to set
	 */
	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}

	/**
	 * @return the postedBy
	 */
	public String getPostedBy() {
		return postedBy;
	}

	/**
	 * @param postedBy the postedBy to set
	 */
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
		
}
