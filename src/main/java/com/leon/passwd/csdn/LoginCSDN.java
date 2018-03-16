package com.leon.passwd.csdn;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.leon.passwd.util.HttpUtils;

public class LoginCSDN
{
	private static final String LOGIN_URL = "https://passport.csdn.net/account/login";
	private static String username = "";
	private static String password = "";
	private static String lt = "";
	private static String execution = "";
	private static String _eventId = "";

	public void fetchnecessaryParam()
	{
		String html = HttpUtils.sendGet(LOGIN_URL);
		Document doc = Jsoup.parse(html);
		Element form = doc.select(".user-pass").get(0);
		lt = form.select("input[name=lt]").get(0).val();
		execution = form.select("input[name=execution]").get(0).val();
		_eventId = form.select("input[name=_eventId]").get(0).val();
	}

	public boolean mockLogin()
	{
		System.out.println("longin start...");
		boolean result = false;
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("lt", lt));
		nvps.add(new BasicNameValuePair("execution", execution));
		nvps.add(new BasicNameValuePair("_eventId", _eventId));
		String ret = HttpUtils.sendPost(LOGIN_URL, nvps);
		System.out.println(ret);
		if (ret.indexOf("redirect_back") > -1)
		{
			// 登录成功
			System.out.println("login success");
			result = true;
		}
		else if (ret.indexOf("登录太频繁") > -1)
		{
			System.out.println("登录太频繁");
		}
		else
		{
			System.out.println("登录失败");
		}
		return result;
	}

	public static void main(String[] args)
	{
		LoginCSDN lc = new LoginCSDN();
		lc.fetchnecessaryParam();
		lc.mockLogin();
	}
}
