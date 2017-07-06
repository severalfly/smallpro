package com.leon.passwd.bean;

public class PasswdRecord
{
	private long id;
	private String uname;// 用户名
	private String login_name;// 登录名
	private String e_mail;// 邮箱
	private String passwd;// 密码

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUname()
	{
		return uname;
	}

	public void setUname(String uname)
	{
		this.uname = uname;
	}

	public String getLogin_name()
	{
		return login_name;
	}

	public void setLogin_name(String login_name)
	{
		this.login_name = login_name;
	}

	public String getE_mail()
	{
		return e_mail;
	}

	public void setE_mail(String e_mail)
	{
		this.e_mail = e_mail;
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
