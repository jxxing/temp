package alipay;

import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;

/**
 * alibaba.fastjson
 * @author Moon
 *
 */
public class JsonUtil {
	
	
	/**
	 * 转换为Json
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		return JSON.toJSONString(obj);
	}
	
	/**
	 * 从Json转换为对象
	 * @param json
	 * @param classOfT
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, Type typeOfT){
		
		Object obj = JSON.parseObject(json, typeOfT);
		if (obj == null){
			return null;
		} else {
			return (T)obj;
		}
	}
	
	/**
	 * 从Json转换为对象
	* @Title: fromJson 
	* @param json
	* @return
	* T 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json){		
		Object obj = JSON.parseObject(json);
		if (obj == null){
			return null;
		} else {
			return (T)obj;
		}
	}
}
