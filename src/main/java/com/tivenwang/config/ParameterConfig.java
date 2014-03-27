package com.tivenwang.config;

import java.math.BigDecimal;

/** 
 * @author Pecan 
 * 类说明 
 * 
 * 请分别添加 参数的名字、描述、数值
 * 
 * 
 * 添加完整后到com.tivenwang.servlet.ParameterInitServlet.paddingData()中增添初始化代码
 */
public class ParameterConfig {
	
	    public static final String DEFAULT_SENDSMS_TO_ONE_USER_IN_AN_HOUR_NAME = "DEFAULT_SENDSMS_TO_ONE_USER_IN_AN_HOUR";
	    public static final String DEFAULT_SENDSMS_TO_ONE_USER_IN_AN_HOUR_REMARK = "一个小时内给单个用户发送短信验证码的最大条数（默认5条）";
	    public static Integer DEFAULT_SENDSMS_TO_ONE_USER_IN_AN_HOUR = 5;
	    //短信验证码的有效期（默认30分钟）
	    public static final String DEFAULT_SMS_PERIOD_OF_VALIDITY_NAME = "DEFAULT_SMS_PERIOD_OF_VALIDITY";
	    public static final String DEFAULT_SMS_PERIOD_OF_VALIDITY_REMARK = "短信验证码的有效期（默认30分钟）";
	    public static Integer DEFAULT_SMS_PERIOD_OF_VALIDITY = 30;//以分钟为单位

	    //显示周围用户X坐标的范围差
	    public static final String User_XLocation_Range_NAME="User_XLocation_Range";
	    public static final String User_XLocation_Range_REMARK="显示周围用户X坐标的范围";
	    public static BigDecimal User_XLocation_Range_VALUE = new BigDecimal(100);
	    //显示周围用户Y坐标的范围差
	    public static final String User_YLocation_Range_NAME="User_YLocation_Range";
	    public static final String User_YLocation_Range_REMARK="显示周围用户Y坐标的范围";
	    public static BigDecimal User_YLocation_Range_VALUE = new BigDecimal(100);
	    //首次登陆获取积分
	    public static final String BIND_PHONE_POINT_NAME="BIND_PHONE_POINT";
	    public static final String BIND_PHONE_POINT_REMARK="用户绑定手机获取的积分";
	    public static Integer BIND_PHONE_POINT_VALUE = 100;

	    //快递员保证金，提现时的底额
	    public static final String EXPRESS_SECURITY_DEPOSIT_NAME="EXPRESS_SECURITY_DEPOSIT";
	    public static final String EXPRESS_SECURITY_DEPOSIT_REMARK="快递员保证金，提现时的底额";
	    public static BigDecimal EXPRESS_SECURITY_DEPOSIT_VALUE = new BigDecimal(300);

	    //推送标题
	    public static final String PUSH_CONTENT_NAME="PUSH_CONTENT";
	    public static final String PUSH_CONTENT_REMARK="快递员通知栏显示内容";
	    public static String PUSH_CONTENT_VALUE = "有活儿啦！快来抢！";
	    
	    //文件上传
	    //上传文件大小限制
	    public static final String UPLOAD_LimitSize_NAME="UPLOAD_LimitSize";
	    public static final String UPLOAD_LimitSize_REMARK="上传文件大小限制";
	    public static  int UPLOAD_LimitSize_VALUE=240;
	    
	    //server根路径对应IP
	    public static final String SERVER_FILEIP_NAME="SERVER_FILEIP";
	    public static final String SERVER_FILEIP_REMARK="server根路径对应IP";
	    public static  String SERVER_FILEIP_VALUE="http://192.168.0.132:8080";
	    //站内信单页信件数量
	    public static final String MESSAGE_PAGE_SIZE_NAME="MESSAGE_PAGE_SIZE";
	    public static final String MESSAGE_PAGE_SIZE_REMARK="站内信单页信件数量";
	    public static  Integer MESSAGE_PAGE_SIZE_VALUE=20;
	    
	    //默认分页每页的大小
	    public static final String DEFAULT_PAGESIZE_NAME="DEFAULT_PAGESIZE";
	    public static final String DEFAULT_PAGESIZE_REMARK="默认分页每页的大小";
	    public static  Integer DEFAULT_PAGESIZE_VALUE = 10;     
            
	    //快递员得到快递费（首重费用+续重费用）的百分比
	    public static final String FEE_PERCENT_NAME="FEE_PERCENT";
	    public static final String FEE_PERCENT_REMARK="快递员得到快递费（首重费用+续重费用）的百分比";
	    public static String FEE_PERCENT_VALUE="0.55";            

	    //快递员得到保价费的百分比
	    public static final String INSURE_PERCENT_NAME="INSURE_PERCENT";
	    public static final String INSURE_PERCENT_REMARK="快递员得到保价费的百分比";
	    public static String INSURE_PERCENT_VALUE="0.8";
}
