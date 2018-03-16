package com.leon.passwd.util;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils
{
	private static CloseableHttpClient httpClient = HttpClients.createDefault();
	private static HttpClientContext context = new HttpClientContext();

	private HttpUtils()
	{

	}

	public static String sendGet(String url)
	{
		CloseableHttpResponse response = null;
		String content = null;
		try
		{
			HttpGet get = new HttpGet(url);
			response = httpClient.execute(get, context);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			return content;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (response != null)
			{
				try
				{
					response.close();
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		}
		return content;
	}

	public static String sendPost(String url, List<NameValuePair> nvps)
	{
		CloseableHttpResponse response = null;
		String content = null;
		try
		{
			HttpPost post = new HttpPost(url);
			if (nvps != null)
			{
				post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			}
			response = httpClient.execute(post, context);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			return content;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return content;
	}

}
