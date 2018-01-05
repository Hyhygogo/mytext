package cn.itcast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.mapper.UserMapper;
import cn.itcast.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User quereyById(Integer id) {
		User user = userMapper.quereyById(id);
		return user;
	}

}
