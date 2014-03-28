package com.tivenwang.converter.user;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import com.tivenwang.log4Api.AbstractLog4Api;

/** 
 * @author Pecan 
 * 类说明 
 */
public class UserLoginConverter extends AbstractLog4Api {

	private static Logger logger=Logger.getLogger(UserLoginConverter.class);
	private Map<Serializable, Serializable> map;
	private int resultId;
	private String simImsi;// sim卡号
	private String networkIp;// IP地址
	
	@Override
	public boolean analysisJsonString(String content) throws JSONException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getResult() {
		resultId=1000;
		return true;
	}
	@Override
	public Integer getResultId() {
		// TODO Auto-generated method stub
		return this.resultId;
	}

	@Override
	public Map<Serializable, Serializable> getParams() {
		// TODO Auto-generated method stub
		return map;
	}

	@Override
	public void logger(String content) {
		// TODO Auto-generated method stub
		logger.info(content);
	}
}
