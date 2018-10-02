package cn.net.cncl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping(value = "/show")
public class ShowController {

	/**
	 * @Title showIndex
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问Index.html<br>
	 *             首页
	 */
	@RequestMapping(value = "/index")
	public String showIndex() {
		return "index";
	}

}
