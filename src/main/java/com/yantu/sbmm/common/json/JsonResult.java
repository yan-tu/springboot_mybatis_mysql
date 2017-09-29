package com.yantu.sbmm.common.json;

import com.yantu.sbmm.common.enums.ResultEnum;

public class JsonResult {
	private String code;
	private String message;
	private Object data;
	
	public JsonResult() {
		this.setCode(ResultEnum.SUCCESS);
		this.setMessage("成功！");
		
	}
	
	public JsonResult(ResultEnum code) {
		this.setCode(code);
		this.setMessage(code.msg());
	}
	
	public JsonResult(ResultEnum code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}
	
	public JsonResult(ResultEnum code, String message, Object data) {
		this.setCode(code);
		this.setMessage(message);
		this.setData(data);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(ResultEnum code) {
		this.code = code.val();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
