package com.leon.message.bean;

public class Account
{
	// 用户唯一id
	private long userid;
	// 用户姓名
	private String username;
	// 用户密码，用于登录
	private String passwd;

	public long getUserid()
	{
		return userid;
	}

	public void setUserid(long userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPasswd()
	{
		return passwd;
	}

	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}

}
