package com.reem.ushop.pojo;

import com.google.gson.annotations.SerializedName;

public class ResponseGet<E> {

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private E data;

	@SerializedName("status")
	private String status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}