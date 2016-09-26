package cn.org.citycloud.srdz.bean;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class PwdForget {
	
	@NotBlank(message="手机号不能为空")
	@ApiModelProperty(value="手机号",required=true)
	private String userName;
	
	@NotBlank(message="验证码不能为空")
	@ApiModelProperty(value="验证码,测试验证码:888888",required=true)
	private String sms;
	
	@NotBlank(message="新密码不能为空")
	@ApiModelProperty(value="新密码",required=true)
	private String pwd;
	
	@NotBlank(message="确认密码不能为空")
	@ApiModelProperty(value="确认密码",required=true)
	private String pwdConfirm;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdConfirm() {
		return pwdConfirm;
	}

	public void setPwdConfirm(String pwdConfirm) {
		this.pwdConfirm = pwdConfirm;
	}
}
