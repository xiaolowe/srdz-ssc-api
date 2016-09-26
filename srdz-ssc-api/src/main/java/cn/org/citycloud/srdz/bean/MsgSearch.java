package cn.org.citycloud.srdz.bean;

import cn.org.citycloud.srdz.bean.page.Page;
import io.swagger.annotations.ApiModelProperty;

public class MsgSearch extends Page {

	@ApiModelProperty(value="开始时间")
	private String stime="";
	
	@ApiModelProperty(value="结束时间")
	private String etime="";
	
	@ApiModelProperty(value="消息类型")
	private int messageType=-1;
	
	@ApiModelProperty(value="消息关键字")
	private String messageKey="";

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

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
}
