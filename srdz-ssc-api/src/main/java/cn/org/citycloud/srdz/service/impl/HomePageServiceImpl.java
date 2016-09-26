package cn.org.citycloud.srdz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.citycloud.srdz.repository.OrderDao;
import cn.org.citycloud.srdz.repository.SupplierDao;
import cn.org.citycloud.srdz.service.HomePageService;

@Service
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private SupplierDao supplierDao;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Object getDetail(int serviceId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		String time = df.format(new Date());
		String stime = time+" 00:00:00";
		String etime = time+" 23:59:59";
		int orderCount = orderDao.count(serviceId,stime,etime);
		int supplierCount = supplierDao.count(serviceId,stime,etime);
		float saleProfit = orderDao.sumSales(serviceId,stime,etime);
		float supplierProfit = orderDao.sum(serviceId, stime, etime);
		float profit = saleProfit+supplierProfit;
		resultMap.put("orders", orderCount);
		resultMap.put("suppliers", supplierCount);
		resultMap.put("profit", profit);
		return resultMap;
	}

}
