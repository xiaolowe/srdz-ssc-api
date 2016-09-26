package cn.org.citycloud.srdz.service;

import cn.org.citycloud.srdz.bean.CashOp;
import cn.org.citycloud.srdz.bean.FinaccOp;
import cn.org.citycloud.srdz.bean.page.Page;

public interface CashService {
	
	public Object getFinaccDetail(Page userPage,int serviceId);
	
	public Object getCashDetail(int cashId);
	
	public Object addCash(CashOp cashOp)throws Exception;
	
	public Object addCash(CashOp cashOp,int userId,String userName)throws Exception;
	
	public Object updateFinacc(FinaccOp finaccOp,int serviceId);
}
