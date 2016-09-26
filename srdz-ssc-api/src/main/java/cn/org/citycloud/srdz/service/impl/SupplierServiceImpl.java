package cn.org.citycloud.srdz.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.org.citycloud.srdz.bean.SupplierSearch;
import cn.org.citycloud.srdz.entity.Supplier;
import cn.org.citycloud.srdz.repository.SupplierDao;
import cn.org.citycloud.srdz.repository.SupplierLevelDao;
import cn.org.citycloud.srdz.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private SupplierLevelDao supplierLevelDao;
	
	@Override
	public Object getSupplierList(SupplierSearch supplierSearch,int serviceId) {
		// TODO Auto-generated method stub
		int page = supplierSearch.getPage();
		int pageSize = supplierSearch.getPageSize();
		Sort sort = new Sort(Sort.Direction.DESC,"createTime");
		Pageable pageable = new PageRequest(page-1, pageSize, sort);
		Specification<Supplier> spec = new Specification<Supplier>() {

			@Override
			public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				predicate = cb.and(predicate,cb.equal(root.get("serviceCenterId"), serviceId));
				if(supplierSearch.getLevel()!=0){
					predicate = cb.and(predicate,cb.equal(root.get("supplierLevelId"), supplierSearch.getLevel()));
				}
				if(supplierSearch.getStatus()!=-1){
					predicate = cb.and(predicate,cb.equal(root.get("status"), supplierSearch.getStatus()));
				}
				if(supplierSearch.getSupplierName().length()>0){
					predicate = cb.and(predicate,cb.like(root.get("supplierName"), "%"+supplierSearch.getSupplierName()+"%"));
				}
				return query.where(predicate).getRestriction();
			}
		};
		return supplierDao.findAll(spec, pageable);
	}

	@Override
	public Object getSupplierDetail(int supplierId) {
		// TODO Auto-generated method stub
		return supplierDao.findOne(supplierId);
	}

	@Override
	public Object getLevelList() {
		// TODO Auto-generated method stub
		return supplierLevelDao.findAll();
	}

}
