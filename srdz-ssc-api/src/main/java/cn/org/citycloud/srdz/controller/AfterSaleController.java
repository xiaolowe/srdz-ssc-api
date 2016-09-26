package cn.org.citycloud.srdz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.srdz.bean.AfSaleOp;
import cn.org.citycloud.srdz.bean.AfterSaleSearch;
import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.service.AfterSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags="售后管理")
public class AfterSaleController extends BaseController {
	
	
	@Autowired
	private AfterSaleService afterSaleService;
	
	@RequestMapping(value="/afsales",method=RequestMethod.GET)
	@ApiOperation(value="售后列表",notes="售后列表信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParams(value={@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),
			@ApiImplicitParam(name="orderId",value="订单号",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="supplierName",value="供应商名称",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="memberName",value="用户名称",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="page",value="页码,默认为1",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="pageSize",value="页面大小,默认为10",required=false,dataType="int",paramType="query")})
	public Object getList(@ApiIgnore @Valid AfterSaleSearch afterSaleSearch){
		return afterSaleService.getList(afterSaleSearch);
	}
	
	@RequestMapping(value="/afsales/{afterSaleId}",method=RequestMethod.GET)
	@ApiOperation(value="售后详情",notes="售后详细信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getDetail(@ApiParam(name="afterSaleId",value="售后ID",required=true)@PathVariable int afterSaleId){
		return afterSaleService.getDetail(afterSaleId);
	}
	
	@RequestMapping(value="/afsales/{afterSaleId}",method=RequestMethod.PUT)
	@ApiOperation(value="售后处理",notes="售后处理",consumes="application/json",produces="application/json",httpMethod="PUT")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object update(@ApiParam(name="afterSaleId",value="售后ID",required=true)@PathVariable int afterSaleId,@RequestBody @Valid AfSaleOp afSaleOp){
		return afterSaleService.update(afterSaleId, afSaleOp);
	}
}
