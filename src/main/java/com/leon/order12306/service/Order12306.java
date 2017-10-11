package com.leon.order12306.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class Order12306
{
	public static void main(String[] args)
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		//设置证书
		//		System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\megan\\12306.keystore");
		//		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		try
		{
			//			URL realUrl = new URL("https://kyfw.12306.cn/otn/login/loginAysnSuggest");
			//			URL realUrl = new URL("https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&0.8946813635463624");
			//			// 打开和URL之间的连接
			//			URLConnection conn = realUrl.openConnection();
			//			// 设置通用的请求属性
			//			conn.setRequestProperty("accept", "*/*");
			//			conn.setRequestProperty("connection", "Keep-Alive");
			//			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//			// 发送POST请求必须设置如下两行
			//			conn.setDoOutput(true);
			//			conn.setDoInput(true);
			//			// 获取URLConnection对象对应的输出流
			//			out = new PrintWriter(conn.getOutputStream());
			//			// 发送请求参数
			//			out.print("loginUserDTO.user_name=aaa&userDTO.password=123456&randCode=182,51,19,130");
			//			// flush输出流的缓冲
			//			out.flush();
			//			// 定义BufferedReader输入流来读取URL的响应
			//			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//			String line;
			//			while ((line = in.readLine()) != null)
			//			{
			//				result += line;
			//			}
			//			System.out.println(result);

			HttpGet get = new HttpGet("https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&0.8946813635463624");
			//创建HttpClientBuilder  
			//			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			//HttpClient  
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(get);
			HttpGet httpGet = new HttpGet("http://www.gxnu.edu.cn/default.html");
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

	@Test
	public void queryPayid()
	{
		//		String time = ObjectUtil.getString(new Timestamp(System.currentTimeMillis()));
		//		System.out.println(time.substring(11, 16));
		//		GtgjCouponConfig gcc = GtgjCouponConfigCache.instance.getGtgjCouponConfigById("7731bfe1545546ab8a6d29f8f763ef8f");
		//		Pair<Res, List<com.huoli.gtgj.common.bean.coupon.ReturnCashCoupon>> pair = CommonActivityPro.sendGtgjCouponV3(null, gcc, accountGtgj, accountGtgj.getUid(), accountGtgj.getP(), 2);
		//		logger.info("发三个帐号已提交的，" + phone + " " + com.alibaba.fastjson.JSONObject.toJSONString(pair));
		// TODO Auto-generated method stub
		try
		{
			//			System.out.println(InetAddress.getLocalHost().getHostAddress());
			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_CEILING));
			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_DOWN));
			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_FLOOR));
			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_HALF_DOWN));
			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_HALF_EVEN));
			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_HALF_UP));
			//			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_UNNECESSARY));
			System.out.println(new BigDecimal("51.5").divide(new BigDecimal(20), BigDecimal.ROUND_UP));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
