package com.leon.passwd.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.leon.passwd.bean.Account;
import com.leon.passwd.dao.impl.AccountImpl;

public class AccountAction extends AbstractAction
{
	private static final long serialVersionUID = 5601982197489875967L;
	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);
	@Resource
	protected AccountImpl accountImpl = new AccountImpl(); // TODO

	private String username;
	private String password;

	@Action(value = "testv2")
	public void testv2()
	{
		logger.info("testv2 " + new Date());
		System.out.println("testv2 " + new Date());
	}

	@Action(value = "/sys/login")
	public void login()
	{
		logger.info(JSONObject.toJSONString(ServletActionContext.getRequest().getParameterMap()));
		JSONObject res = new JSONObject();
		try
		{
			Account account = this.accountImpl.queryAccount(this.username);
			if (account == null || !account.getPasswd().equalsIgnoreCase(this.password))
			{
				// 密码错误
				res.put("code", "1");
				res.put("msg", "帐号不存在或者密码错误");
			}
			else
			{
				// 正确了
				res.put("code", "0");
			}
		}
		catch (Exception e)
		{
			res.put("code", "2");
			logger.error("", e);
		}
		outputString(res.toJSONString());
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
