package cn.org.citycloud.srdz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.org.citycloud.srdz.entity.Message;

public interface MessageDao extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {
	
	@Query(value="delete from Message x where x.messageId in ?1")
	public void delete(Collection<Integer> messageIds);
}
