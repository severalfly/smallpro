package com.leon.passwd.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ELKLog
{
	private static Logger log = LoggerFactory.getLogger(ELKLog.class);

	/**
	 *
	 * @param map 业务需要记的数据
	 * @param businessId 业务唯一id，不可为空
	 * @param uuid 本次记录的唯一id
	 * @param creatTime 创建时间，不可为空
	 */
	public static void log(Map<String, Object> map, String businessId, String uuid, Timestamp creatTime)
	{
		JSONObject json = new JSONObject();
		for (Map.Entry<String, Object> me : map.entrySet())
		{
			json.put("res-" + me.getKey(), me.getValue());
		}
		json.put("uuid", uuid);
		json.put("business_id", businessId);
		json.put("create_time", cTime(creatTime));
		json.put("host_ip", getHost());
		log.info(json.toJSONString());
	}

	private static String cTime(Timestamp createTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSSZ");
		return sdf.format(createTime);
	}

	public static String getHost()
	{
		try
		{
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			return hostAddress;
		}
		catch (UnknownHostException e)
		{
			return "";
		}

	}
}
