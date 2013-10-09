package com.dasbiersec.reloader.domain.log;

import java.util.Collections;
import java.util.List;

public class Chronograph
{
	private Integer low;
	private Integer high;

	private List<Integer> fps;

	public Chronograph(List<Integer> data)
	{
		this.fps = data;

		if (data != null && data.size() > 0)
		{
			Collections.sort(data);

			low = data.get(0);
			high = data.get(data.size() - 1);
		}
	}

	public void setLow(Integer low)
	{
		this.low = low;
	}

	public void setHigh(Integer high)
	{
		this.high = high;
	}

	public List<Integer> getFps()
	{
		return fps;
	}

	public void setFps(List<Integer> fps)
	{
		this.fps = fps;
	}

	public Integer getHigh()
	{
		return high;
	}

	public Integer getLow()
	{
		return low;
	}
}
