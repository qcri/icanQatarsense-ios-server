package com.qsense.transfer;

import com.qsense.util.RoleEnum;


public class UserSimpleTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6334238487913301711L;
	
	private RoleEnum roleName;	
		
	private String groupName;	
	
	private String userName ;
		
	private String firstName;

	private String lastName;
	
	private String assocParticipantName;

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAssocParticipantName() {
		return assocParticipantName;
	}

	public void setAssocParticipantName(String assocParticipantName) {
		this.assocParticipantName = assocParticipantName;
	}

	public RoleEnum getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleEnum roleName) {
		this.roleName = roleName;
	}


}
