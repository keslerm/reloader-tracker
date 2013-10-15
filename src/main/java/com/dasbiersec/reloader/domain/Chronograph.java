package com.dasbiersec.reloader.domain;

import java.math.BigDecimal;
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
		return fps.get(fps.size() - 1);
	}

	public Integer getLow()
	{
		return fps.get(0);
	}

	public BigDecimal getAverage()
	{
		BigDecimal count = new BigDecimal(fps.size());
		BigDecimal total = new BigDecimal(0);

		for (Integer row : fps)
		{
			total = total.add(new BigDecimal(row));
		}

		return total.divide(count);
	}
}
