package cn.org.citycloud.srdz.bean;

import java.io.Serializable;

/**
 * Token
 * 
 * @author lanbo
 *
 */
public class UserToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5259132293621138195L;

	/**
	 * Token
	 */
	private String token;

	
	private int userId;
	
	private String userName;
	
	private int serviceId;
	
	private String userTrueName;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getUserTrueName() {
		return userTrueName;
	}

	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}

	

}
