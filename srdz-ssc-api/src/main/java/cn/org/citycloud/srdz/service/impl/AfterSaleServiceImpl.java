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

import cn.org.citycloud.srdz.bean.AfSaleOp;
import cn.org.citycloud.srdz.bean.AfterSaleSearch;
import cn.org.citycloud.srdz.entity.AfterSale;
import cn.org.citycloud.srdz.repository.AfterSaleDao;
import cn.org.citycloud.srdz.service.AfterSaleService;

@Service
public class AfterSaleServiceImpl implements AfterSaleService {

	@Autowired
	private AfterSaleDao afterSaleDao;
	
	@Override
	public Object getList(AfterSaleSearch afterSaleSearch) {
		// TODO Auto-generated method stub
		int page = afterSaleSearch.getPage();
		int pageSize = afterSaleSearch.getPageSize();
		Sort sort = new Sort(Sort.Direction.DESC,"createDate");
		Pageable pageable = new PageRequest(page-1, pageSize, sort);
		Specification<AfterSale> spec = new Specification<AfterSale>() {

			@Override
			public Predicate toPredicate(Root<AfterSale> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				if(afterSaleSearch.getMemberName().length()>0){
					predicate = cb.and(predicate,cb.like(root.get("memberName"), "%"+afterSaleSearch.getMemberName()+"%"));
				}
				if(afterSaleSearch.getSupplierName().length()>0){
					predicate = cb.and(predicate,cb.like(root.get("supplierName"), "%"+afterSaleSearch.getSupplierName()+"%"));
				}
				if(afterSaleSearch.getOrderId().length()>0){
					predicate = cb.and(predicate,cb.equal(root.get("orderId"), Integer.parseInt(afterSaleSearch.getOrderId())));
				}
				return query.where(predicate).getRestriction();
			}
		};
		return afterSaleDao.findAll(spec, pageable);
	}

	@Override
	public Object getDetail(int afterSaleId) {
		// TODO Auto-generated method stub
		return afterSaleDao.findOne(afterSaleId);
	}

	@Override
	public Object update(int afterSaleId, AfSaleOp afSaleOp) {
		// TODO Auto-generated method stub
		AfterSale afterSale = afterSaleDao.findOne(afterSaleId);
		afterSale.setResult(afSaleOp.getResult());
		afterSale.setStatus(1);
		return afterSaleDao.save(afterSale);
	}

}
