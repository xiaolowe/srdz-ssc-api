package cn.org.citycloud.srdz.bean;

import cn.org.citycloud.srdz.bean.page.Page;
import io.swagger.annotations.ApiModelProperty;

public class AfterSaleSearch extends Page {
	

	@ApiModelProperty(value="订单号")
	private String orderId="";
	
	@ApiModelProperty(value="供应商名称")
	private String supplierName="";
	
	@ApiModelProperty(value="用户名称")
	private String memberName="";

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
