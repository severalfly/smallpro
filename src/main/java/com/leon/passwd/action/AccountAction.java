package com.leon.passwd.action;

import com.alibaba.fastjson.JSONObject;
import com.leon.passwd.util.ELKLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

@Slf4j
@RestController
public class AccountAction
{
	private static final long serialVersionUID = 5601982197489875967L;

	@RequestMapping(value = "testv2")
	public String testv2(int count, int sleep) throws InterruptedException
	{
		JSONObject jsonObject = new JSONObject();

		for (int i = 0; i < count; i++)
		{
			jsonObject.put("key", new Random().nextInt(100) % 2);
			Calendar calendar = Calendar.getInstance();
			jsonObject.put("time", new Timestamp(calendar.getTimeInMillis()).toString());
			log.info(jsonObject.toJSONString());
			ELKLog.log(jsonObject, "test-random", "", new Timestamp(System.currentTimeMillis()));
			//			log.info("testv2 " + new Timestamp(calendar.getTimeInMillis()).toString());
			//			System.out.println("testv2 " + new Timestamp(calendar.getTimeInMillis()).toString());
			if (sleep > 0)
			{
				Thread.sleep(sleep);
			}
		}
		return "done on " + count;
	}

}
