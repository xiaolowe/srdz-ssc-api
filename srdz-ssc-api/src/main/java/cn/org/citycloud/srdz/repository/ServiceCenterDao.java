package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.ServiceCenter;

public interface ServiceCenterDao extends JpaRepository<ServiceCenter, Integer>, JpaSpecificationExecutor<ServiceCenter> {

}
