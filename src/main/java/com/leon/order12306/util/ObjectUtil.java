package com.leon.order12306.util;

public class ObjectUtil
{
	public static String getString(Object obj)
	{
		return obj.toString();
	}

	public static long getLong(Object obj)
	{
		return Long.parseLong(obj.toString());
	}

	public static int getInt(Object obj)
	{
		return Integer.parseInt(obj.toString());
	}
}
