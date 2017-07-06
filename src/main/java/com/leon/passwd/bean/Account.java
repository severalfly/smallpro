package com.leon.passwd.bean;

public class Account
{
	// 用户唯一id
	private int userid;
	// 用户姓名
	private String username;
	// 用户密码，用于登录
	private String passwd;

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
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
