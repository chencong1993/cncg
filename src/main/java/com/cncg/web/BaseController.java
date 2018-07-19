package com.cncg.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cncg.util.StringUtils;

public abstract class BaseController {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 自定义返回消息
	 * @param flag
	 * @param message
	 * @return
	 */
	public static Map<String,Object> resultMap(boolean flag,String message){
		Map<String,Object> map = new HashMap<String,Object> ();
		map.put("status", flag ? "1" : "0");
		map.put("message", StringUtils.isBlank(message) ? "操作成功！": message);
		return map;
	}
	
	/**
	 * 返回消息
	 * @param flag
	 * @param message
	 * @return
	 */
	public static Map<String,Object> resultMap(boolean flag){
		Map<String,Object> map = new HashMap<String,Object> ();
		map.put("status", flag ? "1" : "0");
		map.put("message", flag ? "操作成功！": "操作失败！");
		return map;
	}
}
