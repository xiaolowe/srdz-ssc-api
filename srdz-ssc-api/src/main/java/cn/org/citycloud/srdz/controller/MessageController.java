package cn.org.citycloud.srdz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.srdz.bean.MsgSearch;
import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags="消息管理")
public class MessageController extends BaseController{
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/messages",method=RequestMethod.GET)
	@ApiOperation(value="消息列表",notes="消息列表信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParams(value={@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header"),
			@ApiImplicitParam(name="messageKey",value="消息关键字",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="stime",value="开始时间  2016-03-23",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="etime",value="结束时间  2016-03-25",required=false,dataType="string",paramType="query"),
			@ApiImplicitParam(name="page",value="页码,默认为1",required=false,dataType="int",paramType="query"),
			@ApiImplicitParam(name="pageSize",value="页面大小,默认为10",required=false,dataType="int",paramType="query")})
	public Object getMsgList(@ApiIgnore @Valid MsgSearch msgSearch){
		return messageService.getMsgList(msgSearch,this.getServiceId());
	}
		
	@RequestMapping(value="/messages/{msgId}",method=RequestMethod.GET)
	@ApiOperation(value="消息详情",notes="消息详细信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getMsgDetail(@ApiParam(name="msgId",value="消息ID",required=true)@PathVariable int msgId){
		return messageService.getMsgDetail(msgId);
	}
	
	@RequestMapping(value="/messages/{msgId}",method=RequestMethod.PUT)
	@ApiOperation(value="消息状态修改",notes="消息状态修改",consumes="application/json",produces="application/json",httpMethod="PUT")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object update(@ApiParam(name="msgId",value="消息ID",required=true)@PathVariable int msgId){
		return messageService.update(msgId);
	}
	
	@RequestMapping(value="/messages",method=RequestMethod.DELETE)
	@ApiOperation(value="消息删除",notes="消息删除",consumes="application/json",produces="application/json")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object delete(@RequestParam Integer[] ids){
		return messageService.delete(Arrays.asList(ids),this.getServiceId());
	}
	
}
