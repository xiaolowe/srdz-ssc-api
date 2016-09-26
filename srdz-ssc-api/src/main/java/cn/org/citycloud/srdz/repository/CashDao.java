package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.Cash;

public interface CashDao extends JpaRepository<Cash, Integer>, JpaSpecificationExecutor<Cash> {

	public Cash findByServiceCenterIdAndConfirmStatus(int serviceCenterId,int confirmStatus);
}
