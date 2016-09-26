package cn.org.citycloud.srdz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.org.citycloud.srdz.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
	
	@Query(value="SELECT COUNT(o.order_id) FROM orders o ,supplier s WHERE o.supplier_id=s.supplier_id AND s.service_center_id=?1 and (o.order_time between ?2 and ?3 and o.order_status >= 20)" ,nativeQuery=true)
	public int count(int serviceId,String stime,String etime);
	
	@Query(value="SELECT IFNULL(SUM(oi.service_center_amount),0) FROM order_info oi,orders o,supplier s WHERE oi.order_id=o.order_id AND o.supplier_id=s.supplier_id AND s.service_center_id=?1 and (o.order_time between ?2 and ?3 and o.order_status >= 20)",nativeQuery=true)
	public float sum(int serviceId,String stime,String etime);
		
	@Query(value="SELECT IFNULL(SUM(oi.service_center_amount),0) FROM sales_order_info oi,orders o,supplier s WHERE oi.order_id=o.order_id AND o.supplier_id=s.supplier_id AND s.service_center_id=?1 and (o.order_time between ?2 and ?3 and o.order_status >= 20)",nativeQuery=true)
	public float sumSales(int serviceId,String stime,String etime);
	
	@Query(value="SELECT COUNT(o.order_id),DATE_FORMAT(o.order_time,'%Y-%m-%d') FROM orders o ,supplier s WHERE o.supplier_id=s.supplier_id AND s.service_center_id=?1 and (o.order_time between ?2 and ?3 and o.order_status >= 20) GROUP BY DATE_FORMAT(o.order_time,'%Y %M %d')" ,nativeQuery=true)
	public List<Object[]> countGroupByOrderTime(int serviceId,String stime,String etime);
	
	@Query(value="SELECT IFNULL(SUM(oi.service_center_amount),0),DATE_FORMAT(o.order_time,'%Y-%m-%d') FROM order_info oi,orders o,supplier s WHERE oi.order_id=o.order_id AND o.supplier_id=s.supplier_id AND s.service_center_id=?1 and (o.order_time between ?2 and ?3 and o.order_status >= 20) GROUP BY DATE_FORMAT(o.order_time,'%Y %M %d')",nativeQuery=true)
	public List<Object[]> sumGroupByOrderTime(int serviceId,String stime,String etime);
	
	@Query(value="SELECT IFNULL(SUM(oi.service_center_amount),0),DATE_FORMAT(o.order_time,'%Y-%m-%d') FROM sales_order_info oi,orders o,supplier s WHERE oi.order_id=o.order_id AND o.supplier_id=s.supplier_id AND s.service_center_id=?1 and (o.order_time between ?2 and ?3 and o.order_status >= 20) GROUP BY DATE_FORMAT(o.order_time,'%Y %M %d')",nativeQuery=true)
	public List<Object[]> sumSalesGroupByOrderTime(int serviceId,String stime,String etime);

}
