package cn.itcast.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAction {
	@RequestMapping("toLogin")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("login")
	public String login(String username, String password, HttpSession session) {
		// 校验用户登录
		System.out.println(username);
		System.out.println(password);

		// 把用户名放到session中
		session.setAttribute("username", username);

		return "redirect:/item/itemList.action";
	}

}
