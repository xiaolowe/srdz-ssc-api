package cn.org.citycloud.srdz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Null;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.citycloud.srdz.bean.CashOp;
import cn.org.citycloud.srdz.bean.FinaccOp;
import cn.org.citycloud.srdz.bean.page.Page;
import cn.org.citycloud.srdz.constants.SysCodes;
import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.entity.Cash;
import cn.org.citycloud.srdz.entity.FinAcc;
import cn.org.citycloud.srdz.entity.ServiceCenter;
import cn.org.citycloud.srdz.exception.BusinessErrorException;
import cn.org.citycloud.srdz.repository.CashDao;
import cn.org.citycloud.srdz.repository.FinAccDao;
import cn.org.citycloud.srdz.repository.ServiceCenterDao;
import cn.org.citycloud.srdz.service.CashService;

@Service
public class CashServiceImpl implements CashService {
	
	@Autowired
	private FinAccDao finAccDao;
	@Autowired
	private CashDao cashDao;
	@Autowired
	private ServiceCenterDao serviceCenterDao;

	private Logger logger = LoggerFactory.getLogger(CashServiceImpl.class);

	@Override
	public Object getFinaccDetail(Page page,int serviceId) {
		// TODO Auto-generated method stub
		ServiceCenter serviceCenter = serviceCenterDao.findOne(serviceId);
		int flag=0;
		if(serviceCenter.getCardNo()!=null&&serviceCenter.getCardNo().trim().length()>0) flag=1;
		FinAcc finAcc = finAccDao.findByAccountNoAndAccountType(serviceId,3);
		Sort sort = new Sort(Sort.Direction.DESC,"createTime");
		Pageable pageable = new PageRequest(page.getPage()-1, page.getPageSize(), sort);
		Specification<Cash> spec = new Specification<Cash>() {

			@Override
			public Predicate toPredicate(Root<Cash> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				predicate = cb.equal(root.get("serviceCenterId"), serviceId);
				return query.where(predicate).getRestriction();
			}
		};
		org.springframework.data.domain.Page<Cash> cashPage = cashDao.findAll(spec,pageable);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("serviceCenter", serviceCenter);
		resultMap.put("finAcc", finAcc);
		resultMap.put("cashPage", cashPage);
		resultMap.put("flag", flag);
		return resultMap;
	}

	@Override
	public Object getCashDetail(int cashId) {
		// TODO Auto-generated method stub
		return cashDao.findOne(cashId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object addCash(CashOp cashOp,int userId,String userName)throws Exception {
		// TODO Auto-generated method stub
		logger.info("添加服务中心提款申请---------------------");
		ServiceCenter serviceCenter = serviceCenterDao.findOne(cashOp.getServiceCenterId());
		FinAcc finAcc = finAccDao.findByAccountNoAndAccountType(cashOp.getServiceCenterId(),3);
		Cash cash = cashDao.findByServiceCenterIdAndConfirmStatus(cashOp.getServiceCenterId(), 0);
		if(cash!=null)
			throw new BusinessErrorException("403","当前服务中心有提款未处理！");
		
		cash = new Cash();
		cash.setServiceCenterId(cashOp.getServiceCenterId());
		cash.setApplyMoney(cashOp.getApplyMoney());
		cash.setConfirmStatus(0);
		cash.setApplyType(SysCodes.APPLY_TYPE);
		cash.setApplyUserId(userId);
		cash.setApplyUserName(userName);
		cash.setCompanyName(serviceCenter.getCompanyName());
		cash.setAccountType(SysCodes.ACCOUNT_TYPE);
		cash.setBankAccName(serviceCenter.getCardOwner());
		cash.setBankAccNumber(serviceCenter.getCardNo());
		cash.setBankName(serviceCenter.getBankName() + serviceCenter.getAccountBank());
		cash.setAccountSurplus(finAcc.getAccountBal().subtract(cashOp.getApplyMoney()));
		
		Date date = new Date();
		cash.setApplyTime(date);
		cash.setCreateTime(date);
		cash.setUpdateTime(date);

		logger.debug("修改账户金额，账户：{}， 账户类型：{}", finAcc.getAccountNo(), finAcc.getAccountType());
		logger.debug("账户当前财产状况, 收入：{}，支出：{}，余额：{}", finAcc.getAccountIncome(), finAcc.getAccountPay(), finAcc.getAccountBal());
		logger.debug("账户余额增加：{}，支出增加：{}", cashOp.getApplyMoney(), cashOp.getApplyMoney());
		finAcc.setAccountBal(finAcc.getAccountBal().subtract(cashOp.getApplyMoney()));
		finAcc.setAccountPay(finAcc.getAccountPay().add(cashOp.getApplyMoney()));
		cash = cashDao.save(cash);
		finAccDao.save(finAcc);
		return cash;
	}

	@Override
	public Object updateFinacc(FinaccOp finaccOp,int serviceId) {
		// TODO Auto-generated method stub
		ServiceCenter serviceCenter = serviceCenterDao.findOne(serviceId);
		BeanUtils.copyProperties(finaccOp, serviceCenter);
		return serviceCenterDao.save(serviceCenter);
	}

	@Override
	public Object addCash(CashOp cashOp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
