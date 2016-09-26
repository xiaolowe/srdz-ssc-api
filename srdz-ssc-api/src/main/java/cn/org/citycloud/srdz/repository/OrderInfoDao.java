package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.OrderInfo;

public interface OrderInfoDao extends JpaRepository<OrderInfo, Integer>, JpaSpecificationExecutor<OrderInfo> {

}
