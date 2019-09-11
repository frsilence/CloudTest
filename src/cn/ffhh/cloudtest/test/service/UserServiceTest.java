package cn.ffhh.cloudtest.test.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;

import cn.ffhh.cloudtest.Exception.UserException;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.User;
import cn.ffhh.cloudtest.domain.UserSave;
import cn.ffhh.cloudtest.domain.UserSearch;
import cn.ffhh.cloudtest.service.DemoUserService;
import cn.ffhh.cloudtest.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserServiceTest {
	@Autowired
	private DemoUserService testuserService;
	@Autowired
	private IUserService userService;
	@Test
	public void test() {
		try {
			testuserService.test(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAllByPageInfoTest() throws ParseException {
		/*
		 * userService.findUsersByPageInfo(account, email, username, departmentId,
		 * startDate, endDate, roleId, freeze);
		 */
		try {
			//List<User> users = userService.findUsersByPageInfo(null, null, null, 
					//null, new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-12"), 1, null);
			UserSearch params = new UserSearch();
			params.setUsername("s");
			params.setRoleId(1);
			params.setPageNum(1);
			params.setPageSize(3);
			List<User> users = userService.findUsersByPageInfo(params);
			System.out.println(users);
			System.err.println(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-11"));
			System.err.println("num:"+users.size());
			System.err.println("out:");
			for(User user:users) {
				System.out.println(user);
			}
			PageInfo<User> info = new PageInfo<User>(users);
			System.out.println(info);
			System.out.println(info.getList());
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveUserTest() {
		User user = new User();
		Department department = new Department();
		department.setId(1);
		user.setAccount(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		//user.setAccount("1234");
		user.setAdduid(1);
		user.setAvatar("/sad/d1");
		user.setEmail(RandomStringUtils.randomAlphabetic(8));
		user.setEmailCode(RandomStringUtils.randomAlphabetic(4));
		user.setFreeze(false);
		user.setPassword("qw");
		user.setLastLoginTime(new Timestamp(new Date().getTime()));
		user.setUsername(RandomStringUtils.randomAlphabetic(4));
		user.setDepartment(department);
		try {
			UserSave userSave = new UserSave();
			userService.addUser(userSave);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteUserTest() {
		try {
			userService.deleteUser(new HashSet<Integer>(100));
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
}
