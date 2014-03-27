package com.tivenwang.resources.user;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tivenwang.converter.user.GetUserInfoConverter;
import com.tivenwang.converter.user.UserLoginConverter;
import com.tivenwang.converter.user.UserLogoutConverter;
import com.tivenwang.resources.ResourceService;

/** 
 * @author Pecan 
 * 类说明 
 */
@Path("user")
public class UserResource {

	/**
	 * 用户登录
	 * @param content
	 * @param uri
	 * @return
	 */
	@Path("login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String login(String content){
		return ResourceService.callService(new UserLoginConverter(), "user/login", content);
	}
	
	/**
	 * 用户退出
	 * @param content
	 * @param uri
	 * @return
	 */
	@Path("logout")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String loginOut(String content){
		return ResourceService.callService(new UserLogoutConverter(), "user/logout", content);
	}
	
	/**
	 * 获取用户信息
	 * @param content
	 * @param uri
	 * @return
	 */
	@Path("info/{userId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String info(@PathParam("userId") String content){
		return ResourceService.callService(new GetUserInfoConverter(), "user/info", content);
	}
	
}
