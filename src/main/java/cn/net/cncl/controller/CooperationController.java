package cn.net.cncl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.cncl.entity.Cooperation;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.service.CooperationService;

@Controller
@RequestMapping("/CooperationCtl")
public class CooperationController {

	private static Logger logger = LoggerFactory.getLogger(CooperationController.class);

	/**
	 * CooperationService
	 */
	@Autowired
	private CooperationService cooperationService;

	/**
	 * ImagesMapper
	 */
	@Autowired
	private ImagesMapper imagesMapper;

	// ---------- main ----------
	public static void main(String[] args) {
	}
	// ---------- main ----------

	@RequestMapping(value = "/showManagerEnterEdit")
	public String showManagerEnterEdit(HttpServletRequest request, HttpSession session, Model model) {
		Cooperation show = cooperationService.queryCooperationById(1000000000000001L);
		model.addAttribute("cooperation", show);
		session.setAttribute("cooperationId", 1000000000000001L);
		return "manager_coopeerationEdit";
	}

	// 根据ID查询
	@ResponseBody
	@RequestMapping(value = "/getCooperationById")
	public Cooperation getCooperationById(HttpSession session) {
		Long cooperationId = (Long) session.getAttribute("cooperationId");
		if (null == cooperationId)
			cooperationId = 1000000000000001L;

		Cooperation cooperation = null;
		if (null != cooperationId) {
			cooperation = cooperationService.queryCooperationById(cooperationId);
		}
		session.removeAttribute("cooperationId");
		return cooperation;
	}

	// 保存 - 新建、修改
	@RequestMapping(value = "/editCooperation")
	public String editCooperation(HttpServletRequest request, HttpSession session, Cooperation cooperation, Model model) {
		// 查询数据是否存在
		List<Cooperation> flagList = cooperationService.selectCooperation();
		int flag = 0;
		if (0 < flagList.size()) {
			cooperation.setCooperationId(1000000000000001L);
			flag = cooperationService.updateCoperation(cooperation);
		} else {
			flag = cooperationService.insertCoperation(cooperation);
		}

		if (0 < flag) {
			Cooperation show = cooperationService.queryCooperationById(1000000000000001L);
			model.addAttribute("cooperation", show);
		}

		return "manager_coopeerationEdit";
	}

}
