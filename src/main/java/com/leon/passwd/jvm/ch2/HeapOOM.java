package com.leon.passwd.jvm.ch2;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package com.leon.passwd.jvm.ch2
 * @author zhangyunfei
 * @date 2020-06-13 16:23
 * @version V1.0
 */
public class HeapOOM
{
	private static Logger log = LoggerFactory.getLogger(HeapOOM.class);

	static class OOMObject
	{
	}

	/**
	 * 此代码仅作为示例使用，实际运行会报 OOM 异常
	 * java.lang.OutOfMemoryError: Java heap space
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<OOMObject> list = new ArrayList<>();
		while (true)
		{
			log.info("aaaa");
			list.add(new OOMObject());
		}
	}

}
