package com.leon.passwd.jvm.ch8;

/**
 * @Package com.leon.passwd.jvm.ch8
 * @author zhangyunfei
 * @date 2020-06-21 22:04
 * @version V1.0
 */
public class GrandFather
{
	void thinking()
	{
		System.out.println("i am grandfather");
	}
}

class Father extends GrandFather
{
	void thinking()
	{
		System.out.println("i am father");
	}
}

class Son extends Father
{
	void thinking()
	{ //请读者在这里填入适当的代码(不能修改其他地方的代码) //实现调用祖父类的thinking()方法，打印"i am grandfather" }
		super.thinking();
	}
}

class Main
{
	public static void main(String[] args)
	{
		Son son = new Son();
		son.thinking();
	}
}