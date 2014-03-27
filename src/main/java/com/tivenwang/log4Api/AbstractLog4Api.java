/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.log4Api;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tivenwang.filter.RequestFilter;

/**
 *
 * @author onlan_apple
 */
public abstract class AbstractLog4Api{
    
    private Integer USERID;//用户ID
    private String apiName;//接口地址
    private String paraValue;//输入参数或者输出参数resultid或者中间过程
    private String IP;//IP地址 
    private String memo;//其他
	public Logger logger;
	protected Integer returnTpye=null;
    
    //输入
    private static final byte LogType_input = 1;
    //输出
    private static final byte LogType_output = 2;
    //中间过程
    private static final byte LogType_middle = 3;
    
    public AbstractLog4Api(){
        if (RequestFilter.canGetUserid()){
            this.USERID = RequestFilter.getUserId();
        }else{
            this.USERID = 0;
        }

        this.IP = RequestFilter.getIP();
    }
    
    public String getIP() {
		return IP;
	}

	/**
     * 设置各个参数值,其中paraValue或者memo如果为空填NULL
     * @param (String)apiName
     * @param (int)inputOrOutput
     * @param (String)paraValue
     * @param (String)memo 
     */
    public void setPara(String apiName,String paraValue,String memo){
        this.apiName = apiName;
        this.paraValue = paraValue;
        this.memo = memo;
    }
    
    /**
     * 获取输入操作记录信息
     * @return 
     */
    public String getLogInfo_Input(){
        return getString(LogType_input);
    }
    
    /**
     * 获取输入操作记录信息
     * @param paraValue
     * @return 
     */
    public String getLogInfo_Input(String paraValue){
        return getString(LogType_input,paraValue);
    }
    
    /**
     * 获取输出操作记录信息
     * @return 
     */
    public String getLogInfo_Output(){
        return getString(LogType_output);
    }
    
    /**
     * 获取输出操作记录信息
     * @param paraValue
     * @return 
     */
    public String getLogInfo_Output(String paraValue){
        return getString(LogType_output,paraValue);
    }
    
    /**
     * 获取输出操作记录信息
     * @param resultId
     * @return 
     */
    public String getLogInfo_Output(long resultId){
        String value = "{\"resultId\":"+resultId+"}";
        return getString(LogType_output,value);
    }    
    
    /**
     * 获取中间过程操作记录信息
     * @return 
     */
    public String getLogInfo_Middle(){
        return getString(LogType_middle);
    }
    
    /**
     * 获取中间过程操作记录信息
     * @param paraValue
     * @return 
     */
    public String getLogInfo_Middle(String paraValue){
        return getString(LogType_middle,paraValue);
    }
    
    
    private String getString(int logtype){
   	 if (RequestFilter.canGetUserid()){
         this.USERID = RequestFilter.getUserId();
     }else{
         this.USERID = 0;
     }
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.USERID);
        sb.append(";");
        sb.append(this.apiName);
        sb.append(";");
        sb.append(logtype);
        sb.append(";");
        sb.append(this.paraValue);
        sb.append(";");
        sb.append(RequestFilter.getPlatForm());
        sb.append(";");
        sb.append(RequestFilter.getIP());
        if (memo != null && !"".equals(memo)){
            sb.append(";");
            sb.append(memo);
        }
        return sb.toString();
    }
    
    private String getString(int logtype,String paraValue){
    	 if (RequestFilter.canGetUserid()){
             this.USERID = RequestFilter.getUserId();
         }else{
             this.USERID = 0;
         }
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.USERID);
        sb.append(";");
        sb.append(this.apiName);
        sb.append(";");
        sb.append(logtype);
        sb.append(";");
        sb.append(paraValue);
        sb.append(";");
        sb.append(RequestFilter.getPlatForm());
        sb.append(";");
        sb.append(RequestFilter.getIP());
        if (memo != null && !"".equals(memo)){
            sb.append(";");
            sb.append(memo);
        }
        return sb.toString();
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }


    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setUSERID(Integer USERID) {
        this.USERID = USERID;
    }

	public abstract boolean analysisJsonString(String content) throws Exception;

	public abstract boolean getResult() throws Exception;
	
	public abstract Integer getResultId();

	public abstract Map<Serializable, Serializable> getParams();

	public Integer returnType() {
		// TODO Auto-generated method stub
		return returnTpye;
	}

	public abstract void logger(String content);
}
