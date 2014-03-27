/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.config;

import java.util.HashMap;

/**
 *
 * @author 
 */
public class GlobalConfig {

    
    //过滤器中设置request,response的字符编码
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final int An_Hour = 3600000;//一个小时，用毫秒表示
    public static final int Half_Hour = 1800000;//一个小时，用毫秒表示
    
    /**
     * 图片临时存储路径
     */
    public final static HashMap<String,String>servletPathMap;
    public final static HashMap<Byte,Integer>userPointMap;
    public final static HashMap<String,Integer>rateSquareMap;
    static{
        servletPathMap = new HashMap<String, String>();
        userPointMap = new HashMap<Byte,Integer>();
        rateSquareMap = new HashMap<String,Integer>();
    }
    
}
