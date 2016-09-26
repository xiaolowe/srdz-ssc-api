package cn.org.citycloud.srdz.service;

import java.util.List;

import cn.org.citycloud.srdz.bean.MsgSearch;

public interface MessageService {
	
	public Object getMsgList(MsgSearch msgSearch,int serviceId);
	
	public Object getMsgDetail(int msgId);
	
	public Object update(int msgId);
	
	public Object delete(List<Integer> ids,int serviceId);
	
}
