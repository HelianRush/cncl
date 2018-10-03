package cn.net.cncl.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.net.cncl.common.SessionUser;
import cn.net.cncl.entity.AdminUser;

public class SessionInterceprot implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		String requestURI = request.getRequestURI();
		System.out.println("[Session拦截器]cn.net.cncl.common.SessionInterceprot.postHandle().requestURI = " + requestURI);

		System.out.println("[Session拦截器]cn.net.cncl.common.SessionInterceprot.postHandle() = " + "执行");
		AdminUser user = SessionUser.getUser(request);
		System.out.println("[Session拦截器]cn.net.cncl.common.SessionInterceprot.postHandle() = " + user + " 用户状态");

		if (SessionUser.getUserStatus(request)) {
			System.out.println("[Session拦截器-]cn.net.cncl.common.SessionInterceprot.postHandle() = " + "用户状态：登录");
		} else {
			System.out.println("[Session拦截器-]cn.net.cncl.common.SessionInterceprot.postHandle()= " + "Session：清空");
			modelAndView.setViewName("/300");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
