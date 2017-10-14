package com.leon.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	private static String directory = "d:/tmp";

	public static void main(String[] args)
	{
		// Create directory if it does not exist 
		//		try
		//		{
		//			Files.createDirectory(Paths.get(directory));
		//		}
		//		catch (IOException ioe)
		//		{
		//			ioe.printStackTrace();
		//		}

		System.out.println("File Monitor...");
		// Start to monitor file event per 500 milliseconds
		FileMonitor.getFileMonitor().monitor(directory, 500);

		//		System.out.println("File Generater...");
		//		// New runnable instance
		//		FileGenerator fileGeneratorRunnable = new FileGenerator(directory);
		//
		//		// Start to multi-thread for generating file
		//		for (int i = 0; i < 10; i++)
		//		{
		//			Thread fileGeneratorThread = new Thread(fileGeneratorRunnable);
		//			fileGeneratorThread.start();
		//		}
	}
}
