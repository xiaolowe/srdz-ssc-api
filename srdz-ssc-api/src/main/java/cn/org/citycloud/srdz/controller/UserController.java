package cn.org.citycloud.srdz.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.org.citycloud.srdz.repository.ServiceCenterUserDao;
import cn.org.citycloud.srdz.bean.PwdForget;
import cn.org.citycloud.srdz.bean.PwdModify;
import cn.org.citycloud.srdz.bean.UserLogin;
import cn.org.citycloud.srdz.bean.UserToken;
import cn.org.citycloud.srdz.constants.Constants;
import cn.org.citycloud.srdz.core.BaseController;
import cn.org.citycloud.srdz.entity.ServiceCenterUser;
import cn.org.citycloud.srdz.exception.BusinessErrorException;
import cn.org.citycloud.srdz.utils.TokenManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.rubyeye.xmemcached.MemcachedClient;


@RestController
@Api(tags="登录")
@RequestMapping(value="/user")
public class UserController extends BaseController {

	@Autowired
	private ServiceCenterUserDao serviceCenterUserDao;
	@Autowired
	private MemcachedClient cacheClient;
	
	private static String smsInfo = "888888";
	/**
	 * 登录
	 * @param userLogin
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ApiOperation(value="登录",notes="服务中心后台用户登录",consumes="application/json",produces="application/json",httpMethod="POST")
	public Object login(@RequestBody @Valid UserLogin userLogin)throws Exception{
		Specification<ServiceCenterUser> spec = new Specification<ServiceCenterUser>() {
			@Override
			public Predicate toPredicate(Root<ServiceCenterUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate predicate = cb.conjunction();
				predicate = cb.equal(root.get("userName"), userLogin.getUserName());
				predicate = cb.and(predicate,cb.equal(root.get("userPwd"), userLogin.getUserPwd()));
				return query.where(predicate).getRestriction();
			}
		};
		ServiceCenterUser serviceCenterUser =  serviceCenterUserDao.findOne(spec);
		if(serviceCenterUser==null){			
			throw new BusinessErrorException("403","用户名或密码不正确！");
		}
		if(serviceCenterUser.getUserStatus()==0){
			throw new BusinessErrorException("403","账号已被禁用！");
		}
		
		String token = TokenManager.getMe().generateToken(serviceCenterUser.getServiceCenterUserId(), Constants.TOKEN_SECRET);
		serviceCenterUser.setToken(token);
		serviceCenterUser.setUserStatus(serviceCenterUser.getServiceCenter().getStatus());
		UserToken userToken = new UserToken();
		userToken.setToken(token);
		userToken.setUserId(serviceCenterUser.getServiceCenterUserId());
		userToken.setUserName(serviceCenterUser.getUserName());
		userToken.setUserTrueName(serviceCenterUser.getUserTruename());
		userToken.setServiceId(serviceCenterUser.getServiceCenterId());
		cacheClient.add(token,(int)Constants.TOKEN_EXPIRES_IN,userToken);
		
		return serviceCenterUser;
	}
	
	/**
	 * 忘记密码
	 * @param pwdForget
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/forget",method=RequestMethod.POST)
	@ApiOperation(value="忘记密码",notes="忘记密码",consumes="application/json",produces="application/json")
	public Object forget(@RequestBody @Valid PwdForget pwdForget)throws Exception{
		if(!pwdForget.getPwd().equals(pwdForget.getPwdConfirm())){
			throw new BusinessErrorException("403","两次输入的密码不一致!");
		}
		String smsMsg = cacheClient.get(pwdForget.getUserName());
		if(!smsInfo.equalsIgnoreCase(pwdForget.getSms())){
			if(smsMsg==null||!smsMsg.equalsIgnoreCase(pwdForget.getSms())){
				throw new BusinessErrorException("403","验证码输入不正确!");
			}
		}
		ServiceCenterUser serviceCenterUser = serviceCenterUserDao.findByPhone(pwdForget.getUserName());
		if(serviceCenterUser==null){
			throw new BusinessErrorException("403","该手机号尚未注册!");
		}
		if(serviceCenterUser.getUserStatus()==0){
			throw new BusinessErrorException("403","账号已被禁用！");
		}
		serviceCenterUser.setUserPwd(pwdForget.getPwd());
		return serviceCenterUserDao.save(serviceCenterUser);
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	@ApiOperation(value="修改密码",notes="修改密码",consumes="application/json",produces="application/json")
	@ApiImplicitParam(name="token",value="token",required=true,dataType="string",paramType="header")
	public Object modify(@RequestBody @Valid PwdModify pwdModify)throws Exception{
		if(!pwdModify.getPwd().equals(pwdModify.getPwdConfirm())){
			throw new BusinessErrorException("403","两次输入的密码不一致!");
		}	
		ServiceCenterUser serviceCenterUser = serviceCenterUserDao.findOne(this.getUserId());
		if(serviceCenterUser==null){
			throw new BusinessErrorException("403","该手机号尚未注册!");
		}
		if(serviceCenterUser.getUserStatus()==0){
			throw new BusinessErrorException("403","账号已被禁用！");
		}
		if(!serviceCenterUser.getUserPwd().equalsIgnoreCase(pwdModify.getPwdOrigin())){
			throw new BusinessErrorException("403","原始密码不正确！");
		}
		serviceCenterUser.setUserPwd(pwdModify.getPwd());
		return serviceCenterUserDao.save(serviceCenterUser);
	}
}
