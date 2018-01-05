package cn.itcast.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.mapper.UserMapper;
import cn.itcast.pojo.Order;
import cn.itcast.pojo.User;
import cn.itcast.pojo.UserIvo;

public class UserDaoTest {
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

	}

	@Test
	public void testQuereyById() {
		 UserMapper userDao = this.applicationContext.getBean(UserMapper.class);
		 
	
	}

	@Test
	public void testQuereyByUserName() {
		UserMapper userDao = this.applicationContext.getBean(UserMapper.class);
		UserIvo userIvo = new UserIvo();
		User user = new User();
		user.setUsername("王");
		userIvo.setUser(user);
		List<User> list = userDao.quereyByUserName(userIvo);
		for (User user2 : list) {
			System.out.println(user2);
		}
	}

	@Test
	public void testQuereyOrderUser() {
		UserMapper userDao = this.applicationContext.getBean(UserMapper.class);
		List<Order> list = userDao.quereyUserOrderByid();
		for (Order order : list) {
			System.out.println(order);
		}

	}

	@Test
	public void testquereyUserBysex() {
		UserMapper userDao = this.applicationContext.getBean(UserMapper.class);
		User user = new User();
		user.setUsername("张");
		List<User> list = userDao.quereyUserBysex(user);
		for (User order : list) {
			System.out.println(order);
		}

	}

	@Test
	public void testquereyUserByids() {
		UserMapper userMapper = this.applicationContext.getBean(UserMapper.class);
		UserIvo userIvo = new UserIvo();
		List<Integer> ids=new ArrayList();
		ids.add(1);
		userIvo.setIds(ids);
		List<User> users = userMapper.quereyUserIds(userIvo);
		for (User user : users) {
			System.out.println(user);
		}
		
	}

}
