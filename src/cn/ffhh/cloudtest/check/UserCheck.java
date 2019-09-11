package cn.ffhh.cloudtest.check;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cn.ffhh.cloudtest.domain.UserUpdate;


public class UserCheck implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(UserUpdate.class);
	}
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", "user.username.require","用户名不能为空++spring_vaildate");
		ValidationUtils.rejectIfEmpty(errors, "password", "user.password.require","密码不能为空++spring_vaildate");
		ValidationUtils.rejectIfEmpty(errors, "id", "user.id.require","id不能为空++spring_vaildate");
		ValidationUtils.rejectIfEmpty(errors, "account", "user.account.require","工号不能为空++spring_vaildate");
		ValidationUtils.rejectIfEmpty(errors, "email", "user.email.require","email不能为空++spring_vaildate");
		ValidationUtils.rejectIfEmpty(errors, "department", "user.department.require","department不能为空++spring_vaildate");
		UserUpdate userUpdate = (UserUpdate) target;
		if (userUpdate.getFreeze()==null || userUpdate.getFreeze()==true) {
			errors.rejectValue("freeze", "user.freeze.require", null, "用户未提供冻结信息，或者为true");
		}
	}
	
}
