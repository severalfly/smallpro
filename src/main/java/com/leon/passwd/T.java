package com.leon.passwd;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class T {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "%u6B66%u6C49%2C";
		System.out.println(URLDecoder.decode(str, "utf-8"));
	}

}
