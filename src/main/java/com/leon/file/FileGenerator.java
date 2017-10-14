package com.leon.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author shengshu
 * 
 */
public class FileGenerator implements Runnable
{
	private static final Logger LOG = Logger.getLogger(FileGenerator.class);

	private String directory = null;

	private static Date date = new Date();

	public FileGenerator(String directory)
	{
		this.directory = directory;
	}

	private void generator(String directory)
	{
		for (int i = 0; i < 10; i++)
		{
			String fileName = CommonTool.getDateTimeFormat(date) + "-" + Thread.currentThread().getId() + i + ".pm";

			LOG.info("[Path]: " + directory + "/" + fileName);

			try
			{
				// Create file (path: directory/fileName)
				Files.createFile(Paths.get(directory, fileName));
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	@Override
	public void run()
	{
		this.generator(directory);
	}
}
