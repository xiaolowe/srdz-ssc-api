package cn.org.citycloud.srdz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.org.citycloud.srdz.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier> {

	@Query(value="select count(supplier_id) from supplier where service_center_id= ?1 and (create_time between ?2 and ?3)",nativeQuery=true)
	public int count(int serviceId,String stime,String etime);
	
}
