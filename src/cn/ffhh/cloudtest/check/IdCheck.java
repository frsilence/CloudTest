package cn.ffhh.cloudtest.check;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class IdCheck implements Validator{


	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(Set.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		@SuppressWarnings("unchecked")
		Set<Integer> idSet = (Set<Integer>) arg0;
		if(idSet.size()<2) {
			arg1.rejectValue("id", "user.id", null, "用户id少于2个");
		}
	}
	
}
