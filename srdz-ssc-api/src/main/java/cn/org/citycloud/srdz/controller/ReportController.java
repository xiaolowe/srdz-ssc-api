package cn.org.citycloud.srdz.controller;

import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.exception.BusinessErrorException;
import cn.org.citycloud.srdz.service.ReportService;
import cn.org.citycloud.srdz.utils.CalendarUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.Date;

@RestController
@Api(tags="报表管理")
public class ReportController extends BaseController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value="/reports",method=RequestMethod.GET)
	@ApiOperation(value="报表分月统计信息",notes="报表分月统计信息",consumes="application/json",produces="application/json")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getDetail(@ApiParam(name="searchTime",value="查询日期",required=true)@RequestParam String searchTime){
		
		Date date = new Date();
		try {
			date = CalendarUtils.df.parse(searchTime+"-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportService.getDetail(this.getServiceId(),date);
	}
	
	@RequestMapping(value = "/downExcel", method = RequestMethod.GET)
    @ApiOperation(value="下载报表",notes="下载报表",consumes="application/json",produces="application/json")
	public void downExcel(
			@ApiParam(name="serviceId",value="服务商Id",required=true)@RequestParam int serviceId,
			@ApiParam(name="searchTime",value="查询日期",required=true)@RequestParam String searchTime, 
			HttpServletResponse response) throws IOException, BusinessErrorException, ParseException {
		Date date = CalendarUtils.df.parse(searchTime+"-01");
    	
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        reportService.downReportExcel(serviceId, date, os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("进出账目表.xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(out);
        byte[] buff = new byte[2048];
        int bytesRead;
        // Simple read/write loop.
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bos.close();
    }
	
	
	/*@RequestMapping(value="/reports/days",method=RequestMethod.GET)
	@ApiOperation(value="报表每天统计列表",notes="报表每天统计列表",consumes="application/json",produces="application/json")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object getList(@ApiParam(name="searchTime",value="查询日期",required=true)@RequestParam String searchTime){
		Date date = new Date();
		try {
			date = CalendarUtils.df.parse(searchTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportService.getList(this.getServiceId(),date);
	}*/
}
