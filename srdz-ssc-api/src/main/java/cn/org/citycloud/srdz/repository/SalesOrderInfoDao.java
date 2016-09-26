package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.SalesOrderInfo;

public interface SalesOrderInfoDao extends JpaRepository<SalesOrderInfo, Integer>, JpaSpecificationExecutor<SalesOrderInfo> {

}
