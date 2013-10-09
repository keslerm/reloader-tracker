package com.dasbiersec.reloader.domain.log;

public class Log
{
	private Integer id;
	private String note;
	private String range;
	private String firearm;
	private String groupSize;
	private String shotsInGroup;
	private String targetDistance;

	private Chronograph chronograph;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getRange()
	{
		return range;
	}

	public void setRange(String range)
	{
		this.range = range;
	}

	public String getFirearm()
	{
		return firearm;
	}

	public void setFirearm(String firearm)
	{
		this.firearm = firearm;
	}

	public String getGroupSize()
	{
		return groupSize;
	}

	public void setGroupSize(String groupSize)
	{
		this.groupSize = groupSize;
	}

	public String getShotsInGroup()
	{
		return shotsInGroup;
	}

	public void setShotsInGroup(String shotsInGroup)
	{
		this.shotsInGroup = shotsInGroup;
	}

	public String getTargetDistance()
	{
		return targetDistance;
	}

	public void setTargetDistance(String targetDistance)
	{
		this.targetDistance = targetDistance;
	}

	public Chronograph getChronograph()
	{
		return chronograph;
	}

	public void setChronograph(Chronograph chronograph)
	{
		this.chronograph = chronograph;
	}
}
