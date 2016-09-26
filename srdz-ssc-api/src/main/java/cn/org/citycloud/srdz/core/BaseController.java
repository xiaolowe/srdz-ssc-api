package cn.org.citycloud.srdz.core;

import cn.org.citycloud.srdz.bean.UserToken;

/**
 * 控制器基类
 * 
 * @author lanbo
 *
 */
public class BaseController {
	
	private String token;
	
	private UserToken userToken;
	
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

	public UserToken getUserToken() {
		return userToken;
	}

	public void setUserToken(UserToken userToken) {
		this.userToken = userToken;
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
