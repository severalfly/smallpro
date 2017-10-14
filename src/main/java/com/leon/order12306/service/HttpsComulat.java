package com.leon.order12306.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class HttpsComulat
{
	public static void main(String[] args) throws Exception
	{
		//		URL myURL = new URL("https://www.12306.cn");
		//
		//		// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
		//		HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
		//
		//		// 取得该连接的输入流，以读取响应内容
		//		InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
		//
		//		// 读取服务器的响应内容并显示
		//		int respInt = insr.read();
		//		while (respInt != -1)
		//		{
		//			System.out.print((char) respInt);
		//			respInt = insr.read();
		//		}
		//		System.out.println("done");
		//	}
		//
		//	@Test
		//	public void testHttps() throws Exception
		//	{
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		// 创建URL对象
		URL myURL = new URL("https://kyfw.12306.cn/otn/login/init");
		// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
		HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
		httpsConn.setSSLSocketFactory(ssf);
		// 取得该连接的输入流，以读取响应内容
		InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
		// 读取服务器的响应内容并显示
		int respInt = insr.read();
		while (respInt != -1)
		{
			//			System.out.print((char) respInt);
			respInt = insr.read();
		}
		//		System.out.println(httpsConn.getHeaderFields());
		myURL = new URL("https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&0.3739600564209904");
		httpsConn = (HttpsURLConnection) myURL.openConnection();
		httpsConn.setSSLSocketFactory(ssf);
		insr = new InputStreamReader(httpsConn.getInputStream());
		respInt = insr.read();
		FileOutputStream out = new FileOutputStream(new File("d:\\test.png"));
		while (respInt != -1)
		{
			out.write((char) respInt);
			//			System.out.print((char) respInt);
			respInt = insr.read();
		}
		out.close();
	}
}
