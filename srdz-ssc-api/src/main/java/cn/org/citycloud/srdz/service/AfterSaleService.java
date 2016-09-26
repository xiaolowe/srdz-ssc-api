package cn.org.citycloud.srdz.service;

import cn.org.citycloud.srdz.bean.AfSaleOp;
import cn.org.citycloud.srdz.bean.AfterSaleSearch;

public interface AfterSaleService {
	
	public Object getList(AfterSaleSearch afterSaleSearch);
	
	public Object getDetail(int afterSaleId);
	
	public Object update(int afterSaleId,AfSaleOp afSaleOp);
}
