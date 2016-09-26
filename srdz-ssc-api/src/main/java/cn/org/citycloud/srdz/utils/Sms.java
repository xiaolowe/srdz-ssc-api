package cn.org.citycloud.srdz.utils;

import javax.ws.rs.core.MediaType;

import com.smsweb.www.servlet.anna.AnnaHttpSendResponseObject;
import com.smsweb.www.servlet.anna.SDKProxy;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class Sms {

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public String sendSms(String phone, String code) {
		// just replace key here
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-c6864eb5cccb3c050090b7d0b2bc4de9"));
		WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/send.json");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile", phone);
		formData.add("message", "您正在使用手机注册门店,验证码：" + code + "	【中物华商】");
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
				formData);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		// System.out.print(textEntity);
		// System.out.print(status);
		return textEntity;
	}

	/**
	 * 群发短信
	 * 
	 * @param list
	 *            手机号列表
	 * @param msg
	 *            短信内容
	 * @return
	 */
	public String sendBatch(String list, String msg) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-c6864eb5cccb3c050090b7d0b2bc4de9"));
		WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/send_batch.json");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile_list", list);
		formData.add("message", msg + "	【中物华商】");
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
				formData);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		return textEntity;
	}

	/**
	 * 短信状态
	 * 
	 * @return
	 */
	private String smsStatus() {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-c6864eb5cccb3c050090b7d0b2bc4de9"));
		WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/status.json");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		ClientResponse response = webResource.get(ClientResponse.class);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		// System.out.print(status);
		// System.out.print(textEntity);
		return textEntity;
	}

	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public static String sendSrdzSms(String phone, String code) {

		// 维纳多公司
		// http://www.weinaduo.net/
		SDKProxy sdk2 = new SDKProxy();
		sdk2.init("http://yl.mobsms.net/send/sendAnna.aspx?", "shanrong", "shanrong1");
		AnnaHttpSendResponseObject oneresp = sdk2.send(phone, code);
		// System.out.println(oneresp.getErrid()+","+oneresp.getErr());

		if (oneresp != null)
			return oneresp.getErrid();
		else
			return null;
	}

}
