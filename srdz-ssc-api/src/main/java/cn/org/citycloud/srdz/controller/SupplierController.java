package cn.org.citycloud.srdz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.srdz.bean.SupplierSearch;
import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags="供应商管理")
public class SupplierController extends BaseController {
	
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value="/suppliers",method=RequestMethod.GET)
	@ApiOperation(value="供应商列表",notes="供应商列表信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParams(value={@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),
			@ApiImplicitParam(name="level",value="等级ID",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="supplierName",value="供应商名称",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="status",value="供应商状态：0 待审核	1已审核	2驳回		3冻结",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="page",value="页码,默认为1",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="pageSize",value="页面大小,默认为10",required=false,dataType="int",paramType="query")})
	public Object getSupplierList(@ApiIgnore @Valid SupplierSearch supplierSearch){
		return supplierService.getSupplierList(supplierSearch,this.getServiceId());
	}
	
	@RequestMapping(value="/suppliers/{supplierId}",method=RequestMethod.GET)
	@ApiOperation(value="供应商详情",notes="供应商详情信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getSupplierDetail(@ApiParam(name="supplierId",value="供应商ID",required=true)@PathVariable int supplierId){
		return supplierService.getSupplierDetail(supplierId);
	}
	
	@RequestMapping(value="/suppliers/levels",method=RequestMethod.GET)
	@ApiOperation(value="供应商等级列表",notes="供应商等级列表信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getLevelList(){
		return supplierService.getLevelList();
	}
}
