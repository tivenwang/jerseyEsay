/*
 * To change this template, choose Tools | Templates
 * and open the template in the ditor.
 */
package com.tivenwang.converter;

import java.util.HashMap;



/**
 * 
 * @author Pecan 
 * 类说明：返回参数说明
 */

public class ResultConfig {
    public static final HashMap<Integer,String> RESULT_CONTAINER = new HashMap<Integer, String>();

    /**
     * 正常返回代码
     */
    //查询成功，有数据
    public static final String MSG_SELECT_SUCCESS = "查询成功，有数据";
    public static final Integer STATUS_SELECT_SUCCESS = 1000;
    //修改成功
    public static final String MSG_MODIFY_SUCCESS = "修改成功";
    public static final Integer STATUS_MODIFY_SUCCESS = 1001;
    //添加成功
    public static final String MSG_ADD_SUCCESS = "添加成功";
    public static final Integer STATUS_ADD_SUCCESS = 1002;    
    //删除成功
    public static final String MSG_DELETE_SUCCESS = "删除成功";
    public static final Integer STATUS_DELETE_SUCCESS = 1003;      
    //关注成功
    public static final String MSG_SETCARE_SUCCESS = "关注成功";
    public static final Integer STATUS_SETCARE_SUCCESS = 1007;    
    //更新成功
    public static final String MSG_UPDATE_SUCCESS = "更新成功";
    public static final Integer STATUS_UPDATE_SUCCESS = 1008;  

    
    public static final Integer TTImeiRegCheckCanRegID = 1100;//可注册
    public static final String  TTImeiRegCheckCanRegVALUE = "可注册";
    
    public static final Integer TTImeiRegCheckCantRegID = 1101;//用户名已经存在
    public static final String  TTImeiRegCheckCantRegVALUE = "用户名已经存在";
    
    public static final Integer TTUserCheckEffectiveID = 1102;//用户名有效
    public static final String  TTUserCheckEffectiveVALUE = "用户名有效";
    
    public static final Integer TTUserCheckUnEffectiveID = 1103;//用户名无效
    public static final String  TTUserCheckUnEffectiveVALUE = "用户名无效";
    
    public static final Integer TTRegistrationSuccessID = 1104;//注册成功
    public static final String  TTRegistrationSuccessVALUE = "注册成功";
    
    
    public static final Integer TTLoginSuccessID = 1106;//登陆成功
    public static final String  TTLoginSuccessVALUE = "登陆成功";
    
    public static final Integer TTLogoutSuccessID = 1107;//退出成功
    public static final String  TTLogoutSuccessVALUE = "退出成功";
    
    
    public static final Integer TTBindingQuerySuccessID = 1110;//查询成功
    public static final String  TTBindingQuerySuccessVALUE = "查询成功";
    
    public static final Integer SendMessageSuccessID = 1125;//发送成功
    public static final String  SendMessageSuccessVALUE = "发送成功";
    public static final Integer SubmitSuccessID = 1126;//提交成功
    public static final String  SubmitSuccessVALUE = "提交成功";
    
    //查询成功，无数据    
    public static final Integer STATUS_NO_DATA_SUCCESS = 1500;         
    public static final String MSG_NO_DATA_SUCCESS = "查询成功，无数据";
    
    //系统错误
    public static final Integer FFSystemErrorID = 3000;//系统错误
    public static final String  FFSystemErrorVALUE = "系统错误";

    //数据库异常
    public static final Integer FFDBConnectionErrorID = 4000;//数据库无法连接
    public static final String  FFDBConnectionErrorVALUE = "数据库无法连接";
    
    //公共异常
    public static final Integer FFUnknownErrorID = 5000;//未知错误
    public static final String  FFUnknownErrorVALUE = "未知错误";
    
    public static final Integer FFJsonFormatErrorID = 5001;//输入的JSON参数不能解析,请检查格式
    public static final String  FFJsonFormatErrorVALUE = "输入的JSON参数不能解析,请检查格式";
    
    public static final Integer FFRelatedInfoID = 5002;//无相关信息
    public static final String  FFRelatedInfoVALUE = "无相关信息";
    
    //HTTP头异常
    public static final Integer FFHttpHeaderNullID = 5100;//缺少用于鉴权的HTTP请求头
    public static final String  FFHttpHeaderNullVALUE = "缺少用于鉴权的HTTP请求头";
    
