package com.leon.passwd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.leon.passwd.schedule.GrabLeon;

public class Initializer implements ApplicationListener {
	private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		logger.info("tomcat start...");
		setupStartup();
	}

	private void setupStartup() {
		try {
			// 抓取测试
			new GrabLeon().start();
		} catch (Exception e) {
			logger.error("", e);
		}

	}

}
