package com.cncg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Spring MVC的拦截器
 * @author CNCG
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = Logger.getLogger(LoginInterceptor.class);
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
	/**
	 * 预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        logger.info("==> 请求开始:"+request.getRequestURI());
        long beginTime = System.currentTimeMillis();//开始时间
        startTimeThreadLocal.set(beginTime); 		//线程绑定变量（该数据只有当前请求的线程可见）
        if(session.getAttribute("user")==null){
			request.getRequestDispatcher("/login").forward(request,response);
			return false;
		}
		return true;
	}
	
	/**
	 * 后处理（调用了Service并返回ModelAndView，但未进行页面渲染）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null && modelAndView.getViewName() != null)
			logger.info("=== 返回视图："+modelAndView.getViewName());
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * 返回处理（已经渲染页面）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long endTime = System.currentTimeMillis(); // 2、结束时间
		long second = (endTime - beginTime)/1000;	//秒
		double max = Runtime.getRuntime().maxMemory()/1024/1024;
		double total = Runtime.getRuntime().totalMemory()/1024/1024;
		double free = Runtime.getRuntime().freeMemory()/1024/1024;
		double used = total-free;
		logger.info("<== 请求结束："+request.getRequestURI()+"  耗时："+second+"秒，最大内存"+max+"M 已分配内存"+"["+used+"M/"+total+"M]");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
