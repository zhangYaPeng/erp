package com.zyp.erp.utils.response;

import java.io.Serializable;

public class ResponseObject<T> implements Serializable {

	private static final long serialVersionUID = 8937154110439921721L;

	private MSG msg = new MSG();

	private T data;

	public ResponseObject() {
	}

	public void setErrorCode() {
		this.msg.setSuccess(false);
		this.msg.setErrorCode();
	}

	public void setNotLoginErrorCode() {
		this.msg.setSuccess(false);
		this.msg.setNotLoginErrorCode();
	}

	public void putMsg(String info, boolean isSuccess) {
		putMsg(info, isSuccess, (isSuccess == true ? 0 : -1));
	}

	public void putMsg(String info, boolean isSuccess, int code) {
		msg.setCode(code);
		msg.setInfo(info);
		msg.setSuccess(isSuccess);
	}

	public MSG getMsg() {
		return msg;
	}

	public void setMsg(MSG msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
