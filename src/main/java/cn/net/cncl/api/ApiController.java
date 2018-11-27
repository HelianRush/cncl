package cn.net.cncl.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.api.entity.CnclData;
import cn.net.cncl.api.entity.Head;
import cn.net.cncl.api.tools.ToolRequest;
import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.Celebritys;
import cn.net.cncl.entity.News;
import cn.net.cncl.service.CelebritysService;
import cn.net.cncl.service.ImagesService;
import cn.net.cncl.service.ManagerService;
import cn.net.cncl.service.NewsService;
import cn.net.cncl.service.NewsTypeService;

@RestController
@RequestMapping("/cnclApi")
public class ApiController {
	private final static Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	ManagerService webInfoService;
	@Autowired
	CelebritysService celebritysService;
	@Autowired
	ImagesService imagesService;
	@Autowired
	ManagerService managerService;
	@Autowired
	NewsService newsService;
	@Autowired
	NewsTypeService newsTypeService;

	@RequestMapping("/getData")
	public String getData(@RequestBody JSONObject requestJosn) {

		CnclData data = null;
		Head head = null;
		Map<String, Object> params = null;
		JSONObject body = null;
		String responesJson = null;

		try {

			data = ToolRequest.getJosn(requestJosn);
			head = data.getHead();
			params = data.getQuery();

			// 如果head有数据，根据ApiCode调用相应接口业务
			if (Constant.SUCCESS.getCode().equals(head.getCode())) {

				switch (head.getApiCode()) {

				// 网站基本信息
				case Constant.API_WEB_INFO:
					body = webInfoService.getWebInfo();
					break;

				// 名人库精确搜索
				case Constant.API_QUERY_BY_CLELBRITY:
					body = celebritysService.queryByClelbrity(params);
					break;

				// 站内搜索
				case Constant.API_QUERY_ALL:
					body = new JSONObject();

					List<Celebritys> clelbrityList = celebritysService.queryClelbrityByName(params);
					List<News> newsList = newsService.queryNewsByName(params);

					JSONArray clelbrityListToJosn = new JSONArray();
					for (Celebritys celebrity : clelbrityList) {
						clelbrityListToJosn.add(celebrity);
					}

					JSONArray newsListToJson = new JSONArray();
					for (News news : newsList) {
						newsListToJson.add(news);
					}

					body.put("clelbrityList", clelbrityListToJosn);
					body.put("newsList", newsListToJson);
					break;

				// 滚动图片展示
				case Constant.API_TOP_IMAGES:
					body = imagesService.getApiTopImages();
					break;

				// 资讯菜单
				case Constant.API_NEWS_TYPE_LIST:
					body = newsTypeService.getNewsTypeList();
					break;

				// 资讯推荐列表
				case Constant.API_TOP_NEWS:
					body = newsService.getTopNews(params);
					break;

				// 名人库推荐列表
				case Constant.API_TOP_CELEBRITY:
					body = celebritysService.getTopCelebrity();
					break;

				// 名人库查询
				case Constant.API_QUERY_CELEBRITY_BY_NAME:
					body = celebritysService.queryClelbrityByNames(params);
					break;

				// 名人库列表
				case Constant.API_CELEBRITY_LIST:
					body = celebritysService.celebrityList(params);
					break;

				// 单条名人库
				case Constant.API_QUERY_CELEBRITY_BY_ID:
					body = celebritysService.apiQueryCelebritysByID(params);
					break;

				// 资讯列表
				case Constant.API_NEWS_LIST:
					body = newsService.newsList(params);
					break;

				// 单条资讯
				case Constant.API_QUERY_NEWS_BY_ID:
					body = newsService.apiQueryNewsById(params);
					break;

				default:
					head.setApiCode("null");
					head.setCode("null");
					head.setMsg("没有匹配的接口编码");
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.debug(e.getMessage());
		} finally {
			data.setBody(body);
			responesJson = ((JSONObject) JSONObject.toJSON(data)).toJSONString();
		}

		return responesJson;
	}

	/**
	 * 1.网站基本信息<br>
	 * 接口地址：/api/webInfo <br>
	 * 请求类型：POST <br>
	 * 状态码：200 <br>
	 * 简介：获取网站信息<br>
	 * 网站名称、域名、备案编号、公司名称、法人、图片、网站邮箱。
	 */
	@RequestMapping(value = "/webinfo")
	public String apiWebInfo() {
		return null;
	}

}
