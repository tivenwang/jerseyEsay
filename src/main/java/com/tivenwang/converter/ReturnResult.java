package com.tivenwang.converter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;

import com.tivenwang.util.JSONUtil;

/**
 * @author Pecan 类说明
 * 
 *处理返回值的逻辑
 * 
 */
public class ReturnResult extends ResultConfig {

	public static Logger logger = Logger.getLogger(ReturnResult.class);

	/**
	 * 根据返回ID获取一个result=true 的Result
	 * @param resultId
	 * @return
	 */
	public static Result getSuccessObjectById(Integer resultId) {
		return getResult(resultId, true);
	}


	/**
	 * 根据返回ID获取一个result=true 的json
	 * @param resultId
	 * @return
	 */
	public static String getSuccessStringById(Integer resultId) {
		try {
			return JSONUtil.entityToJSONString(getSuccessObjectById(resultId));
		} catch (JsonGenerationException e) {
			logger.error("",e);
			e.printStackTrace();
			return "{\"result\":false,\"resultId\":5518,\"resultMSG\":\"系统转换Result到Json异常\"}";
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
			return "{\"result\":false,\"resultId\":5518,\"resultMSG\":\"系统转换Result到Json异常\"}";
		}
	}

	/**
	 * 根据返回ID获取一个result=false 的Result
	 * @param resultId
	 * @return
	 */
	public static Result getFailObjectById(Integer resultId) {
		return getResult(resultId, false);
	}

	/**
	 * 根据返回ID获取一个result=false 的json
	 * @param resultId
	 * @return
	 */
	public static String getFailStringById(Integer resultId) {
		try {
			return JSONUtil.entityToJSONString(getFailObjectById(resultId));
		} catch (JsonGenerationException e) {
			logger.error("",e);
			e.printStackTrace();
			return "{\"result\":false,\"resultId\":5518,\"resultMSG\":\"系统转换Result到Json异常\"}";
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
			return "{\"result\":false,\"resultId\":5518,\"resultMSG\":\"系统转换Result到Json异常\"}";
		}
	}

	/**
	 * 返回一个result=true的json
	 * 将map中的key和bean转换为json
	 * @param resultId
	 * @param prams
	 * @return
	 */
	public static String getSuccessStringByMap(Integer resultId,Map<Serializable, Serializable> prams) {
		StringBuffer stringBuffer = new StringBuffer(getSuccessStringById(resultId));
		try {
			if (prams != null && prams.size() != 0) {
				Set<Entry<Serializable, Serializable>> entrys = prams.entrySet();
				stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
				for (Entry<Serializable, Serializable> entry : entrys) {
					stringBuffer.append(",\"").append(entry.getKey().toString()).append("\":").append(JSONUtil.entityToJSONString(entry.getValue()));
				}
				stringBuffer.append("}");
			}
		} catch (JsonGenerationException ex) {
			logger.error("",ex);
			return "{\"result\":false,\"resultId\":5518,\"resultMSG\":\"系统转换Result到Json异常\"}";
		} catch (IOException ex) {
			logger.error("",ex);
			return "{\"result\":false,\"resultId\":5518,\"resultMSG\":\"系统转换Result到Json异常\"}";
		}

		return stringBuffer.toString();
	}

	/**
	 * 返回一个指定result、resultId的Result
	 * @param resultId
	 * @param flag
	 * @return
	 */
	private static Result getResult(Integer resultId, boolean flag) {
		Result result = new Result();
		result.setResult(flag);
		result.setResultId(resultId);
		result.setResultMSG(RESULT_CONTAINER.get(resultId));
		return result;
	}
}
