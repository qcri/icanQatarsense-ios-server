
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





/**
 * Class responsible for managing user information
 * 
 * @author Neeraj
 */

@Entity
@Table(name = "user")
public class UserEntity extends CommonEntity {
		
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
	
	@ManyToOne
	@JoinColumn(name = "oryx_id")
	private OryxEntity oryx;

	@Column(name= "coins")
	private Long coins;

	@Column(name= "username")
	private String userName ;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
			
	@Column(name = "password")
	private String password;
	
	@Column(name = "device_id")
	private String deviceId;
	
	@Column(name = "permanent_device_id")
	private String permanentDeviceId;
	
	@Column(name = "auth_token")
	private String authToken;
	
	@ManyToOne
	@JoinColumn(name = "device_type_id")
	private DeviceTypeEntity deviceType;
	
	@ManyToOne
	@JoinColumn(name = "associated_participant_id")
	private UserEntity associatedParticipant;

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
	 * @return the associatedParticipant
	 */
	public UserEntity getAssociatedParticipant() {
		return associatedParticipant;
	}

	/**
	 * @param associatedParticipant the associatedParticipant to set
	 */
	public void setAssociatedParticipant(UserEntity associatedParticipant) {
		this.associatedParticipant = associatedParticipant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserEntity [role=" + role + ", group=" + group + ", userName="
				+ userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + "]";
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getPermanentDeviceId() {
		return permanentDeviceId;
	}

	public void setPermanentDeviceId(String permanentDeviceId) {
		this.permanentDeviceId = permanentDeviceId;
	}

	public DeviceTypeEntity getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceTypeEntity deviceType) {
		this.deviceType = deviceType;
	}
	
	public OryxEntity getOryx() {
		return oryx;
	}

	public void setOryx(OryxEntity oryx) {
		this.oryx = oryx;
	}

	public Long getCoins() {
		return coins;
	}

	public void setCoins(Long coins) {
		this.coins = coins;
	}

}
