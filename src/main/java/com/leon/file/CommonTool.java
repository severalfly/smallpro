package com.leon.file;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shengshu
 * 
 */
public class CommonTool
{

	// Date time format
	public static String getDateTimeFormat(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
		String dateTime = simpleDateFormat.format(date);

		return dateTime;
	}

	// Page format
	public static String pagingFormat(int pageNumber)
	{
		String pageNumberStr = "P00";

		if (pageNumber < 0)
		{
			throw new NumberFormatException("The page number should not be negative");
		}

		if (pageNumber < 10)
		{
			pageNumberStr = String.format("P0%d", pageNumber);
		}
		else
		{
			pageNumberStr = String.format("P%d", pageNumber);
		}

		return pageNumberStr;
	}

}
