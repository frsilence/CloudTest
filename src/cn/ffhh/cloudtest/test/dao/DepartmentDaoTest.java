package cn.ffhh.cloudtest.test.dao;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ffhh.cloudtest.dao.IDepartmentDao;
import cn.ffhh.cloudtest.dao.IUserDao;
import cn.ffhh.cloudtest.domain.Department;
import cn.ffhh.cloudtest.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DepartmentDaoTest {
	@Autowired
	private IDepartmentDao departmentDao;
	@Autowired
	private IUserDao userDao;
	@Test
	public void departmentSaveTest() {
		Department department = new Department();
		department.setDepartmentName(RandomStringUtils.randomAlphabetic(8));
		department.setLevel(new Random().nextInt(3)+1);
		department.setListOrder(new Random().nextInt(10));
		department.setParentId(0);
		User user = new User();
		user.setId(2);
		department.setAddUser(user);
		System.out.println("save num:"+departmentDao.save(department));
		System.out.println(department.getId());
	}
	@Test
	public void departmentDeleteTest() {
		System.out.println("删除数量："+departmentDao.delete(4));
	}
	@Test
	public void departmentUpdateTest() {
		System.out.println("更新前："+departmentDao.findByDepartmentId(2));
		Department department = departmentDao.findByDepartmentId(2);
		department.setDepartmentName("update"+new Random().nextInt(100));
		department.setLevel(null);
		department.setAddUser(userDao.findByUserId(6));
		departmentDao.update(department);
		System.out.println("更新："+departmentDao.update(department));
		System.out.println("更新后："+departmentDao.findByDepartmentId(2));
	}
	@Test
	public void departmentFindTest() {
		System.out.println(departmentDao.findByDepartmentId(3));
		System.out.println(departmentDao.findByDepartmentname("up"));
	}
}
