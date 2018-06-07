package com.leon.passwd;

import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TT
{
	private String id;

	//	public static void main(String[] args)
	//	{
	//		System.out.println(new T().getId());
	//	}

	@Test
	public void testRandom() throws Exception
	{
		int cnt = 12;
		BigDecimal all = new BigDecimal("30");
		for (int i = 0; i < 100; i++)
		{
			getRandom(cnt, all, false);
		}

	}

	private void getRandom(int cnt, BigDecimal all, boolean pflag) throws Exception
	{
		all = all.multiply(new BigDecimal(100));
		if (all.intValue() < cnt)
		{
			throw new Exception("金额错误啦");
		}
		List<BigDecimal> list = new ArrayList<>();
		int res = 0;
		for (int i = 0; i < cnt; i++)
		{
			int max = (all.intValue() - res) / (cnt - i) * 2 - 1;
			int rand = 0;
			if (cnt - i == 1)
			{
				rand = all.intValue() - res;
			}
			else
			{

				rand = new Random().nextInt(max) + 1;
			}
			if (rand <= 0)
			{
				throw new Exception("小于0啦");
			}
			if (pflag)
			{
				System.out.println(rand);
			}
			list.add(new BigDecimal(rand).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN));
			res += rand;
		}
		System.out.println(list);
		//		System.out.println(cnt + "  " + all + " " + res);
	}
}
