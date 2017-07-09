package com.qsense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qsense.util.DeviceTypeEnum;

/**
 * Class responsible for managing user session
 * 
 * @author Kushal
 */

@Entity
@Table(name = "user_session")
public class UserSessionEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -277580361750969963L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@Column(name = "login_time")
	private Date loginTime;

	@Column(name = "logout_time")
	private Date logoutTime;
			
	@Column(name = "timezone")
	private String timezone;

	@Column(name = "permanent_device_id")
	private String permanentDeviceId;
	
	@Column(name = "device_id")
	private String deviceId;
	
	@Column(name = "auth_token")
	private String authToken;

	@Column(name = "is_step_count_sensor_available", nullable=false)
	private Boolean isStepCountSensorAvailable=true;
	
	@ManyToOne
	@JoinColumn(name = "device_type_id")
	private DeviceTypeEntity deviceType;
	
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
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the logoutTime
	 */
	public Date getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @param logoutTime the logoutTime to set
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the permanentDeviceId
	 */
	public String getPermanentDeviceId() {
		return permanentDeviceId;
	}

	/**
	 * @param permanentDeviceId the permanentDeviceId to set
	 */
	public void setPermanentDeviceId(String permanentDeviceId) {
		this.permanentDeviceId = permanentDeviceId;
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
	 * @return the isStepCountSensorAvailable
	 */
	public Boolean getIsStepCountSensorAvailable() {
		return isStepCountSensorAvailable;
	}

	/**
	 * @param isStepCountSensorAvailable the isStepCountSensorAvailable to set
	 */
	public void setIsStepCountSensorAvailable(Boolean isStepCountSensorAvailable) {
		this.isStepCountSensorAvailable = isStepCountSensorAvailable;
	}

	public DeviceTypeEntity getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceTypeEntity deviceType) {
		this.deviceType = deviceType;
	}
}
