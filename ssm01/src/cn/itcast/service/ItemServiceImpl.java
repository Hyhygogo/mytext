package cn.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.mapper.ItemMapper;
import cn.itcast.pojo.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public List<Item> queryList() {
		return itemMapper.selectByExample(null);
	}

	@Override
	public Item quereyById(Integer id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	//修改
	@Override
	public void Update(Item item) {
		itemMapper.updateByPrimaryKeySelective(item);
		
	}

}