    public static final Integer FFHttpHeaderTokenInvalidID = 5101;//Token失效，请重新登陆
    public static final String  FFHttpHeaderTokenInvalidVALUE = "Token失效，请重新登陆";
    
    public static final Integer FFHttpHeaderTokenInvalidAndHasLoginAnotherPhoneID = 5102;//Token失效，该账号已在另一台设备登陆，如非您本人操作，请尽快修改密码
    public static final String  FFHttpHeaderTokenInvalidAndHasLoginAnotherPhoneVALUE = "Token失效，该账号已在另一台设备登陆，如非您本人操作，请尽快修改密码";
    
    public static final Integer FFHttpHeaderTokenErrorID = 5103;//Token验证失败,请重新登陆
    public static final String  FFHttpHeaderTokenErrorVALUE = "Token验证失败,请重新登陆";
    
    static{
    	//正常返回代码
    	RESULT_CONTAINER.put(STATUS_SELECT_SUCCESS, MSG_SELECT_SUCCESS);
    	RESULT_CONTAINER.put(STATUS_MODIFY_SUCCESS, MSG_MODIFY_SUCCESS);
    	RESULT_CONTAINER.put(STATUS_ADD_SUCCESS, MSG_ADD_SUCCESS);
    	RESULT_CONTAINER.put(STATUS_DELETE_SUCCESS, MSG_DELETE_SUCCESS);
    	RESULT_CONTAINER.put(STATUS_SETCARE_SUCCESS, MSG_SETCARE_SUCCESS);
    	RESULT_CONTAINER.put(STATUS_UPDATE_SUCCESS, MSG_UPDATE_SUCCESS);
    	RESULT_CONTAINER.put(SendMessageSuccessID, SendMessageSuccessVALUE);
    	RESULT_CONTAINER.put(FFHttpHeaderTokenInvalidID, FFHttpHeaderTokenInvalidVALUE);
    	RESULT_CONTAINER.put(FFHttpHeaderNullID, FFHttpHeaderNullVALUE);
    	RESULT_CONTAINER.put(FFHttpHeaderTokenErrorID, FFHttpHeaderTokenErrorVALUE);
    	RESULT_CONTAINER.put(FFHttpHeaderTokenInvalidAndHasLoginAnotherPhoneID, FFHttpHeaderTokenInvalidAndHasLoginAnotherPhoneVALUE);
    	RESULT_CONTAINER.put(SubmitSuccessID, SubmitSuccessVALUE);
    	
    	RESULT_CONTAINER.put(TTImeiRegCheckCanRegID, TTImeiRegCheckCanRegVALUE);
    	RESULT_CONTAINER.put(TTImeiRegCheckCantRegID, TTImeiRegCheckCantRegVALUE);
    	RESULT_CONTAINER.put(TTUserCheckEffectiveID, TTUserCheckEffectiveVALUE);
    	RESULT_CONTAINER.put(TTUserCheckUnEffectiveID, TTUserCheckUnEffectiveVALUE);
    	RESULT_CONTAINER.put(TTRegistrationSuccessID, TTRegistrationSuccessVALUE);
    	RESULT_CONTAINER.put(TTLoginSuccessID, TTLoginSuccessVALUE);
    	RESULT_CONTAINER.put(TTLogoutSuccessID, TTLogoutSuccessVALUE);
    	RESULT_CONTAINER.put(TTBindingQuerySuccessID, TTBindingQuerySuccessVALUE);
    	RESULT_CONTAINER.put(FFRelatedInfoID, FFRelatedInfoVALUE);
    	RESULT_CONTAINER.put(STATUS_NO_DATA_SUCCESS, MSG_NO_DATA_SUCCESS);
    	
    	
    	//错误返回代码
    	RESULT_CONTAINER.put(FFJsonFormatErrorID, FFJsonFormatErrorVALUE);
    	RESULT_CONTAINER.put(FFUnknownErrorID, FFUnknownErrorVALUE);
    	RESULT_CONTAINER.put(FFSystemErrorID, FFSystemErrorVALUE);
    	RESULT_CONTAINER.put(FFDBConnectionErrorID, FFDBConnectionErrorVALUE);
        
    }
    
}
