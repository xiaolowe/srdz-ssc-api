package cn.org.citycloud.srdz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.srdz.bean.OrderSearch;
import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags="订单管理")
public class OrderController extends BaseController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/orders",method=RequestMethod.GET)
	@ApiOperation(value="订单列表",notes="订单列表信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParams(value={@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),
			@ApiImplicitParam(name="orderId",value="订单号",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="userTruename",value="下单人姓名",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="stime",value="开始时间  2016-03-23",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="etime",value="结束时间  2016-03-25",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="orderStatus",value="订单状态：0(已取消)10(默认):未付款;20:已付款;30:已收货;40:已评价",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="orderType",value="订单类型,1为普通订单,2为分销订单,0为全部",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="page",value="页码,默认为1",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="pageSize",value="页面大小,默认为10",required=false,dataType="int",paramType="query")})
	public Object getOrderList(@ApiIgnore @Valid OrderSearch orderSearch){
		return orderService.getOrderList(orderSearch,this.getServiceId());
	}
	
	@RequestMapping(value="/orders/{orderId}/{orderType}",method=RequestMethod.GET)
	@ApiOperation(value="订单信息",notes="订单的详细信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getOrderDetail(@ApiParam(name="orderId",value="订单ID",required=true)@PathVariable int orderId,@ApiParam(name="orderType",value="订单类型，1为普通订，2为分销订单",required=true)@PathVariable int orderType){
		return orderService.getOrderDetail(orderId,orderType);
	}
	
	@RequestMapping(value="/orders/{orderId}",method=RequestMethod.GET)
	@ApiOperation(value="订单信息",notes="订单的详细信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getDetail(@ApiParam(name="orderId",value="订单ID",required=true)@PathVariable int orderId){
		return orderService.getOrderDetail(orderId);
	}
	
	
	/*@RequestMapping(value="/orders/distribution",method=RequestMethod.GET)
	@ApiOperation(value="分销订单列表",notes="分销订单列表信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParams(value={@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),
			@ApiImplicitParam(name="orderId",value="订单号",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="userTruename",value="下单人姓名",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="stime",value="开始时间  2016-03-23",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="etime",value="结束时间  2016-03-25",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="orderStatus",value="订单状态：0(已取消)10(默认):未付款;20:已付款;30:已收货;40:已评价",required=false,dataType="int",paramType="query")})
	public Object getDrpOrderList(@ApiIgnore @Valid OrderSearch orderSearch){
		return orderService.getDrpOrderList(orderSearch);
	}*/
	
	
	/*@RequestMapping(value="/orders/distribution/{orderId}",method=RequestMethod.GET)
	@ApiOperation(value="分销订单详情",notes="分销订单的详细信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getDrpOrderDetail(@ApiParam(name="orderId",value="订单ID",required=true)@PathVariable int orderId){
		return orderService.getDrpOrderDetail(orderId);
	}*/
}
