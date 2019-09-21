package com.leon.passwd.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

@Slf4j
@RestController
public class AccountAction extends AbstractAction
{
	private static final long serialVersionUID = 5601982197489875967L;
	//	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);

	@RequestMapping(value = "testv2")
	public String testv2(int count) throws InterruptedException
	{
		JSONObject jsonObject = new JSONObject();

		for (int i = 0; i < count; i++)
		{
			jsonObject.put("res-key", new Random().nextInt(100) % 2);
			Calendar calendar = Calendar.getInstance();
			jsonObject.put("res-time", new Timestamp(calendar.getTimeInMillis()).toString());
			log.info(jsonObject.toJSONString());
			//			log.info("testv2 " + new Timestamp(calendar.getTimeInMillis()).toString());
			//			System.out.println("testv2 " + new Timestamp(calendar.getTimeInMillis()).toString());
			Thread.sleep(500);
		}
		return "done on " + count;
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
