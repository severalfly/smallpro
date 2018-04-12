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
			// 当前失败次数，超过5次时，就可以退出了
			int errCnt = 0;
			int failCnt = 0;
			for (int i = 0; i < 100; i++) {

				JSONObject json = queryFrom12306();
				if (json != null) {
					if (json.getBooleanValue("status") && "1".equals(json.getJSONObject("data").getString("flag"))) {
						// 暂时认为的成功状态
						logger.info(i + "获取到正确结果一次： " + new Date().toString());
					} else {
						// 返回成功，但是结果失败
						failCnt++;
						logger.info("获取到失败结果一次： " + new Date().toString());
					}
					// System.out.println(json.toJSONString());
				} else {
					System.out.println("error");
					errCnt++;
				}
				if (errCnt > 4 || failCnt > 4) {
					logger.info(new Date() + " 当前已失败 errCnt: {}  failCnt: {} 退出抓取任务, 总执行: " + i, errCnt, failCnt);
					break;
				}
				Thread.sleep(1 * 100);
			}
			logger.info(new Date() + " 当前已完成 errCnt: {}  failCnt: {} 退出抓取任务", errCnt, failCnt);
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
			String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT";
			url = String.format(url, departDate, departCode, arriveCode);
			HttpGet get = new HttpGet(url);

			get.addHeader("Accept-Encoding", "gzip");
			get.addHeader("Connection", "keep-alive");
			get.addHeader("Host", "kyfw.12306.cn");
			get.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:41.0) Gecko/20100101 Firefox/51.0");
			get.addHeader("Cookie",
					"JSESSIONID=18B19BCA6F2988B5A5FF9E31317563CC; route=c5c62a339e7744272a54643b3be5bf64; BIGipServerotn=350487050.38945.0000; BIGipServerpool_passport=200081930.50215.0000; _jc_save_detail=true; current_captcha_type=Z; _jc_save_wfdc_flag=dc; BIGipServeropn=1592787466.24610.0000; _jc_save_fromStation=%u8302%u540D%2CMDQ; _jc_save_toStation=%u8087%u5E86%2CZVQ; RAIL_EXPIRATION=1523597209057; RAIL_DEVICEID=OPlWqemc1vvR9uPU0NpqhsNk4Bg8A64SCm3SXMg8jpWL79YiAiQ__KwxDlXMROPl3ybwPFan3GybXGk4eO19Bcj7dogBfLe5j3IfQInqiC6tDpZTFuEtUhOg0VjGqVjqggj14rKAEyhPAhvGwftUAj-tPr-4N649; _jc_save_fromDate=2018-04-12; _jc_save_toDate=2018-04-12");
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
