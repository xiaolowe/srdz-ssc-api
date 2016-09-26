package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.FinAcc;

public interface FinAccDao extends JpaRepository<FinAcc, Integer>, JpaSpecificationExecutor<FinAcc> {
	
	public FinAcc findByAccountNoAndAccountType(int accountNo,int accountType);

}
