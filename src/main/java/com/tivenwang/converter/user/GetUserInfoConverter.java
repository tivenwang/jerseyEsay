package com.tivenwang.converter.user;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import com.tivenwang.converter.ResultConfig;
import com.tivenwang.log4Api.AbstractLog4Api;

/**
 * @author Pecan 类说明
 */
public class GetUserInfoConverter extends AbstractLog4Api {
	private static Logger logger = Logger.getLogger(GetUserInfoConverter.class);
	private int resultId;
	private Integer userInfoId;
	private Map<Serializable, Serializable> map;

	@Override
	public boolean analysisJsonString(String content) throws JSONException {
		try {
			userInfoId = Integer.valueOf(content);
		} catch (NumberFormatException e) {
			userInfoId = -1;
		}
		return true;
	}

	@Override
	public boolean getResult() throws Exception {
		resultId=ResultConfig.TTBindingQuerySuccessID;// = 1110 查询成功
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
