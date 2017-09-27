package com.leon.order12306.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Order12306
{
	public static void main(String[] args)
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		//设置证书
		System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\megan\\12306.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		try
		{
			URL realUrl = new URL("https://kyfw.12306.cn/otn/login/loginAysnSuggest");
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print("loginUserDTO.user_name=aaa&userDTO.password=123456&randCode=182,51,19,130");
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null)
			{
				result += line;
			}
			System.out.println(result);
		}
		catch (Exception e)
		{
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

}
