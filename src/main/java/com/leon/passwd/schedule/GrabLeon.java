package com.leon.passwd.schedule;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrabLeon extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(GrabLeon.class);

	@Override
	public void run() {
		try {
			logger.info("grab leon start...");
			String url = "https://kyfw.12306.cn/otn/leftTicket/queryA?leftTicketDTO.train_date=2018-04-30&leftTicketDTO.from_station=WHN&leftTicketDTO.to_station=BJP&purpose_codes=ADULT";
			HttpGet get = new HttpGet(url);

			get.addHeader("Accept-Encoding", "gzip");
			get.addHeader("Connection", "keep-alive");
			get.addHeader("Host", "kyfw.12306.cn");
			// get.setConfig(config);

			// set12306Cookie(server.getRight(), context);
			// HttpClient httpClient = GtAsyHttpClient
			// httpClient.executeGet(get, context,
			// new FutureCallback<HttpResponse>() {
			// @Override
			// public void failed(Exception e) {
			// String error = "UnknowFailed";
			// if (e != null) {
			// error = e.getMessage();
			// if (ObjectUtil.isNull(error)) {
			// error = "UnknowFailedException";
			// }
			// }
			// logError(error);
			// semaphore.release();
			// closeInvalidConnection(context);
			// errorCount.addAndGet(1);
			// failCount.addAndGet(1);
			// }
			//
			// @Override
			// public void completed(HttpResponse httpResponse) {
			// semaphore.release();
			// try {
			// validProxyList.add(p);
			// save(t, departCode, arriveCode, departDate,
			// httpResponse);
			// } catch (Exception e) {
			// logError("ErrorCompleted");
			// }
			// }
			//
			// @Override
			// public void cancelled() {
			// logError("cancel");
			// semaphore.release();
			// closeInvalidConnection(context);
			// errorCount.addAndGet(1);
			// cancleCount.addAndGet(1);
			// }
			// });
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
