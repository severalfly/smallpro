package com.leon.passwd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class T {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// String str = "%u6B66%u6C49%2C";
		// System.out.println(URLDecoder.decode(str, "utf-8"));
		// }
		// @Test
		// public void testPush() {
		// System.out.println("tttttt");
		pushToWechart("o4RQlNsJlD6V_CRRdv6U6vMnXexc", 1, "", "U_NTeesHuk7tBI8g0zSbemzgW_cEcAS0th8HMxQLmL0", "first_data ffff", null, "remark", null);
	}

	/**
	 * 推送到航班管家微信公众号
	 * 
	 * @param openid
	 *            用户openid
	 * @param msgType
	 * @param jumpUrl
	 * @param templateid
	 * @param first
	 * @param keywords
	 * @param remark
	 * @param otherData
	 *            {} 为跳转到小程序
	 */
	public static void pushToWechart(String openid, int msgType, String jumpUrl, String templateid, String first, List<String> keywords, String remark, JSONObject otherData) {
		// WechartTemplatePush wtp = new WechartTemplatePush();
		JSONObject wtp = new JSONObject();
		// wtp.setId(DBIdMaker.instance.getStringId());
		wtp.put("touser", openid);
		wtp.put("template_id", templateid);
		wtp.put("url", jumpUrl);
		JSONObject data = new JSONObject();
		wtp.put("data", data);
		JSONObject jsonFirst = new JSONObject();
		jsonFirst.put("value", first);
		jsonFirst.put("color", "#173177");
		data.put("first", jsonFirst);
		if (keywords == null) {
			keywords = new ArrayList<String>(5);
		}

		if (keywords.size() <= 5) {
			int max = 5 - keywords.size();
			for (int i = 0; i < max; i++) {
				keywords.add("");
			}
		}

		if (StringUtils.isNotBlank(keywords.get(0))) {
			JSONObject keyword1 = new JSONObject();
			keyword1.put("value", keywords.get(0));
			keyword1.put("color", "");
			data.put("keyword1", keyword1); // 需要与模版的keyword 匹配
		}
		if (StringUtils.isNotBlank(keywords.get(1))) {
			JSONObject keyword2 = new JSONObject();
			keyword2.put("value", keywords.get(1));
			keyword2.put("color", "");
			data.put("keyword2", keyword2);
		}
		if (StringUtils.isNotBlank(keywords.get(2))) {
			JSONObject keyword3 = new JSONObject();
			keyword3.put("value", keywords.get(2));
			keyword3.put("color", "");
			data.put("keyword3", keyword3);
		}
		if (StringUtils.isNotBlank(keywords.get(3))) {
			JSONObject keyword4 = new JSONObject();
			keyword4.put("value", keywords);
			keyword4.put("color", "");
			data.put("keyword4", keyword4);
		}
		if (StringUtils.isNotBlank(keywords.get(4))) {
			JSONObject keyword5 = new JSONObject();
			keyword5.put("value", keywords.get(4));
			keyword5.put("color", "");
			data.put("keyword5", keyword5);
		}
		JSONObject jsonRemark = new JSONObject();
		jsonRemark.put("value", remark);
		jsonRemark.put("color", "");
		data.put("remark", jsonRemark);

		// wtp.setOtherData(otherData == null ? "" : otherData.toJSONString());

		String res = sendPost("http://wx.133.cn/hbrobot/pushalerm", wtp.toJSONString());
		System.out.println(res);
	}



	public static String sendPost(String uri, String param) {
		return sendPost(uri, param, "utf-8");
	}

	public static String sendPost(String uri, String param, String charset) {
		String result = null;
		PrintWriter out = null;
		InputStream in = null;
		try {
			URL url = new URL(uri);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setDoInput(true);
			urlcon.setDoOutput(true);
			urlcon.setUseCaches(false);
			urlcon.setRequestMethod("POST");
			urlcon.connect();// 获取连接
			out = new PrintWriter(urlcon.getOutputStream());
			out.print(param);
			out.flush();
			in = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(in, charset));
			StringBuffer bs = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				bs.append(line);
			}
			result = bs.toString();
		} catch (Exception e) {
			System.out.println("[请求异常][地址：" + uri + "][参数：" + param + "][错误信息：" + e.getMessage() + "]");
		} finally {
			try {
				if (null != in)
					in.close();
				if (null != out)
					out.close();
			} catch (Exception e2) {
				System.out.println("[关闭流异常][错误信息：" + e2.getMessage() + "]");
			}
		}
		return result;
	}
}
