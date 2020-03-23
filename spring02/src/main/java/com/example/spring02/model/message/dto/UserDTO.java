package com.example.spring02.model.message.dto;

public class UserDTO {
	private String userid;
	private String upw;
	private String uname;
	private int upoint;
	//getter,setter,toString()
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + "]";
	}
}
