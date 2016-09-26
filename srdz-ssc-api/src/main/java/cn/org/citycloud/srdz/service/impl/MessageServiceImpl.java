package cn.org.citycloud.srdz.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.org.citycloud.srdz.bean.MsgSearch;
import cn.org.citycloud.srdz.entity.MailMessage;
import cn.org.citycloud.srdz.entity.Message;
import cn.org.citycloud.srdz.repository.MailMessageDao;
import cn.org.citycloud.srdz.repository.MessageDao;
import cn.org.citycloud.srdz.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private MailMessageDao mailMessageDao;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Object getMsgList(MsgSearch msgSearch,int serviceId) {
		// TODO Auto-generated method stub
		int page = msgSearch.getPage();
		int pageSize = msgSearch.getPageSize();
		Sort sort = new Sort(Sort.Direction.DESC,"createDate");
		Pageable pageable = new PageRequest(page-1, pageSize, sort);
		Specification<Message> spec = new Specification<Message>() {

			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				predicate = cb.and(predicate,cb.equal(root.get("platform"), 3));
				predicate = cb.and(predicate,cb.equal(root.get("receiverId"), serviceId));
				if(msgSearch.getMessageKey().length()>0){
					predicate = cb.and(predicate,cb.like(root.get("messageContent"), "%"+msgSearch.getMessageKey()+"%"));
				}
				if(msgSearch.getStime().length()>0&&msgSearch.getEtime().length()>0){
					String stime = msgSearch.getStime()+" 00:00:00";
					String etime = msgSearch.getEtime()+" 23:59:59";
					try {
						predicate = cb.and(predicate,cb.between(root.get("createDate"), df.parse(stime), df.parse(etime)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return query.where(predicate).getRestriction();
			}
		};
		return messageDao.findAll(spec, pageable);
	}

	@Override
	public Object getMsgDetail(int msgId) {
		// TODO Auto-generated method stub
		return messageDao.findOne(msgId);
	}

	@Override
	public Object update(int msgId) {
		// TODO Auto-generated method stub
		Message message = messageDao.findOne(msgId);
		message.setStatus(1);
		return messageDao.save(message);
	}

	@Override
	public Object delete(List<Integer> ids,int serviceId) {
		// TODO Auto-generated method stub
		for(int id:ids){
			messageDao.delete(id);
		}
		return getMsgList(new MsgSearch(),serviceId);
	}

}
