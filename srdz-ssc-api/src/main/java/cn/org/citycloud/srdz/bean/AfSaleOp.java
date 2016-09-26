package cn.org.citycloud.srdz.bean;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AfSaleOp {

	@NotBlank(message="处理结果不能为空")
	@ApiModelProperty(value="处理结果",required=true)
	public String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
