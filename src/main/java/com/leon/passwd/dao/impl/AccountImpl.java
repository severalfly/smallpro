package com.leon.passwd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.leon.passwd.bean.Account;
import com.leon.passwd.util.DBUtils;
import com.leon.passwd.util.ObjectUtil;

@Component("accountImpl")
public class AccountImpl
{
	public Account queryAccount(String username)
	{
		String sql = "select username, password from user where username = ?";
		List<Object[]> s = DBUtils.query(sql, new String[] { username });
		if (s == null || s.size() <= 0)
		{
			return null;
		}
		Account res = new Account();
		res.setUsername(ObjectUtil.getString(s.get(0)[0]));
		res.setPasswd(ObjectUtil.getString(s.get(0)[1]));
		return res;
	}
}
