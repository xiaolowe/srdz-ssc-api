package cn.org.citycloud.srdz.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.srdz.bean.CashOp;
import cn.org.citycloud.srdz.bean.FinaccOp;
import cn.org.citycloud.srdz.bean.page.Page;
import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.service.CashService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags="财务管理")
public class CashController extends BaseController {
	
	@Autowired
	private CashService cashService;
	
	@RequestMapping(value="/finaccs",method=RequestMethod.GET)
	@ApiOperation(value="服务中心的财务信息",notes="服务中心的财务信息",consumes="application/json",produces="application/json")
	@ApiImplicitParams(value={@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),
			@ApiImplicitParam(name="page",value="页码,默认为1",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="pageSize",value="页面大小,默认为10",required=false,dataType="int",paramType="query")})
	public Object getFinaccDetail(@ApiIgnore @Valid Page page){
		
		return cashService.getFinaccDetail(page,this.getServiceId());
	};
	
	
	@RequestMapping(value="/cashs/{cashId}",method=RequestMethod.GET)
	@ApiOperation(value="某条提款详情",notes="某条提款记录详情",consumes="application/json",produces="application/json")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getCashDetail(@ApiParam(name="cashId",value="提款ID",required=true) @PathVariable int cashId){
		return cashService.getCashDetail(cashId);
	};
	
	
	@RequestMapping(value="/cashs",method=RequestMethod.POST)
	@ApiOperation(value="提款申请",notes="提款申请",consumes="application/json",produces="application/json")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object addCash(@RequestBody @Valid CashOp cashOp)throws Exception{
		return cashService.addCash(cashOp,this.getUserId(),this.getUserTrueName());
	}
	
	
	@RequestMapping(value="/finaccs",method=RequestMethod.PUT)
	@ApiOperation(value="绑定银行卡",notes="绑定银行卡",consumes="application/json",produces="application/json")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object updateFinacc(@RequestBody @Valid FinaccOp finaccOp){
		return cashService.updateFinacc(finaccOp,this.getServiceId());
	};
}
