package cn.org.citycloud.srdz.constants;

import java.text.SimpleDateFormat;

/**
 * 常量Class
 * @author lanbo
 *
 */
public class Constants {

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final long TOKEN_EXPIRES_IN = 24*3600;
	
	public static final String TOKEN_SECRET = "IFFa52XkBEQ9AoO8";
	
	// 会员的状态 1为开启 0为关闭
	public static final int MEMBER_STATUS_OPEN = 1;
	
	public static final int MEMBER_STATUS_CLOSED = 0;
	
	// 接口外网地址
	public static final String API_URL1 = "";
	
	// 接口外网路径
	public static final String API_PATH = "/xbty-manager-api";
	
	//图片外网地址
	public static final String FILE_URL="";
	
	// 接口外网地址
	public static final String API_URL = "";
	
	// 素材库类型 [1:我的素材库， 2:系统素材库]
	public static final int MATERIAL_PERSONAL = 1;
	public static final int MATERIAL_SYSTEM = 2;
	
	public static final int ARTICLE_STATE = 1;

}
