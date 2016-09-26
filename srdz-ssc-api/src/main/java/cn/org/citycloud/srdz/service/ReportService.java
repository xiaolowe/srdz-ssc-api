package cn.org.citycloud.srdz.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

public interface ReportService {
	
	public Object getDetail(int serviceId,Date date);
	
	public Object getList(int serviceId,Date date);
	
	/**
	 * 报表下载
	 * @param serviceId
	 * @param date
	 * @throws IOException 
	 */
	public void downReportExcel(int serviceId,Date date, ByteArrayOutputStream os) throws IOException;
}
