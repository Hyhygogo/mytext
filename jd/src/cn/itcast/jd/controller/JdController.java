package cn.itcast.jd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jd.pojo.Result;
import cn.itcast.jd.service.SearchService;

@Controller
@RequestMapping("search")
public class JdController {
	@Autowired
	private SearchService  searchService;
	@RequestMapping("list")
	public String queryList(Model model, String queryString, String catalog_name, String price, String sort,
			Integer page) throws Exception{
		//传入条件，查询result
		Result  result =this.searchService.queryProduct(queryString,catalog_name,price,sort,page);
		model.addAttribute("result",result);
		model.addAttribute("catalog_name",catalog_name);
		model.addAttribute("queryString",queryString);
		model.addAttribute("price",price);
		model.addAttribute("sort",sort);
		model.addAttribute("page",page);
		return "product_list";
	}
	
}
