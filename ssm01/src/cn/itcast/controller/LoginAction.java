package cn.itcast.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.pojo.Item;
import cn.itcast.pojo.ItemIvo;
import cn.itcast.service.ItemService;
import cn.itcast.service.UserService;

@Controller
@RequestMapping("/item")
public class LoginAction {

	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;

	@RequestMapping(value = { "list", "listAll" })
	public String queryList(Model model) throws Exception {

		List<Item> list = itemService.queryList();

		model.addAttribute("itemList", list);
		return "itemList";
	}

	// @RequestMapping("/itemEdit")
	// public String edit(HttpServletRequest request, Model model) {
	// String ids = request.getParameter("id");
	// Integer id = Integer.valueOf(ids);
	// Item item = itemService.quereyById(id);
	// model.addAttribute("item", item);
	// return "itemEdit";
	// }

	/*@RequestMapping("/itemEdit")
	// @RequestParam:默认请求参数 value：参数名 required：是否必须 defaultValue默认值
	public String edit1(@RequestParam(value = "id", required = true, defaultValue = "1") int id, Model model) {
		Item item = itemService.quereyById(id);
		model.addAttribute("item", item);
		return "itemEdit";
	}*/
	//加入@RequestBody
	@RequestMapping("/itemEdit")
	@ResponseBody
	public Item edit1(@RequestBody Item item,Model model) {
		model.addAttribute("item", item);
		return item;
	}

	// 修改重定向到编辑页面
	/*
	 * @RequestMapping("/updateItem") public String update1(Item item) {
	 * itemService.Update(item); return
	 * "redirect:itemEdit.action?id="+item.getId(); }
	 */
	// 修改请求转发到编辑页面
	@RequestMapping("/updateItem")
	public String update(@RequestBody Item item, MultipartFile multipartFile) throws Exception {
		// 图片上传
		// 设置图片名称，使用Uuid，不可重复
		String picName = UUID.randomUUID().toString();
		// 获取文件上传名称
		String filename = multipartFile.getOriginalFilename();
		// 获取文件后缀,截取字符串，文件的最后一个点 .jpg
		String lastname = filename.substring(filename.lastIndexOf("."));
		// 上传
		multipartFile.transferTo(new File("f:/img/" + picName + lastname));
		// 设置图片地址
		item.setPic(picName + lastname);
		itemService.Update(item);
		return "forward:itemEdit.action";
	}
	// 得到list数组对象实现批量的修改删除等
	/*
	 * @RequestMapping("/updateItem") public String update(ItemIvo item) {
	 * java.util.List<Item> list = item.getItemList(); return "success"; }
	 */

	@RequestMapping(value = "queryItem", method = RequestMethod.POST)
	public String query(@RequestBody ItemIvo itemivo, Integer[] ids) {
		Integer[] id = itemivo.getIds();
		for (Integer integer : id) {
			System.out.println("aaa" + integer);
		}
		for (Integer integer : ids) {
			System.out.println("bbb" + integer);
		}
		return "success";
	}
	@RequestMapping("item/{id}")
	public String queryItemById(@PathVariable() Integer id) {
		Item item = itemService.quereyById(id);
		return " success";
	}
}	
