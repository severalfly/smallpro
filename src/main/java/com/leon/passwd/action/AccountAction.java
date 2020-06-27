package com.leon.passwd.action;

import com.alibaba.fastjson.JSONObject;
import com.leon.passwd.util.ELKLog;
import com.leon.passwd.util.ThreadUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class AccountAction
{
	private static Logger log = LoggerFactory.getLogger(AccountAction.class);
	private ThreadPoolExecutor threadexecutor = new ThreadPoolExecutor(10, 300, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));

	private static final long serialVersionUID = 5601982197489875967L;

	@RequestMapping(value = "testv2")
	public String testv2(int count, int sleep) throws InterruptedException
	{

		for (int i = 0; i < count; i++)
		{

			ThreadUtil.submitTask(threadexecutor, new Handler());
			//			log.info("testv2 " + new Timestamp(calendar.getTimeInMillis()).toString());
			//			System.out.println("testv2 " + new Timestamp(calendar.getTimeInMillis()).toString());
			if (sleep > 0)
			{
				Thread.sleep(sleep);
			}
		}
		return "done on " + count;
	}

	class Handler extends Thread
	{
		@Override
		public void run()
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("key", new Random().nextInt(100) % 2);
			Calendar calendar = Calendar.getInstance();
			jsonObject.put("time", new Timestamp(calendar.getTimeInMillis()).toString());
			log.info(jsonObject.toJSONString());
			ELKLog.log(jsonObject, "test-random", "", new Timestamp(System.currentTimeMillis()));
		}
	}

	public static void main(String[] args) throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			log.info("Info log [" + i + "]." + new Timestamp(System.currentTimeMillis()).toString());
			Thread.sleep(500);
		}
	}
}
