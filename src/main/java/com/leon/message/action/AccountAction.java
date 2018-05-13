package com.leon.message.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountAction extends AbstractAction
{
	private static final long serialVersionUID = 5601982197489875967L;
	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);

	@Action(value = "/testv2")
	public void testv2()
	{
		logger.info("testv2 " + new Date());
		System.out.println("testv2 " + new Date());
		outputString("out " + new Date());
	}
}
