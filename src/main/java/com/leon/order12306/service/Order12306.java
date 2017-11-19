package com.leon.order12306.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class Order12306
{
	@Test
	public void testEncode() throws UnsupportedEncodingException
	{
		String station = "上海虹桥";
		System.out.println(URLEncoder.encode(station, "utf-8"));
		System.out.println(URLEncoder.encode(station, "gb2312"));
	}
	public static void main(String[] args)
	{
		PrintWriter out = null;
		BufferedReader in = null;
		try
		{
			//			HttpGet httpGet = new HttpGet("https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&0.8946813635463624");
			HttpGet httpGet = new HttpGet("http://dynamic.12306.cn/mapping/kfxt/zwdcx/LCZWD/cx.jsp?cz=%C9%CF%BA%A3%BA%E7%C7%C5&cc=g9&cxlx=0&rq=2017-10-18&czEn=-E4-B8-8A-E6-B5-B7-E8-99-B9-E6-A1-A5&tp=1508333945052");
			//HttpClient  
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//			HttpGet httpGet = new HttpGet("http://www.gxnu.edu.cn/default.html");
			System.out.println(httpGet.getRequestLine());
			try
			{
				//执行get请求  
				HttpResponse httpResponse = httpClient.execute(httpGet);
				//获取响应消息实体  
				HttpEntity entity = httpResponse.getEntity();
				//响应状态  
				System.out.println("status:" + httpResponse.getStatusLine());
				//判断响应实体是否为空  
				if (entity != null)
				{
					System.out.println("contentEncoding:" + entity.getContentEncoding());
					System.out.println("response content:" + EntityUtils.toString(entity));
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					//关闭流并释放资源  
					httpClient.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
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
