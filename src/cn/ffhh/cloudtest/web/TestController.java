package cn.ffhh.cloudtest.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@ResponseBody
public class TestController {
	@RequestMapping("/loginuser")
	public String loginuser(HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		return ((UserDetails)securityContextImpl.getAuthentication().getPrincipal()).toString();
	}
	
	@RequestMapping("/loginuser2")
	public String loginuser2(Authentication authentication) {
		return authentication.toString();
	}
}
