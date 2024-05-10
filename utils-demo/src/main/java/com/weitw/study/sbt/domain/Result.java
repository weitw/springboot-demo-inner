package com.weitw.study.sbt.domain;

import java.io.Serializable;

/**
 * AJAX统一返回数据模型
 * code:0  data:对应实际数据结构(Any)
 * code:-1 data:错误描述信息
 * code:1 data:失败描述信息（-1是一些异常，1是业务上失败，不是异常）
 */
public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4376168366173465183L;
	private int code;
	private String message;
	private Object data;

	public Result() {
	}

	public Result(int code) {
		this.code = code;
	}

	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static Result ok() {
		return new Result(0);
	}


	public static Result ok(Object data) {
		return ok().data(data);
	}

	public static Result error() {
		return new Result(-1);
	}
	public  Result error(int code,String message) {
		this.setCode(code);
		this.setMessage(message);
		return this;
	}

	public static Result error(Object data) {
		return error().data(data);
	}

	public static Result fail() {
		return new Result(1);
	}

	public Result fail(int code,String message) {
		this.setCode(code);
		this.setMessage(message);
		return this;
	}

	public static Result fail(Object data) {
		return fail().data(data);
	}

    public static Result invalidSession() {
        return new Result(-2);
    }
    public static Result invalidSession(Object data) {
        return invalidSession(-2).data(data);
    }

	public static Result code(int code) {
		return new Result(code);
	}

	public Result data(Object data) {
		this.data = data;
		return this;
	}

	public Result message(String message) {
		this.message = message;
		return this;
	}

	public int getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
