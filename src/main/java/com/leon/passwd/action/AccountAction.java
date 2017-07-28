package com.leon.passwd.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.leon.passwd.bean.Account;
import com.leon.passwd.bean.PasswdRecord;
import com.leon.passwd.dao.impl.AccountImpl;

public class AccountAction extends AbstractAction
{
	private static final long serialVersionUID = 5601982197489875967L;
	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);
	@Resource
	protected AccountImpl accountImpl = new AccountImpl();

	private String username;
	private String password;
	private String ids;
	private String unmae;
	private String loginName;
	private String passwordRecord;

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
				List<PasswdRecord> list = this.accountImpl.queryAllPasswds(account.getUserid());
				HttpServletRequest request = ServletActionContext.getRequest();
				request.getSession().setAttribute("list", list);
				request.getSession().setAttribute("user", account.getUsername());
				request.getSession().setAttribute("userid", account.getUserid());

			}
		}
		catch (Exception e)
		{
			res.put("code", "2");
			logger.error("", e);
		}
		outputString(res.toJSONString());
	}


	@Action(value = "/addPasswd")
	public void addPasswd()
	{

		System.out.println("addPasswd " + this.loginName + " " + this.unmae + " " + this.passwordRecord);
		try
		{
			PasswdRecord pr = new PasswdRecord();
			pr.setId(System.currentTimeMillis());
			int ownid = (int) ServletActionContext.getRequest().getSession().getAttribute("userid");
			pr.setOwnId(ownid);
			pr.setPasswd(this.passwordRecord);
			pr.setUname(this.unmae);
			pr.setLogin_name(this.loginName);
			this.accountImpl.insertPasswordRecord(pr);
			List<PasswdRecord> list = this.accountImpl.queryAllPasswds(ownid);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("list", list);
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
	}

	@Action(value = "/deletePasswd")
	public void deletePasswd()
	{
		System.out.println("deletePasswd " + this.ids);
		this.accountImpl.deletePr(this.ids);
		int ownid = (int) ServletActionContext.getRequest().getSession().getAttribute("userid");
		List<PasswdRecord> list = this.accountImpl.queryAllPasswds(ownid);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("list", list);
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

	public String getIds()
	{
		return this.ids;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public String getUnmae()
	{
		return this.unmae;
	}

	public void setUnmae(String unmae)
	{
		this.unmae = unmae;
	}

	public String getLoginName()
	{
		return this.loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getPasswordRecord()
	{
		return this.passwordRecord;
	}

	public void setPasswordRecord(String passwordRecord)
	{
		this.passwordRecord = passwordRecord;
	}

}
