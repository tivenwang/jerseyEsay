package com.tivenwang.resources;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;

import com.tivenwang.converter.ResultConfig;
import com.tivenwang.converter.ReturnResult;
import com.tivenwang.log4Api.AbstractLog4Api;

/**
 * @author Pecan 
 * 类说明
 */
public class ResourceService {

	private static Logger logger=Logger.getLogger(ResourceService.class);

	/**
	 * 接口调用的通用逻辑
	 * @param converter 逻辑类
	 * @param apiPath 调用接口地址
	 * @param content 输入内容
	 * @return 输出内容
	 */
	public static String callService(AbstractLog4Api converter, String apiPath,String content) {
		converter.setApiName(apiPath);
		converter.logger(converter.getLogInfo_Input(content));
		String returnString=null;
		try {
			if (!converter.analysisJsonString(content)||!converter.getResult()) {//分别执行json校验逻辑、业务逻辑
				returnString=ReturnResult.getFailStringById(converter.getResultId()); // 错误消息
			}else {
				returnString = ReturnResult.getSuccessStringByMap(converter.getResultId(), converter.getParams());  // 成功信息
				if (converter.returnType()!=null) {//判断 日志写入的内容  不为空日志只写入返回代号
					returnString = ReturnResult.getSuccessStringById(converter.getResultId());//成功信息ID
				}
			}
		} catch (JSONException e) {
			returnString=ReturnResult.getFailStringById(ResultConfig.FFJsonFormatErrorID); //5001 Json解析错误
		}catch (Exception e) {
			logger.error("", e);
			returnString=ReturnResult.getFailStringById(ResultConfig.FFUnknownErrorID); //5000 未知错误
		}
		converter.logger(converter.getLogInfo_Output(returnString));
		return returnString;
	}
}
