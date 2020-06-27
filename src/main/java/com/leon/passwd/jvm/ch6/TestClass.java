package com.leon.passwd.jvm.ch6;

/**
 * @Package com.leon.passwd.jvm.ch2.ch6
 * @author zhangyunfei
 * @date 2020-06-14 10:34
 * @version V1.0
 */
public class TestClass
{
	private int m;

	public int inc()
	{
		return m + 1;
	}

	public void onlyMe(Object f)
	{
		synchronized (f)
		{

		}
	}
}
