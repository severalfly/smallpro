package com.leon.stock;

import com.leon.utils.HttpUtils;

public class StockPrice
{
	private static final String URL = "http://hq.sinajs.cn/list=sh601006,sh601001";

	public static void main(String[] args)
	{
		System.out.println(HttpUtils.sendGet(URL));

	}
}
