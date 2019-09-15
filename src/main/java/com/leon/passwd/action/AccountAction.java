package com.leon.passwd.action;

import lombok.extern.slf4j.Slf4j;
import org.apache.struts2.convention.annotation.Action;

import java.util.Date;

@Slf4j
public class AccountAction extends AbstractAction
{
	private static final long serialVersionUID = 5601982197489875967L;
	//	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);

	@Action(value = "testv2")
	public void testv2()
	{
		log.info("testv2 " + new Date());
		System.out.println("testv2 " + new Date());
	}

	public static void main(String[] args) throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			log.info("Info log [" + i + "].");
			Thread.sleep(500);
		}
	}
}
