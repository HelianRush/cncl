package cn.net.cncl.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.mapper.WebInfoMapper;
import cn.net.cncl.service.ManagerService;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	private static Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Autowired
	private WebInfoMapper webInfoMapper;

	/**
	 * @1、JSON转字符串 如：object.toJSONString()<br>
	 * @2、获取JSON的某个数据值 如：JSONObject object = jsonObj.getJSONObject("request")<br>
	 * @3、对象转JSON串 如：JSONObject.parseObject(json);<br>
	 * @4、JSON转对象 如：RequestData requestData =
	 *            JSONObject.parseObject(object.toJSONString(),
	 *            RequestData.class)<br>
	 * @5、Map转JSON 如：String json = JSONObject.toJSONString(map1); <br>
	 *             JSONObject jsonObject = JSONObject.parseObject(json)<br>
	 * @6、字符串转JSON 如：JSONObject.parseObject(json);<br>
	 * 
	 * 			@注意：<br>
	 * @1、传送数据给第三方（发送请求或响应数据）时，要做URLEncoder.encode("业务数据","UTF-8")进行格式化，否则会造成参数传送过程中丢失<br>
	 * @2、接收方不需要做URLDecoder.decode("接收的业务数据", "UTF-8")，如果接收的是加密数据，那么做过URLDecoder后，可能会造成解密失败<br>
	 */

	/**
	 * 查询 网站信息
	 */
	@Override
	public String getWebInfo() {
		Map<String, Object> webInfo = webInfoMapper.getWebInfo();
		String jsonString = JSONObject.toJSONString(webInfo);
		System.out.println(jsonString);
		return jsonString;
	}

}
