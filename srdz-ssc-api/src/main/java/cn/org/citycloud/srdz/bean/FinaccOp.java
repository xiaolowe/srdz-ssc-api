package cn.org.citycloud.srdz.bean;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class FinaccOp {
	
	@NotBlank(message="开户行不能为空")
	@ApiModelProperty(value="开户行",required=true)
	private String accountBank;
	
	@NotBlank(message="卡号不能为空")
	@ApiModelProperty(value="卡号",required=true)
	private String cardNo;
	
	@NotBlank(message="持卡人不能为空")
	@ApiModelProperty(value="持卡人",required=true)
	private String cardOwner;

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}

}
