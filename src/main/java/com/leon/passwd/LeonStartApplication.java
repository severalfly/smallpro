package com.leon.passwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableCaching
@ComponentScan(basePackages = { "com.leon" })
//@PropertySource(name = "EncryptedProperties", value = { "classpath:application-test.yml", "classpath:application-release.yml" })
@EnableScheduling
public class LeonStartApplication
{
	public static void main(String[] args)
	{
		try
		{
			SpringApplication.run(LeonStartApplication.class, args);
		}
		catch (Throwable e)
		{
			System.out.println(e);
			System.exit(1);
		}
	}
}
