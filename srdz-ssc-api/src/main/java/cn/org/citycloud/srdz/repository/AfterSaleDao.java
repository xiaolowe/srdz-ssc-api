package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.AfterSale;

public interface AfterSaleDao extends JpaRepository<AfterSale, Integer>, JpaSpecificationExecutor<AfterSale> {

}
