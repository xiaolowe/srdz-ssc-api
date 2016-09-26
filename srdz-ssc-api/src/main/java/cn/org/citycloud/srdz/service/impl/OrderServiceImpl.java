package cn.org.citycloud.srdz.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.org.citycloud.srdz.bean.OrderSearch;
import cn.org.citycloud.srdz.entity.Order;
import cn.org.citycloud.srdz.entity.SalesOrderInfo;
import cn.org.citycloud.srdz.repository.OrderDao;
import cn.org.citycloud.srdz.repository.OrderInfoDao;
import cn.org.citycloud.srdz.repository.SalesOrderInfoDao;
import cn.org.citycloud.srdz.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private SalesOrderInfoDao salesOrderInfoDao;
	@Autowired
	private OrderDao orderDao;

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Object getOrderList(OrderSearch orderSearch,int serviceId) {
		// TODO Auto-generated method stub
		int page = orderSearch.getPage();
		int pageSize = orderSearch.getPageSize();
		Sort sort = new Sort(Sort.Direction.DESC,"orderId");
		Pageable pageable = new PageRequest(page-1, pageSize, sort);
		Specification<Order> spec = new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				if(orderSearch.getOrderId().length()>0){
					predicate = cb.equal(root.get("orderId"), Integer.parseInt(orderSearch.getOrderId()));
				}
				predicate = cb.and(predicate,cb.equal(root.get("supplier").get("serviceCenterId"), serviceId));
				if(orderSearch.getUserTruename().length()>0){
					String name = orderSearch.getUserTruename();
					predicate = cb.and(predicate,cb.like(root.join("member").get("memberTruename"),"%"+name+"%"));
				}
				if(orderSearch.getOrderStatus()!=-1){
					predicate = cb.and(predicate,cb.equal(root.get("orderStatus"), orderSearch.getOrderStatus()));
				}
				if(orderSearch.getOrderType()!=0){
					predicate = cb.and(predicate,cb.equal(root.get("orderType"), orderSearch.getOrderType()));
				}
				if(orderSearch.getStime().length()>0&&orderSearch.getEtime().length()>0){
					String stime = orderSearch.getStime()+" 00:00:00";
					String etime = orderSearch.getEtime()+" 23:59:59";
					try {
						predicate = cb.and(predicate,cb.between(root.get("orderTime"), df.parse(stime), df.parse(etime)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return query.where(predicate).getRestriction();
			}
		};
		return orderDao.findAll(spec, pageable);
	}

	@Override
	public Object getOrderDetail(int orderId,int orderType) {
		// TODO Auto-generated method stub
		if(orderType==1){
			return orderInfoDao.findOne(orderId);
		}		
		else if(orderType==2){
			return salesOrderInfoDao.findOne(orderId);
		}else {
			return orderDao.findOne(orderId);
		}
			
	}

	@Override
	public Object getDrpOrderList(OrderSearch orderSearch) {
		// TODO Auto-generated method stub
		int page = orderSearch.getPage();
		int pageSize = orderSearch.getPageSize();
		Sort sort = new Sort(Sort.Direction.DESC,"orderId");
		Pageable pageable = new PageRequest(page, pageSize, sort);
		Specification<SalesOrderInfo> spec = new Specification<SalesOrderInfo>() {

			@Override
			public Predicate toPredicate(Root<SalesOrderInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				if(orderSearch.getOrderId().length()>0){
					predicate = cb.equal(root.get("orderId"), Integer.parseInt(orderSearch.getOrderId()));
				}
				if(orderSearch.getUserTruename().length()>0){
					String name = orderSearch.getUserTruename();
					predicate = cb.and(predicate,cb.like(root.join("wechatSalesMember").get("memberTruename"),"%"+name+"%"));
				}
				if(orderSearch.getOrderStatus()!=-1){
					predicate = cb.and(predicate,cb.equal(root.get("order").get("orderStatus"), orderSearch.getOrderStatus()));
				}
				if(orderSearch.getOrderType()!=0){
					predicate = cb.and(predicate,cb.equal(root.get("orderType"), orderSearch.getOrderType()));
				}
				if(orderSearch.getStime().length()>0&&orderSearch.getEtime().length()>0){
					String stime = orderSearch.getStime()+" 00:00:00";
					String etime = orderSearch.getEtime()+" 23:59:59";
					try {
						
						predicate = cb.and(predicate,cb.between(root.get("order").get("orderTime"), df.parse(stime), df.parse(etime)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return query.where(predicate).getRestriction();
			}
		};
		return salesOrderInfoDao.findAll(spec, pageable);
	}

	@Override
	public Object getDrpOrderDetail(int orderId) {
		// TODO Auto-generated method stub
		return salesOrderInfoDao.findOne(orderId);
	}

	@Override
	public Object getOrderDetail(int orderId) {
		// TODO Auto-generated method stub
		Order order = orderDao.findOne(orderId);
		
		return getOrderDetail(orderId, order.getOrderType());
	}

}
