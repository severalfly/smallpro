package com.leon.passwd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.leon.order12306.util.DBUtils;
import com.leon.order12306.util.ObjectUtil;
import com.leon.passwd.bean.Account;
import com.leon.passwd.bean.PasswdRecord;

@Component("accountImpl")
public class AccountImpl
{
	public Account queryAccount(String username)
	{
		String sql = "select username, password, id from user where username = ?";
		List<Object[]> s = DBUtils.query(sql, new String[] { username });
		if (s == null || s.size() <= 0)
		{
			return null;
		}
		Account res = new Account();
		res.setUsername(ObjectUtil.getString(s.get(0)[0]));
		res.setPasswd(ObjectUtil.getString(s.get(0)[1]));
		res.setUserid(ObjectUtil.getInt(s.get(0)[2]));
		return res;
	}

	public List<PasswdRecord> queryAllPasswds(int userid)
	{
		String sql = "select id, uname,login_name,e_mail,passwd,create_time,update_time from passwd_record where  owner_id = ?";
		List<Object[]> s = DBUtils.query(sql, new String[] { userid + "" });
		if (s == null || s.size() <= 0)
		{
			return new ArrayList<>();
		}
		List<PasswdRecord> list = new ArrayList<>(s.size());
		for (Object[] objects : s)
		{
			PasswdRecord p = new PasswdRecord();
			p.setId(ObjectUtil.getInt(objects[0]));
			p.setUname(ObjectUtil.getString(objects[1]));
			p.setLogin_name(ObjectUtil.getString(objects[2]));
			p.setE_mail(ObjectUtil.getString(objects[3]));
			p.setPasswd(ObjectUtil.getString(objects[4]));
			list.add(p);
		}
		return list;
	}
}
