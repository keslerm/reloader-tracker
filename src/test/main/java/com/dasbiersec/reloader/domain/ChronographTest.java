package com.dasbiersec.reloader.domain;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ChronographTest
{
	private Chronograph chronograph;

	@Before
	public void setUp()
	{
		List<Integer> fps = new ArrayList<Integer>();
		fps.add(53);
		fps.add(61);
		fps.add(49);
		fps.add(67);
		fps.add(55);
		fps.add(63);

		chronograph = new Chronograph(fps);
	}

	@Test
	public void testLow()
	{
		Assert.assertEquals((Integer) 49, chronograph.getLow());
	}

	@Test
	public void testHigh()
	{
		Assert.assertEquals((Integer) 67, chronograph.getHigh());
	}

	@Test
	public void testStandardDeviation()
	{
		Assert.assertEquals(new BigDecimal(6.191).setScale(3, RoundingMode.HALF_UP), chronograph.getStandardDeviation());
	}

	@Test
	public void testDeviance()
	{
		Assert.assertEquals(new BigDecimal(38.333).setScale(3, RoundingMode.HALF_UP), chronograph.getVariance());
	}
}
