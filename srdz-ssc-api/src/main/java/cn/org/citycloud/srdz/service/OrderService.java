package cn.org.citycloud.srdz.service;

import cn.org.citycloud.srdz.bean.OrderSearch;

public interface OrderService {
	
	public Object getOrderList(OrderSearch orderSearch,int serviceId);
	
	public Object getOrderDetail(int orderId,int orderType);
	
	public Object getOrderDetail(int orderId);
	
	public Object getDrpOrderList(OrderSearch orderSearch);
	
	public Object getDrpOrderDetail(int orderId);

}
