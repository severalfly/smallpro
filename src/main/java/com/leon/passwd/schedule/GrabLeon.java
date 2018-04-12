package com.leon.passwd.schedule;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class GrabLeon extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(GrabLeon.class);

	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {

				JSONObject json = queryFrom12306();
				if (json != null) {
					if (json.getBooleanValue("status") && "1".equals(json.getJSONObject("data").getString("flag"))) {
						// 暂时认为的成功状态
						logger.info("获取到正确结果一次： " + new Date().toString());
					} else {
						// 返回成功，但是结果失败
						logger.info("获取到失败结果一次： " + new Date().toString());
					}
					// System.out.println(json.toJSONString());
				} else {
					System.out.println("error");
				}
				Thread.sleep(5 * 1000);
			}
		} catch (Exception e) {
			logger.error("哇，有异常", e);

		}
	}

	private JSONObject queryFrom12306() {

		try {
			logger.info("grab leon start...");
			String departCode = "WHN";
			String arriveCode = "BJP";
			String departDate = "2018-04-30";
			// String url =
			// "https://kyfw.12306.cn/otn/leftTicket/queryA?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT";
			String url = "https://kyfw.12306.cn/otn/leftTicket/queryO?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT";
			url = String.format(url, departDate, departCode, arriveCode);
			HttpGet get = new HttpGet(url);

			get.addHeader("Accept-Encoding", "gzip");
			get.addHeader("Connection", "keep-alive");
			get.addHeader("Host", "kyfw.12306.cn");
			get.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:41.0) Gecko/20100101 Firefox/51.0");
			get.addHeader("Cookie",
					"JSESSIONID=A5B5F4A74250FF94E6C60F77713B15E8; route=9036359bb8a8a461c164a04f8f50b252; BIGipServerotn=132383242.64545.0000; RAIL_EXPIRATION=1523841767777; RAIL_DEVICEID=csxLbdO1UzYEXGNRwWpoIN3pb6LjGLpo7PlJtLzEW_ef94Mz3LqKg-rdNG4oG1NKww2nJ7yEyInR5C0VR9n0MyTDBjEiaT7LjikhKhE3KFF5zRNP4eUd7722EK0eZGeaH1rPKu_Yr71lPoGqRsPyRPHFoWhJkStx; _jc_save_fromStation=%u6B66%u6C49%2CWHN; _jc_save_toStation=%u5317%u4EAC%2CBJP; _jc_save_fromDate=2018-04-20; _jc_save_toDate=2018-04-12; _jc_save_wfdc_flag=dc");
			// get.setConfig(config);

			// set12306Cookie(server.getRight(), context);
			HttpClient httpClient = HttpClients.createDefault();
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			// System.out.println(EntityUtils.toString(entity));
			logger.info("获取到结果全部结果 {}", JSONObject.toJSONString(response));
			String sEntity = EntityUtils.toString(entity);
			logger.info("获取到结果entity结果 {}", sEntity);
			// System.out.println(JSONObject.toJSONString(response));
			JSONObject json = JSONObject.parseObject(sEntity);
			return json;

		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
}
