package cn.org.citycloud.srdz.bean;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class PwdModify {
	
	@NotBlank(message="原密码不能为空")
	@ApiModelProperty(value="原密码",required=true)
	private String pwdOrigin;
	
	@NotBlank(message="新密码不能为空")
	@ApiModelProperty(value="新密码",required=true)
	private String pwd;
	
	@NotBlank(message="确认密码不能为空")
	@ApiModelProperty(value="确认密码",required=true)
	private String pwdConfirm;

	public String getPwdOrigin() {
		return pwdOrigin;
	}

	public void setPwdOrigin(String pwdOrigin) {
		this.pwdOrigin = pwdOrigin;
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
