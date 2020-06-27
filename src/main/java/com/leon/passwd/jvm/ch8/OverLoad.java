package com.leon.passwd.jvm.ch8;

import java.io.Serializable;

/**
 * @Package com.leon.passwd.jvm.ch8
 * @author zhangyunfei
 * @date 2020-06-21 11:46
 * @version V1.0
 */
public class OverLoad
{
	public static void sayHello(Object arg)

	{
		System.out.println("hello Object");
	}

	public static void sayHello(int arg)

	{
		System.out.println("hello int");
	}

	public static void sayHello(long arg)

	{
		System.out.println("hello long");
	}

	public static void sayHello(Character arg)

	{
		System.out.println("hello Character");
	}

	public static void sayHello(char arg)

	{
		System.out.println("hello char");
	}

	public static void sayHello(char... arg)

	{
		System.out.println("hello char……");
	}

	public static void sayHello(Serializable arg)

	{
		System.out.println("hello Serializable");
	}

	public static void main(String[] args)

	{
		sayHello('a');
	}
}
