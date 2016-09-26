package cn.org.citycloud.srdz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.service.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="首页")
public class HomePageController extends BaseController {
	
	@Autowired
	private HomePageService homePageService;
	
	@RequestMapping(value="/homepage",method=RequestMethod.GET)
	@ApiOperation(value="首页信息",notes="首页详细信息",consumes="application/json",produces="application/json",httpMethod="GET")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getDetail(){
		return homePageService.getDetail(this.getServiceId());
	}
}
