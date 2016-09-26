package cn.org.citycloud.srdz.bean;

import cn.org.citycloud.srdz.bean.page.Page;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

public class SupplierSearch extends Page{
	
	@ApiModelProperty(name="等级")
	private int level=0;
	
	@ApiModelProperty(name="状态")
	private int status=-1;
	
	@ApiModelProperty(name="供应商名称")
	private String supplierName="";

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
