package cn.org.citycloud.srdz.bean;

import cn.org.citycloud.srdz.bean.page.Page;
import io.swagger.annotations.ApiModelProperty;

public class OrderSearch extends Page {
	
	@ApiModelProperty(value="订单号")
	private String orderId="";
	
	@ApiModelProperty(value="下单人姓名")
	private String userTruename="";
	
	@ApiModelProperty(value="订单状态")
	private int orderStatus=-1;
	
	@ApiModelProperty(value="开始时间")
	private String stime="";
	
	@ApiModelProperty(value="结束时间")
	private String etime="";
	
	@ApiModelProperty(value="订单类型,1为普通订单,2为分销订单")
	private int orderType = 0;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserTruename() {
		return userTruename;
	}

	public void setUserTruename(String userTruename) {
		this.userTruename = userTruename;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

}
