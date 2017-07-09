package com.qsense.transfer;


public class UserTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6334238487913301711L;
	
	private RoleTO role;	
		
	private AppGroupTO group;	
	
	private String userName ;
		
	private String firstName;

	private String lastName;
			
	private String password;
	
	private String deviceId;
	
	private String authToken;
	
	private UserTO associatedParticipant;

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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * @return the associatedParticipant
	 */
	public UserTO getAssociatedParticipant() {
		return associatedParticipant;
	}

	/**
	 * @param associatedParticipant the associatedParticipant to set
	 */
	public void setAssociatedParticipant(UserTO associatedParticipant) {
		this.associatedParticipant = associatedParticipant;
	}

	public String getName() {
		return userName;
	}
	
}
