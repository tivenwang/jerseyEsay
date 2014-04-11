package com.tivenwang.converter.user;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import com.tivenwang.converter.ResultConfig;
import com.tivenwang.filter.RequestFilter;
import com.tivenwang.log4Api.AbstractLog4Api;

/**
 * @author Pecan 类说明
 */
public class UserLogoutConverter extends AbstractLog4Api {

	private static Logger logger = Logger.getLogger(UserLogoutConverter.class);
	private int resultId;

	@Override
	public boolean analysisJsonString(String content) throws JSONException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getResult() throws Exception {
		// TODO Auto-generated method stub
		Integer userId = RequestFilter.getUserId();
		resultId=ResultConfig.TTLogoutSuccessID ;//= 1107 退出成功
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
		return null;
	}

	@Override
	public void logger(String content) {
		// TODO Auto-generated method stub
		logger.info(content);
	}
}
