package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.org.citycloud.srdz.entity.ServiceCenterUser;

public interface ServiceCenterUserDao extends JpaRepository<ServiceCenterUser, Integer>, JpaSpecificationExecutor<ServiceCenterUser> {
	
	public ServiceCenterUser findByUserName(String userName);
	
	public ServiceCenterUser findByPhone(String phone);
	
	public ServiceCenterUser findByUserNameAndUserPwd(String userName,String userPwd);
	
}
