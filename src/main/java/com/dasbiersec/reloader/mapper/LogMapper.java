package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.Chronograph;
import com.dasbiersec.reloader.domain.Log;
import com.dasbiersec.reloader.dto.log.ChronographDTO;
import com.dasbiersec.reloader.dto.log.LogDTO;

public class LogMapper
{
	public static LogDTO domainToDTO(Log entity)
	{
		LogDTO log = new LogDTO();
		log.id = entity.getId();
		log.firearm = entity.getFirearm();
		log.groupSize = entity.getGroupSize();
		log.note = entity.getNote();
		log.range = entity.getRange();
		log.shotsInGroup = entity.getShotsInGroup();
		log.targetDistance = entity.getTargetDistance();

		log.chronograph = domainToDTO(new Chronograph(entity.getFps()));
		log.chronograph.fps = entity.getFps();

		return log;
	}

	public static Log dtoToDomain(LogDTO log)
	{
		Log entity = new Log();
		entity.setId(log.id);
		entity.setTargetDistance(log.targetDistance);
		entity.setShotsInGroup(log.shotsInGroup);
		entity.setRange(log.range);
		entity.setNote(log.note);
		entity.setFirearm(log.firearm);
		entity.setGroupSize(log.groupSize);

		if (log.chronograph != null)
			entity.setFps(log.chronograph.fps);

		return entity;
	}

	public static ChronographDTO domainToDTO(Chronograph domain)
	{
		ChronographDTO dto = new ChronographDTO();
		dto.average = domain.getAverage();
		dto.high = domain.getHigh();
		dto.low = domain.getLow();
		dto.variance = domain.getVariance();
		dto.standardDeviation = domain.getStandardDeviation();
		return dto;
	}
}
