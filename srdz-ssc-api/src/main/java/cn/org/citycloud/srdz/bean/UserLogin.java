package cn.org.citycloud.srdz.bean;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class UserLogin {
	
	@NotBlank(message="登录名不能为空")
	@ApiModelProperty(value="登录名称",required=true)
	private String userName;
	
	@NotBlank(message="登录密码不能为空")
	@ApiModelProperty(value="登录密码",required=true)
	private String userPwd;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
