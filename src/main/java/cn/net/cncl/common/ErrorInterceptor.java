package cn.net.cncl.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ErrorInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		System.out.println("[拦截器]cn.net.cncl.common.ErrorInterceptor.postHandle() = " + "执行");

		if (response.getStatus() == 500) {
			modelAndView.setViewName("/500");
		} else if (response.getStatus() == 404) {
			modelAndView.setViewName("/404");
		}

		// if (SessionUser.getUserStatus(request)) {
		// System.out.println("[拦截器-]cn.net.cncl.common.ErrorInterceptor.postHandle() =
		// " + "用户状态：登录");
		// } else {
		// System.out.println("[拦截器-]cn.net.cncl.common.ErrorInterceptor.postHandle() =
		// " + "Session：清空");
		// modelAndView.setViewName("/300");
		// }
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
