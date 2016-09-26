package cn.org.citycloud.srdz.bean;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class CashOp {
	
	@ApiModelProperty(value="服务中心ID",required=true)
	private int serviceCenterId;
	
	@ApiModelProperty(value="申请金额",required=true)
	private BigDecimal applyMoney;

	public int getServiceCenterId() {
		return serviceCenterId;
	}

	public void setServiceCenterId(int serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

}
