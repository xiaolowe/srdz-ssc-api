package cn.org.citycloud.srdz.service;

import cn.org.citycloud.srdz.bean.SupplierSearch;

public interface SupplierService {
	
	public Object getSupplierList(SupplierSearch supplierSearch,int serviceId);
	
	public Object getSupplierDetail(int supplierId);
	
	public Object getLevelList();

}
