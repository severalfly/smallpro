package com.leon.passwd.util;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadUtil
{
	private static Logger log = LoggerFactory.getLogger(ThreadUtil.class);

	/**
	 * 将任务放入线程池，阻塞型
	 * @param executor 线程池
	 * @param run 任务
	 */
	public static void submitTask(ThreadPoolExecutor executor, Runnable run)
	{
		//防止任务被丢弃
		while (true)
		{
			try
			{
				executor.submit(run);
				// 任务添加成功则退出，否则一起等待直到有空闲
				break;
			}
			catch (RejectedExecutionException e)
			{
				try
				{
					// 队列已满，需要重试
					Thread.sleep(100L);
				}
				catch (InterruptedException e1)
				{
					log.error("", e);
				}
			}
		}

	}
}
