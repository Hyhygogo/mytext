package cn.itcast.mapper;

import java.util.List;

import cn.itcast.pojo.Order;
import cn.itcast.pojo.User;
import cn.itcast.pojo.UserIvo;

public interface UserMapper {
	public User quereyById(int id);

	public List<User> quereyByUserName(UserIvo userIvo);

	public List<Order> quereyUserOrderByid();

	public List<User> quereyUserBysex(User user);

	public List<User> quereyUserIds(UserIvo userIvo);
}
