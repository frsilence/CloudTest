package cn.ffhh.cloudtest.test.dao;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ffhh.cloudtest.dao.IPermissionDao;
import cn.ffhh.cloudtest.domain.Permission;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class PermissionDaoTest {
	@Autowired
	private IPermissionDao permissionDao;
	@Test
	public void perimissionSaveTest() {
		Permission permission = new Permission();
		permission.setPermissionName(RandomStringUtils.randomAlphabetic(8));
		permission.setPermissionDesc(RandomStringUtils.randomAlphabetic(26));
		permission.setTypes(1);
		permission.setPartentId(0);
		permission.setUrl(RandomStringUtils.randomAlphabetic(26));
		permission.setMethod("get");
		permission.setListorder(1);
		permission.setDisplay(false);
		permission.setIconname("ok");
		System.out.println("save num:"+permissionDao.save(permission));
		System.out.println(permission.getId());
	}
	@Test
	public void perimissionDeleteTest() {
		System.out.println("删除数量："+permissionDao.delete(4));
	}
	@Test
	public void perimissionUpdateTest() {
		System.out.println("更新前："+permissionDao.findByPermissionId(2));
		Permission permission = permissionDao.findByPermissionId(2);
		permission.setPermissionName("update"+new Random().nextInt(100));
		permission.setPermissionDesc(null);
		permissionDao.update(permission);
		System.out.println("更新："+permissionDao.update(permission));
		System.out.println("更新后："+permissionDao.findByPermissionId(2));
	}
	@Test
	public void perimissionFindTest() {
		System.out.println(permissionDao.findByPermissionId(3));
		System.out.println(permissionDao.findByPermissionname("up"));
	}
}
