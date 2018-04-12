package com.leon.passwd.action;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leon.passwd.schedule.GrabLeon;

public class AccountAction extends AbstractAction
{
	private static final long serialVersionUID = 5601982197489875967L;
	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);

	@Action(value = "testv2")
	public void testv2()
	{
		logger.info("testV2 start");
		new GrabLeon().start();
	}
}
