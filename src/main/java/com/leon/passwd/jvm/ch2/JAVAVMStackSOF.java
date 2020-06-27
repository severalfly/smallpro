package com.leon.passwd.jvm.ch2;

/**
 * @Package com.leon.passwd.jvm.ch2
 * @author zhangyunfei
 * @date 2020-06-13 16:41
 * @version V1.0
 */
public class JAVAVMStackSOF
{

	public int len = 1;

	public void stackleak()
	{
		len++;
		stackleak();
	}

	/**
	 * 会报栈溢出
	 * 28077
	 * java.lang.StackOverflowError
	 * 在单个线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无 法分配的时候，虚拟机抛出的都是StackOverflowError异常。
	 * 如果测试时不限于单线程，通过不断地建立线程的方式倒是可以产生内存溢出异常，如 代码清单2-5所示。但是这样产生的内存溢出异常与栈空间是否足够大并不存在任何联系，
	 * 或者准确地说，在这种情况下，为每个线程的栈分配的内存越大，反而越容易产生内存溢出 异常。
	 * @param args
	 */
	public static void main(String[] args)
	{
		JAVAVMStackSOF s = new JAVAVMStackSOF();
		try
		{
			s.stackleak();
		}
		catch (Throwable e)
		{
			System.out.println(s.len);
			e.printStackTrace();
		}
	}
}
