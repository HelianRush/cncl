package cn.net.cncl.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.entity.WebInfo;
import cn.net.cncl.mapper.WebInfoMapper;
import cn.net.cncl.service.ManagerService;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	private static Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Autowired
	private WebInfoMapper webInfoMapper;

	/**
	 * 查询 网站信息
	 */
	@Override
	public JSONObject getWebInfo() {
		Map<String, Object> webInfo = webInfoMapper.getWebInfo();
		String jsonString = JSONObject.toJSONString(webInfo);
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		// System.out.println(jsonString);
		return jsonObject;
	}

	/**
	 * 查询 网站信息
	 */
	@Override
	public WebInfo getWebInfoObject() {
		WebInfo webInfoObject = webInfoMapper.getWebInfoObject();
		// System.out.println(webInfoObject);
		return webInfoObject;
	}

	/**
	 * 编辑 网站信息
	 */
	@Override
	public int updateWebInfo(WebInfo webInfo) {

		if (StringUtils.isBlank(webInfo.getWebName()) && StringUtils.isBlank(webInfo.getDomainName()) && StringUtils.isBlank(webInfo.getRecordsCode()) && StringUtils.isBlank(webInfo.getCompany()) && StringUtils.isBlank(webInfo.getLegalPerson()) && StringUtils.isBlank(webInfo.getWebEmail()) && StringUtils.isBlank(webInfo.getImageLogo())) {
			return 1;
		} else {
			if (StringUtils.isBlank(webInfo.getWebName()))
				webInfo.setWebName(null);

			if (StringUtils.isBlank(webInfo.getDomainName()))
				webInfo.setDomainName(null);

			if (StringUtils.isBlank(webInfo.getRecordsCode()))
				webInfo.setRecordsCode(null);

			if (StringUtils.isBlank(webInfo.getCompany()))
				webInfo.setCompany(null);

			if (StringUtils.isBlank(webInfo.getLegalPerson()))
				webInfo.setLegalPerson(null);

			if (StringUtils.isBlank(webInfo.getWebEmail()))
				webInfo.setWebEmail(null);

			if (StringUtils.isBlank(webInfo.getImageLogo()))
				webInfo.setImageLogo(null);

			// System.out.println(webInfo);
			return webInfoMapper.updateWebInfo(webInfo);
		}
	}

}
