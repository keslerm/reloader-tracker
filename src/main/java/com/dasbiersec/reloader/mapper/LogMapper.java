package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.log.Chronograph;
import com.dasbiersec.reloader.domain.log.Log;
import com.dasbiersec.reloader.entity.LogEntity;

public class LogMapper
{
	public static Log entityToDomain(LogEntity entity)
	{
		Log log = new Log();
		log.setId(entity.getId());
		log.setFirearm(entity.getFirearm());
		log.setGroupSize(entity.getGroupSize());
		log.setNote(entity.getNote());
		log.setRange(entity.getRange());
		log.setShotsInGroup(entity.getShotsInGroup());
		log.setTargetDistance(entity.getTargetDistance());
		log.setChronograph(new Chronograph(entity.getFps()));
		return log;
	}

	public static LogEntity domainToEntity(Log log)
	{
		LogEntity entity = new LogEntity();
		entity.setId(log.getId());
		entity.setTargetDistance(log.getTargetDistance());
		entity.setShotsInGroup(log.getShotsInGroup());
		entity.setRange(log.getRange());
		entity.setNote(log.getNote());
		entity.setFirearm(log.getFirearm());
		entity.setGroupSize(log.getGroupSize());

		if (log.getChronograph() != null)
			entity.setFps(log.getChronograph().getFps());
		return entity;
	}
}
