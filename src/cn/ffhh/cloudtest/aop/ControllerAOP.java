package cn.ffhh.cloudtest.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

import cn.ffhh.cloudtest.Exception.ServiceException;
import cn.ffhh.cloudtest.domain.ResponseBean;

/**
 *  拦截返回值类型为ResponseBean的请求使用AOP技术
 *  处理参数检验、结果封装以及异常处理
 * @author yfzhao
 *
 */
@Component
@Aspect
public class ControllerAOP {
	private static ThreadLocal<ResponseBean<Object>> responseBean = new ThreadLocal<ResponseBean<Object>>();//使用ThreadLocal变量确保线程安全
	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final ExecutableValidator validator = factory.getValidator().forExecutables();

	@Pointcut("execution(cn.ffhh.cloudtest.domain.ResponseBean<Object> cn.ffhh.cloudtest.web.*.*(..))")
	public void controllerAopPointcut() {
	}
	
	@SuppressWarnings("unchecked")
	@Around("controllerAopPointcut()")
	public Object arround(ProceedingJoinPoint point) {
		System.err.println("ControllerAop环绕增强开始");
		try {
			//控制器参数验证
			Object[] params = point.getArgs();
			//无参数直接执行
			if(params.length==0) {
				responseBean.set((ResponseBean<Object>)point.proceed());
				responseBean.get().setCode(ResponseBean.SUCCESS);
				responseBean.get().setMsg("success");
				System.err.println("无参：："+responseBean.get());
				return responseBean.get();
			}
			//javabean的参数检验
			for(Object param:params) {
				if(param instanceof BeanPropertyBindingResult) {
					BeanPropertyBindingResult result = (BeanPropertyBindingResult) param;
					if(result.hasErrors()) {
						List<ObjectError> errors = result.getAllErrors();
						Map<String, String> errorFiled = new HashMap<String,String>();
						for(ObjectError error:errors) {
							System.err.println(error.getCode());
							System.err.println(error.getDefaultMessage());
							System.err.println(error.getObjectName());
							System.err.println(error.getArguments());
							errorFiled.put(error.getCode(),error.getDefaultMessage());
						}
						responseBean.set(new ResponseBean<Object>());
						responseBean.get().setCode(ResponseBean.CHECK_FAIL);
						responseBean.get().setMsg("有参数错误");
						responseBean.get().setData(errorFiled);
						System.err.println("javaBean:::"+responseBean.get());
						return responseBean.get();
					}
				}
			}
			//基本类型参数检验
			//获取切入目标对象
			Object target = point.getThis();
			//获取切入方法
			Method method = ((MethodSignature)point.getSignature()).getMethod();
			//执行参数校验
			Set<ConstraintViolation<Object>> validateResult = validator.validateParameters(target, method, params);
			//检测失败，处理
			ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
			String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);//获取方法参数名称
			if(!validateResult.isEmpty()) {
				Map<String, String> errorFiled = new HashMap<String,String>();
				for(ConstraintViolation<Object> constraintViolation:validateResult) {
					PathImpl patrImpl = (PathImpl) constraintViolation.getPropertyPath();//获取校验的参数路径
					int paramIndex = patrImpl.getLeafNode().getParameterIndex();//获取校验的参数位置
					String paramName = parameterNames[paramIndex];//获取校验的参数名称
					errorFiled.put(paramName, constraintViolation.getMessage());
				}
				responseBean.set(new ResponseBean<Object>());
				responseBean.get().setCode(ResponseBean.CHECK_FAIL);
				responseBean.get().setMsg("有参数错误");
				responseBean.get().setData(errorFiled);
				System.err.println("基本类型:::"+responseBean.get());
				return responseBean.get();
			}
			//参数全部检验通过
			responseBean.set((ResponseBean<Object>) point.proceed());
			responseBean.get().setCode(ResponseBean.SUCCESS);
			responseBean.get().setMsg("success");
			System.err.println(responseBean.get());
			return responseBean.get();
		} catch (Throwable e) {
			e.printStackTrace();
			responseBean.set(controllerExceptionHandle(e));
			return responseBean.get();
		}
	}
	
	private ResponseBean<Object> controllerExceptionHandle(Throwable e) {
		ResponseBean<Object> errorResponseBean = new ResponseBean<>();
		if(e instanceof ServiceException) {
			errorResponseBean.setCode(ResponseBean.Fail);
			errorResponseBean.setMsg(e.getMessage());
		}else {
			errorResponseBean.setCode(ResponseBean.UNKNOWN_EXCEPTION);
			errorResponseBean.setMsg("未知错误");
		}
		return errorResponseBean;
	}
}
