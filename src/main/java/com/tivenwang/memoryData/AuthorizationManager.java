package com.tivenwang.memoryData;

import java.util.concurrent.ConcurrentHashMap;

import com.tivenwang.filter.RequestFilter;
import com.tivenwang.util.MD5;

/** 
 * @author Pecan 
 * 类说明 
 */
public class AuthorizationManager {
	private static final ConcurrentHashMap<String, String> userAuthorizationInfo = new ConcurrentHashMap<String, String>(20000, 0.75f, 100);

    /**
     * 获取用户的token信息
     * @param requestUserId   用户ID
     * @param requestPlatform  软件平台  -GlobalConfig
     * @return
     */
    public static String getAuthorizationInfo(String requestUserId){
    	return AuthorizationManager.userAuthorizationInfo.get(requestUserId);					
    }
    
    
    /**
     * 生成token
     * @param platform
     * @param userId
     * @param machineImei
     * @return
     */
    public static String setAuthorizationInfo(Integer userId,String machineImei){
		MD5 md5 = new MD5();
		long currentTime = RequestFilter.getTime();
		String Token = md5.getMD5ofStr(currentTime + machineImei);
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append(currentTime).append(";").append(md5.getMD5ofStr(currentTime + machineImei))
		.append(";").append(machineImei).append(";").append(RequestFilter.TOKENVALIDATYTIME);
		userAuthorizationInfo.put(String.valueOf(userId),stringBuffer.toString());
		return Token;
    }
    
    
    /**
     * 删除用户token
     * @param platform
     * @param userId
     */
    public static void removeAuthorizationInfo(String platform,Integer userId){
    	userAuthorizationInfo.remove(String.valueOf(userId));
    }
    
}
