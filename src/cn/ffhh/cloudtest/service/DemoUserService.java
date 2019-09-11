package cn.ffhh.cloudtest.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ffhh.cloudtest.dao.IUserDao;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.User;

@Service
public class DemoUserService {
	@Autowired
	private IUserDao userDao;
	//如果想要对手动throw的异常(无论是java.lang.Exception还是自定义异常)也进行事务回滚，需要修改事务配置 ,添加roll-backfor属性：
	//<tx:method name="do*" propagation="REQUIRED" rollback-for="java.lang.Exception"/>
	//注解需要添加相应属性
	//默认
	//@Transactional(rollbackFor = RuntimeException.class)
	//指定回滚的异常
	@Transactional(rollbackFor = Exception.class)
	public void test(Integer id) throws Exception{
		User myuser = userDao.findByUserId(id);
		myuser.setEmail("up:"+RandomStringUtils.randomAlphabetic(4));
		userDao.update(myuser);
		System.out.println(userDao.findByUserId(id));
		System.err.println("D1");
		User user = new User();
		Department department = new Department();
		department.setId(1);
		user.setAccount(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		user.setAdduid(3);
		user.setAvatar("/sad/d1");
		user.setEmail(RandomStringUtils.randomAlphabetic(8));
		user.setEmailCode(RandomStringUtils.randomAlphabetic(4));
		user.setFreeze(false);
		user.setPassword("qw");
		user.setLastLoginTime(new Timestamp(new Date().getTime()));
		user.setUsername(RandomStringUtils.randomAlphabetic(4));
		user.setDepartment(department);
		try {
			int i = 1/0;
			System.out.println(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("error");
		}
		userDao.save(user);
		System.out.println(userDao.findByUserId(user.getId()));
	}
}
