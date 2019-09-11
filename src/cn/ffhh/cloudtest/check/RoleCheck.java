package cn.ffhh.cloudtest.check;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoleCheck implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
	}

}
