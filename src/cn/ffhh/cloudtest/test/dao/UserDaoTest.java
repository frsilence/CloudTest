package cn.ffhh.cloudtest.test.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ffhh.cloudtest.dao.IUserDao;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-security.xml"})
public class UserDaoTest {
	@Autowired
	private IUserDao userDao;
	@Test
	public void userSaveTest() {
		Department department = new Department();
		department.setId(1);
		User user = new User();
		user.setAccount(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		user.setAdduid(1);
		user.setAvatar("/sad/d1");
		user.setEmail(RandomStringUtils.randomAlphabetic(8));
		user.setEmailCode(RandomStringUtils.randomAlphabetic(4));
		user.setFreeze(false);
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user.setLastLoginTime(new Timestamp(new Date().getTime()));
		user.setUsername(RandomStringUtils.randomAlphabetic(4));
		user.setDepartment(department);
		System.out.println(userDao.save(user));
		System.out.println(user.getId());
	}
	@Test
	public void userDeleteTest() {
		System.out.println("删除数量："+userDao.delete(8));
	}
	@Test
	public void userUpdateTest() {
		System.out.println("更新前："+userDao.findByUserId(10));
		User user = userDao.findByUserId(10);
		user.setEmailCode("wewe");
		user.setFreeze(true);
		user.setAdduid(89);
		System.out.println("更新后："+userDao.update(user));
	}
	@Test
	public void userFindTest() {
		System.out.println(userDao.findByUserId(45));
		System.out.println(userDao.findByUsername("SWee"));
		System.out.println(userDao.findByEmail("URVZJbWN"));
		System.out.println(userDao.findAllUsers());
	}
	
	@Test
	public void test() {
		String aString = "adb";
		String bString = "21";
		Object bObject = aString;
		if(bObject instanceof String) {
			System.out.println("ok");
			bString = (String) bObject;
		}
		System.out.println(bString);
	}
}
