package cn.itcast.converter;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.exception.MyException;

public class CustomerException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String msg;
		// 如果是自定义异常,读取异常信息
		if (ex instanceof MyException) {
			msg = ex.getMessage();

		}
		// 是运行时异常，从错误堆栈中读取异常信息
		else {
			// 一个stringbuffer类用来拼接错误信息，而且还加了一把锁
			StringWriter out = new StringWriter();
			//
			PrintWriter writer = new PrintWriter(out);
			// 打印堆栈中的信息
			ex.printStackTrace(writer);
			msg = out.toString();
		}
		//获取异常信息，传递到错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg",msg);
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
