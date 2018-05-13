package com.leon.message.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class AbstractAction extends ActionSupport
{

	/**
	 * json格式输出
	 * @param string 内容
	 * @param cross 是否允许跨域
	 */
	protected void outputString(String string, boolean cross)
	{
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			if (cross)
			{
				response.addHeader("Access-Control-Allow-Origin", "*");
			}
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(string);
			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	protected void outputString(String string)
	{
		outputString(string, false);
	}
}
