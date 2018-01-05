package cn.itcast.service;

import java.util.List;

import cn.itcast.pojo.Item;

public interface ItemService {
	public List<Item> queryList();
	public Item quereyById(Integer id);
	public void Update(Item item);
}
