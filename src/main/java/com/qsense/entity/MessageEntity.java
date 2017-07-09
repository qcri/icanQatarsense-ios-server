
package com.qsense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





/**
 * Class responsible for managing messages
 * 
 * @author Neeraj
 */

@Entity
@Table(name = "message")
public class MessageEntity  extends BaseEntity {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6342449685197281373L;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;	
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private AppGroupEntity group;	

	@Column(name= "title")
	private String title ;
	
	@Column(name = "content")
	private String content;

	@Column(name = "posted_at")
	private Date postedAt;
				
	@Column(name = "posted_by")
	private Long postedBy;

	/**
	 * @return the role
	 */
	public RoleEntity getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleEntity role) {
		this.role = role;
	}

	/**
	 * @return the group
	 */
	public AppGroupEntity getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(AppGroupEntity group) {
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
	public Long getPostedBy() {
		return postedBy;
	}

	/**
	 * @param postedBy the postedBy to set
	 */
	public void setPostedBy(Long postedBy) {
		this.postedBy = postedBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageEntity [role=" + role + ", group=" + group + ", title="
				+ title + ", content=" + content + ", postedAt=" + postedAt
				+ ", postedBy=" + postedBy + "]";
	}
}
