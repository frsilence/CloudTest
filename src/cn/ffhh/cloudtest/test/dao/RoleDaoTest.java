package cn.ffhh.cloudtest.test.dao;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ffhh.cloudtest.dao.IRoleDao;
import cn.ffhh.cloudtest.dao.IUserDao;
import cn.ffhh.cloudtest.domain.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RoleDaoTest {
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;
	@Test
	public void roleSaveTest() {
		Role role = new Role();
		role.setRoleName(RandomStringUtils.randomAlphabetic(8));
		role.setRoleDesc(RandomStringUtils.randomAlphabetic(26));
		role.setAddUser(userDao.findByUserId(2));
		System.out.println("save num:"+roleDao.save(role));
		System.out.println(role.getId());
	}
	@Test
	public void roleDeleteTest() {
		System.out.println("删除数量："+roleDao.delete(4));
	}
	@Test
	public void roleUpdateTest() {
		System.out.println("更新前："+roleDao.findByRoleId(2));
		Role role = roleDao.findByRoleId(2);
		role.setRoleName("update"+new Random().nextInt(100));
		role.setRoleDesc(null);
		role.setAddUser(userDao.findByUserId(2));
		roleDao.update(role);
		System.out.println("更新："+roleDao.update(role));
		System.out.println("更新后："+roleDao.findByRoleId(2));
	}
	@Test
	public void roleFindTest() {
		System.out.println(roleDao.findByRoleId(3));
		System.out.println(roleDao.findByRoleName("up"));
		System.out.println(roleDao.findByUserId(2));
	}
}
