package com.tivenwang.filter;

import com.tivenwang.converter.ResultConfig;
import com.tivenwang.memoryData.AuthorizationManager;
import com.tivenwang.util.NetUtil;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class RequestFilter implements Filter {
	public static Logger logger=Logger.getLogger(RequestFilter.class);
    public static final String USERTOKEN_HEADER = "userToken";
    public static final String USERID_HEADER = "userId";
    public static final String MACHINEIMEI_HEADER = "machineImei";
    public static final String VERSION_HEADER = "version";
    public static final String PLATFORM_HEADER = "platform";
    public static final String TOKENCHECKERROR_RESPONSE = "/webresources/tokenCheckError";
    public static final long TOKENVALIDATYTIME = 2592000000l;//TOKEN有效期30天（以毫秒为单位）
    private static final ThreadLocal<Integer> USERID = new ThreadLocal<Integer>();
    private static final ThreadLocal<String> PLATFORM = new ThreadLocal<String>();
    private static final ThreadLocal<Integer> ERRORID = new ThreadLocal<Integer>();
    private static final ThreadLocal<String> IMEI = new ThreadLocal<String>();
    private static final ThreadLocal<String> VERSION = new ThreadLocal<String>();
    private static final ThreadLocal<String> IP = new ThreadLocal<String>();

    public RequestFilter() {
    }

    public void init(FilterConfig config) throws ServletException {
    }
    
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        filterService((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void filterService(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        IP.set(NetUtil.getIpAddr(request));
        RequestDispatcher dispatch = request.getRequestDispatcher(TOKENCHECKERROR_RESPONSE);
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo(); 
        String requestUserId = request.getHeader(USERID_HEADER);    
        if((requestUserId != null)&&(!"".equals(requestUserId)))
        {
        	USERID.set(Integer.valueOf(requestUserId));
        }else{
        	USERID.set(0);
        }
        
        if (  pathInfo.startsWith("/tokenCheckError")
        		|| pathInfo.startsWith("/user/register")
        		|| pathInfo.startsWith("/user/login")
                || !path.startsWith("/webresources")) {
        	chain.doFilter(request, response);
        } else {
        	
            String requestUserToken = request.getHeader(USERTOKEN_HEADER);
            String requestMachineImei = request.getHeader(MACHINEIMEI_HEADER);
            String requestVersion = request.getHeader(VERSION_HEADER);
            if ((requestUserToken != null) && (!"".equals(requestUserToken)) && (requestUserId != null)
                    && (!"".equals(requestUserId)) && (requestMachineImei != null)
                    && (!"".equals(requestMachineImei)) && (requestVersion != null)
                    && (!"".equals(requestVersion))) {
                String lastLoginInfo = AuthorizationManager.getAuthorizationInfo(requestUserId);
                if (lastLoginInfo != null) {
                	if (lastLoginInfo.equals("-1")){  //未知的软件平台
                		ERRORID.set(ResultConfig.FFHttpHeaderNullID);// = 5100;缺少用于鉴权的HTTP请求头
                		dispatch.forward(request, response);
                		return;
                	}
                    String lastLoginInfoo[] = lastLoginInfo.split(";");
                    String lastLoginTime = lastLoginInfoo[0];
                    String lastUserToken = lastLoginInfoo[1];
                    String lastMachineImei = lastLoginInfoo[2];
                    long lastTime = Long.parseLong(lastLoginTime);
                    long currentTime = getTime();
                    
                    if (lastUserToken.equals(requestUserToken) && (currentTime - lastTime < TOKENVALIDATYTIME)) {
                    	//第三方登录失效问题的处理
                        long expires=0;
                        if (lastLoginInfoo.length==4) {
    						expires=Long.valueOf(lastLoginInfoo[3]);
    						if (currentTime - lastTime < expires) {
    	                        USERID.set(Integer.valueOf(requestUserId));
    	                        IMEI.set(requestMachineImei);
    	                        VERSION.set(requestVersion);
    	                        chain.doFilter(request, response);
    						}else {
    							ERRORID.set(ResultConfig.FFHttpHeaderTokenInvalidID);
    							logger.warn("鉴权失败："+ResultConfig.FFHttpHeaderTokenInvalidID);
    	                        dispatch.forward(request, response);
							}
    					}else {
    						USERID.set(Integer.valueOf(requestUserId));
    						IMEI.set(requestMachineImei);
    						VERSION.set(requestVersion);
    						chain.doFilter(request, response);
						}
                    } else {
                        if ((!lastUserToken.equals(requestUserToken)) && (!lastMachineImei.equals(requestMachineImei))) {
                            //ERRORID.set(-101);
                            ERRORID.set(ResultConfig.FFHttpHeaderTokenInvalidAndHasLoginAnotherPhoneID);
                            logger.warn("鉴权失败："+ResultConfig.FFHttpHeaderTokenInvalidAndHasLoginAnotherPhoneID);
                            //error(response,"Token失效，该账号已在另一台设备登陆，如非您本人操作，请尽快修改密码","-101");
                        } else {
                            if (lastUserToken.equals(requestUserToken)) {
                                //ERRORID.set(-100);
                            	ERRORID.set(ResultConfig.FFHttpHeaderTokenInvalidID);
                            	logger.warn("鉴权失败："+ResultConfig.FFHttpHeaderTokenInvalidID);
                                //error(response,"Token已过有效期,请重新登陆","-100");
                            } else {
                                if (currentTime - lastTime < TOKENVALIDATYTIME) {
                                	// 5103;//Token验证失败,请重新登陆
                                    ERRORID.set(ResultConfig.FFHttpHeaderTokenErrorID);//5103
                                    logger.warn("鉴权失败："+ResultConfig.FFHttpHeaderTokenErrorID);
                                } else {
                                	//5101;//Token失效，请重新登陆
                                	logger.warn("鉴权失败："+ResultConfig.FFHttpHeaderTokenInvalidID);
                                    ERRORID.set(ResultConfig.FFHttpHeaderTokenInvalidID);
                                }
                            }
                        }
                        dispatch.forward(request, response);
                    }
                } else {
                    //ERRORID.set(-100);
                    ERRORID.set(ResultConfig.FFHttpHeaderTokenInvalidID);
                    logger.warn("鉴权失败："+ResultConfig.FFHttpHeaderTokenInvalidID);
                    dispatch.forward(request, response);
                    //error(response,"Token失效，请重新登陆","-100");
                }
            } else {
                //ERRORID.set(-99);
                ERRORID.set(ResultConfig.FFHttpHeaderNullID);
                logger.warn("鉴权失败："+ResultConfig.FFHttpHeaderNullID);
                dispatch.forward(request, response);
                //error(response,"缺少用于鉴权的HTTP请求头","-99");
            }
        }
    }

    public static long getTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static Integer getUserId() {
        return USERID.get();
    }

    public static boolean canGetUserid() {
        if (null == USERID.get()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static String getPlatForm(){
    	return PLATFORM.get();
    }

    public static Integer getErrorId() {
        return (Integer) ERRORID.get();
    }

    public static String getImei() {
        return IMEI.get();
    }

    public static String getVersion() {
        return VERSION.get();
    }

    public static String getIP() {
        return IP.get();
    }
}
