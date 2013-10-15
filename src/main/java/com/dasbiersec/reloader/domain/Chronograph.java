package com.dasbiersec.reloader.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class Chronograph
{
	private List<Integer> fps;

	public Chronograph(List<Integer> fps)
	{
		this.fps = fps;
		Collections.sort(this.fps);
	}

	public Integer getHigh()
	{
		if (fps == null || fps.size() == 0)
			return null;

		return fps.get(fps.size() - 1);
	}

	public Integer getLow()
	{
		if (fps == null || fps.size() == 0)
			return null;

		return fps.get(0);
	}

	public BigDecimal getAverage()
	{
		if (fps == null || fps.size() == 0)
			return null;

		BigDecimal count = new BigDecimal(fps.size());
		BigDecimal total = new BigDecimal(0);

		for (Integer row : fps)
		{
			total = total.add(new BigDecimal(row));
		}

		return total.divide(count);
	}

	public BigDecimal getStandardDeviation()
	{
		if (fps == null || fps.size() == 0)
			return null;

		BigDecimal total = getVariance();
		return new BigDecimal(Math.sqrt(total.doubleValue())).setScale(3, RoundingMode.HALF_UP);
	}

	public BigDecimal getVariance()
	{
		if (fps == null || fps.size() == 0)
			return null;

		BigDecimal mean = getAverage();

		BigDecimal total = new BigDecimal(0);

		for (Integer row : fps)
		{
			BigDecimal temp = new BigDecimal(row).subtract(mean);
			total = total.add(temp.multiply(temp));
		}

		total = total.divide(new BigDecimal(fps.size()), 3, RoundingMode.HALF_UP);

		return total;
	}
}
