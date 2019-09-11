package cn.ffhh.cloudtest.domain;

public class ResponseBean<T> {
	public static final int NO_LOGIN = -1;
	public static final int SUCCESS = 0;
	public static final int Fail = 1;
	public static final int CHECK_FAIL = 2;
	public static final int NO_PERMISSION = 3;
	public static final int UNKNOWN_EXCEPTION = 99;
	private String msg;
	private Integer code;
	private T data;
	public ResponseBean() {
		super();
	}
	public ResponseBean(T data) {
		super();
		this.data = data;
	}
	public ResponseBean(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = UNKNOWN_EXCEPTION;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseBean [msg=" + msg + ", code=" + code + ", data=" + data + "]";
	}
	
	
}
