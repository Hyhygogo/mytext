package cn.itcast.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor1 implements HandlerInterceptor {

	// Controller执行前调用此方法
	// 返回true表示继续执行，返回false中止执行
	// 这里可以加入登录校验、权限拦截等
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("username");
		if (attribute != null) {
			// 如果用户名不为空,放行
			return true;
		} else {
			// 如果为空，转发到
			response.sendRedirect(request.getContextPath()+"/user/toLogin.action");
			return false;
		}

	}

	// controller执行后但未返回视图前调用此方法
	// 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	// controller执行后且视图返回后调用此方法
	// 这里可得到执行controller时的异常信息
	// 这里可记录操作日志
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
