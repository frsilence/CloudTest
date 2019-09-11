package cn.ffhh.cloudtest.domain;

import java.util.Map;

public class ResponseBodyFormat {
	private String status;
	private String msg;
	private Map<String, Object> data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseBodyFormat [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
