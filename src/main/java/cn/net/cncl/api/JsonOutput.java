package cn.net.cncl.api;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.api.entity.CnclData;
import cn.net.cncl.api.entity.Head;
import cn.net.cncl.common.Constant;

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

public class JsonOutput {

	public static void main(String[] args) {
		JsonOutput output = new JsonOutput();
		output.outRequestJosn();
	}

	public void outRequestJosn() {

		CnclData cncl = new CnclData();
		Head head = new Head();
		Map<String, Object> query = new HashMap<String, Object>();
		JSONObject body = new JSONObject();

		head.setApiCode(Constant.API_WEB_INFO);
		head.setCode(Constant.SUCCESS.getCode());
		head.setMsg(Constant.SUCCESS.getMessage());

		query.put("param1", 1);
		query.put("param2", 2);
		query.put("param3", 3);

		cncl.setHead(head);
		cncl.setQuery(query);
		cncl.setBody(body);

		JSONObject cnclData = new JSONObject();

		cnclData.put("cnclData", cncl);
		System.out.println(cnclData.toJSONString());
	}

	// {"data":{"body":{},"head":{"apiCode":"webInfo","code":"200","msg":"成功"},"query":{}}}
	// {"data":{"body":{},"head":{"apiCode":"webInfo","code":"200","msg":"成功"},"query":{"param3":3,"param1":1,"param2":2}}}
	// {"head":{"msg":"成功","code":"200","apiCode":"webInfo"},"query":{"param3":3,"param1":1,"param2":2},"body":{"imageLogo":"logo1.png","webID":1,"webEmail":"abc@cncl.net.cn","recordsCode":"京ICP备案00000000号-1","webName":"中华名人库","domainName":"http://www.cncl.net.cn","legalPerson":"测试","company":"测试公司"}}
}
