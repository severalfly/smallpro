package com.leon.passwd.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@RestController
public class AccountAction extends AbstractAction
{
	private static final long serialVersionUID = 5601982197489875967L;
	//	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);

	@RequestMapping(value = "testv2")
	public String testv2()
	{
		log.info("testv2 " + new Date());
		System.out.println("testv2 " + new Date());
		return "done";
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
