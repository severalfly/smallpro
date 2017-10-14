package com.leon.file;

import java.io.File;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

/**
 * @author shengshu
 * 
 */
public class FileMonitor extends FileAlterationListenerAdaptor
{
	private static final Logger LOG = Logger.getLogger(FileMonitor.class);

	private static FileMonitor fileMonitor;

	private static int pageNumber = 0;

	private FileMonitor()
	{

	}

	// Get singleton object instance
	public static FileMonitor getFileMonitor()
	{
		if (fileMonitor == null)
		{
			synchronized (FileMonitor.class)
			{
				if (fileMonitor == null)
				{
					fileMonitor = new FileMonitor();
				}
			}
		}

		return fileMonitor;
	}

	// Create file event
	@Override
	public void onFileCreate(File file)
	{
		System.out.println("[Create]: " + file.getAbsolutePath());

		//		String fileAbsolutePath = file.getAbsolutePath();
		//		String fileAbsoluteParentPath = file.getParent();
		//		String fileBaseName = FilenameUtils.getBaseName(fileAbsolutePath);

		//		File destFile = new File(fileAbsoluteParentPath + File.separator + fileBaseName + "-" + CommonTooler.pagingFormat(pageNumber) + ".xml");
		//
		//		try
		//		{
		//			FileUtils.moveFile(file, destFile);
		//		}
		//		catch (IOException ioe)
		//		{
		//			ioe.printStackTrace();
		//		}

		pageNumber++;
	}

	// Change file event
	@Override
	public void onFileChange(File file)
	{
		System.out.println("[Change]: " + file.getAbsolutePath());
	}

	// Delete file event
	@Override
	public void onFileDelete(File file)
	{
		System.out.println("[Delete]: " + file.getAbsolutePath());
	}

	public void monitor(String directory, int interval)
	{
		System.out.println('t');
		// Observer file whose suffix is pm 
		FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(directory, FileFilterUtils.and(FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter("")), null);

		// Add listener for event (file create & change & delete)
		fileAlterationObserver.addListener(this);

		// Monitor per interval
		FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(interval, fileAlterationObserver);

		try
		{
			// Start to monitor
			fileAlterationMonitor.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
