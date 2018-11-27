package cn.net.cncl.api.tools;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.api.entity.CnclData;
import cn.net.cncl.api.entity.Head;

/**
 * @1、JSON转字符串 如：object.toJSONString()
 * @2、获取JSON的某个数据值 如：JSONObject object = jsonObj.getJSONObject("request")
 * @3、对象转JSON串 如：JSONObject.parseObject(json);
 * @4、JSON转对象 如：RequestData requestData =
 *            JSONObject.parseObject(object.toJSONString(), RequestData.class)
 * @5、Map转JSON 如：String json = JSONObject.toJSONString(map1); JSONObject
 *             jsonObject = JSONObject.parseObject(json)
 * @6、字符串转JSON 如：JSONObject.parseObject(json);
 * 
 * @注意：
 * @1、传送数据给第三方（发送请求或响应数据）时，要做URLEncoder.encode("业务数据","UTF-8")进行格式化，否则会造成参数传送过程中丢失
 * @2、接收方不需要做URLDecoder.decode("接收的业务数据", "UTF-8")，如果接收的是加密数据，那么做过URLDecoder后，可能会造成解密失败
 */
public class ToolRequest {

	/**
	 * 获取JSON数据
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static CnclData getJosn(JSONObject requestJson) {
		CnclData data = null;
		JSONObject dataJson = requestJson.getJSONObject("data");
		if (null != dataJson) {
			data = JSONObject.parseObject(dataJson.toJSONString(), CnclData.class);
			// System.out.println(data);
		} else {
			data = new CnclData();
			Head head = new Head();
			head.setApiCode("URL_ERROR");
			head.setCode("DATA_ERROR");
			head.setMsg("请求数据不正确！");
			data.setHead(head);
			data.setQuery(new HashMap<String, Object>());
			data.setBody(new JSONObject());
			// System.out.println(data);
		}
		return data;
	}

}
