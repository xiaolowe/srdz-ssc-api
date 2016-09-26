package cn.org.citycloud.srdz.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.citycloud.srdz.bean.ReportResult;
import cn.org.citycloud.srdz.repository.OrderDao;
import cn.org.citycloud.srdz.service.ReportService;
import cn.org.citycloud.srdz.utils.CalendarUtils;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Object getDetail(int serviceId,Date date) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		String firstDay = CalendarUtils.getFirstDay(date)+" 00:00:00";
		String lastDay = CalendarUtils.getLastDay(date)+" 23:59:59";
		int orderCount = orderDao.count(serviceId, firstDay, lastDay);
		float saleProfit = orderDao.sumSales(serviceId,firstDay,lastDay);
		float supplierProfit = orderDao.sum(serviceId, firstDay, lastDay);
		float profit = saleProfit+supplierProfit;
		resultMap.put("orders", orderCount);
		resultMap.put("profit", profit);
		resultMap.put("daily", getList(serviceId, date));
		return resultMap;
	}

	@Override
	public Object getList(int serviceId,Date date) {
		// TODO Auto-generated method stub
		Map<String, ReportResult> resultMap = new TreeMap<>();
		String firstDay = CalendarUtils.getFirstDay(date)+" 00:00:00";
		String lastDay = CalendarUtils.getLastDay(date)+" 23:59:59";
		List<Object[]> orders=orderDao.countGroupByOrderTime(serviceId, firstDay, lastDay);
		List<Object[]> suppliers=orderDao.sumGroupByOrderTime(serviceId, firstDay, lastDay);
		List<Object[]> sales=orderDao.sumSalesGroupByOrderTime(serviceId, firstDay, lastDay);
		for(Object[] order:orders){
			int count = ((BigInteger)order[0]).intValue();
			String dateTime = (String)order[1];
			ReportResult reportResult = new ReportResult();
			reportResult.setOrders(count);
			reportResult.setDateTime(dateTime);
			resultMap.put(dateTime, reportResult);
		}
		for(Object[] supplier:suppliers){
			
			String dateTime = (String)supplier[1];
			float money = ((BigDecimal)supplier[0]).floatValue();
			if(resultMap.containsKey(dateTime)){
				resultMap.get(dateTime).setSupplierMoney(money);
			}
		}
		for(Object[] sale:sales){
			String dateTime = (String)sale[1];
			float money = ((BigDecimal)sale[0]).floatValue();
			if(resultMap.containsKey(dateTime)){
				resultMap.get(dateTime).setSaleMoney(money);
			}
		}
		return resultMap;
	}

	/**
	 * 报表下载
	 * @throws IOException 
	 */
	@Override
	public void downReportExcel(int serviceId, Date date, ByteArrayOutputStream os) throws IOException {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		Workbook wb = new HSSFWorkbook();
		
		Sheet sheet = wb.createSheet("进出账目表");
        Row row = sheet.createRow(0);
        CellRangeAddress cra = new CellRangeAddress(0, 0, 1, 4);
        //在sheet里增加合并单元格
        sheet.addMergedRegion(cra);
        Cell cell0_0 = row.createCell(0);
        cell0_0.setCellValue("报表类型");
        Cell cell0_1 = row.createCell(1);
        int month = c.get(Calendar.MONTH) + 1;
        cell0_1.setCellValue(c.get(Calendar.YEAR) + "-" + month + "-进出账目表");

        sheet.createRow(1);
		
        String[] rowNames = {"收入单据数：", "收入金额："};
        
        // 查询统计数据
        Map<String, Object> resultMap = (Map<String, Object>) getDetail(serviceId, date);
        
        String[] rowKeys = {String.valueOf(resultMap.get("orders")),  String.valueOf(resultMap.get("profit"))};
        
        createRowCell(rowNames, rowKeys, sheet);
        
        sheet.createRow(6);

        String[] columnNames = {"日期", "收入单据", "收入金额"};
        
        Row row7 = sheet.createRow(7);
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell7_N = row7.createCell(i);
            cell7_N.setCellValue(columnNames[i]);
        }
        
        Map<String, ReportResult> reportResults = (Map<String, ReportResult>) resultMap.get("daily");
        
        int i = 1;
        for(String key : reportResults.keySet()) {
        	ReportResult result = reportResults.get(key);
        	
        	Row rowN = sheet.createRow(7 + i);
            Cell cell1 = rowN.createCell(0);
            cell1.setCellValue(result.getDateTime());
            Cell cell2 = rowN.createCell(1);
            cell2.setCellValue(result.getOrders());
            Cell cell3 = rowN.createCell(2);
            cell3.setCellValue(result.getSupplierMoney());
            
            i++;
        }
        
        wb.write(os);
		
	}
	
	 /**
     * 创建row cell
     *
     * @param rowNames
     * @param rowKeys
     * @param sheet
     */
    private void createRowCell(String[] rowNames,String[] rowKeys, Sheet sheet) {
        for (int i = 0; i < rowNames.length; i++) {
            Row rowN = sheet.createRow(i + 2);
            Cell cellN_0 = rowN.createCell(0);
            cellN_0.setCellValue(rowNames[i]);
            Cell cellN_1 = rowN.createCell(1);
            cellN_1.setCellValue(rowKeys[i]);
        }
    }

}
