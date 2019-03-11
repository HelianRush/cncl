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
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.net.cncl.api.entity.CnclData;
import cn.net.cncl.api.entity.Head;
import cn.net.cncl.api.tools.ToolRequest;
import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.Celebritys;
import cn.net.cncl.entity.News;
import cn.net.cncl.entity.Special;
import cn.net.cncl.service.CelebritysService;
import cn.net.cncl.service.CooperationService;
import cn.net.cncl.service.ImagesService;
import cn.net.cncl.service.ManagerService;
import cn.net.cncl.service.NewsService;
import cn.net.cncl.service.NewsTypeService;
import cn.net.cncl.service.SpecialIndexService;
import cn.net.cncl.service.SpecialService;
import cn.net.cncl.service.SpecialTypeService;

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
	@Autowired
	SpecialIndexService specialIndexService;
	@Autowired
	SpecialTypeService specialTypeService;
	@Autowired
	SpecialService specialService;
	@Autowired
	CooperationService cooperationService;

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
					List<Special> specialList = specialService.querytSpecialByName(params);

					JSONArray clelbrityListToJosn = new JSONArray();
					for (Celebritys celebrity : clelbrityList) {
						String jsonString = JSONObject.toJSONString(celebrity, SerializerFeature.DisableCircularReferenceDetect);
						JSONObject parseObject = JSONObject.parseObject(jsonString);
						clelbrityListToJosn.add(parseObject);
					}

					JSONArray newsListToJson = new JSONArray();
					for (News news : newsList) {
						String jsonString = JSONObject.toJSONString(news, SerializerFeature.DisableCircularReferenceDetect);
						JSONObject parseObject = JSONObject.parseObject(jsonString);
						newsListToJson.add(parseObject);
					}

					JSONArray specialListToJson = new JSONArray();
					for (Special special : specialList) {
						String jsonString = JSONObject.toJSONString(special, SerializerFeature.DisableCircularReferenceDetect);
						JSONObject parseObject = JSONObject.parseObject(jsonString);
						specialListToJson.add(parseObject);
					}

					body.put("clelbrityList", clelbrityListToJosn);
					body.put("newsList", newsListToJson);
					body.put("specialList", specialListToJson);
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

				// 首页专题推荐8个
				case Constant.API_TOP_SPECIAL_LIST:
					body = specialService.apiTopSpecialList(params);
					break;

				// 专题页 首页
				case Constant.API_SPECIAL_INDEX:
					body = specialIndexService.querySpecialIndex();
					break;

				// 专题页 展示类别
				case Constant.API__SPECIAL_TYPE:
					body = specialTypeService.querytSpecialType();
					break;

				// 专题页 展示列表 根据类别 全部 分页
				case Constant.API__SPECIAL_LIST_BY_TYPE:
					body = specialService.querySpecialByType(params);
					break;

				// 专题 单条
				case Constant.API_QUERY_SPECIAL_BY_ID:
					body = specialService.querySpecialById(params);
					break;

				// 入驻页
				case Constant.API_QUERY_COOPERATION:
					body = cooperationService.queryCooperation();
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

}

/**
 * @1、JSON转字符串 如：object.toJSONString()<br>
 * 
 * @2、获取JSON的某个数据值 如：JSONObject object = jsonObj.getJSONObject("request")<br>
 * 
 * @3、对象转JSON串 如：JSONObject.parseObject(json);<br>
 * 
 * @4、JSON转对象 如：RequestData requestData
 *            =JSONObject.parseObject(object.toJSONString(),
 *            RequestData.class)<br>
 * 
 * @5、Map转JSON 如：String json = JSONObject.toJSONString(map1); <br>
 *             JSONObject jsonObject = JSONObject.parseObject(json)<br>
 * 
 * @6、字符串转JSON 如：JSONObject.parseObject(json);<br>
 * 
 * 			@注意：<br>
 * @1、传送数据给第三方（发送请求或响应数据）时，要做URLEncoder.encode("业务数据","UTF-8")进行格式化，否则会造成参数传送过程中丢失<br>
 * @2、接收方不需要做URLDecoder.decode("接收的业务数据", "UTF-8")，如果接收的是加密数据，那么做过URLDecoder后，可能会造成解密失败<br>
 */
