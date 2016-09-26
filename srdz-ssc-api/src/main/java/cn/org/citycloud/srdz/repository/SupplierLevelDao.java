package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.SupplierLevel;

public interface SupplierLevelDao extends JpaRepository<SupplierLevel, Integer>, JpaSpecificationExecutor<SupplierLevel> {

}
