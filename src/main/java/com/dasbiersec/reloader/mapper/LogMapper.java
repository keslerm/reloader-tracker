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

		log.chronograph = domainToDTO(entity.getChronograph());
		log.chronograph.fps = entity.getFps();

		return log;
	}

	public static Log dtoToDomain(LogDTO log)
	{
		Log entity = new Log();
		copyDTOToDomain(log, entity);
		return entity;
	}

	public static void copyDTOToDomain(LogDTO dto, Log log)
	{
		log.setTargetDistance(dto.targetDistance);
		log.setShotsInGroup(dto.shotsInGroup);
		log.setRange(dto.range);
		log.setNote(dto.note);
		log.setFirearm(dto.firearm);
		log.setGroupSize(dto.groupSize);

		if (dto.chronograph != null)
			log.setFps(dto.chronograph.fps);
		else
			log.setFps(null);
	}

	public static ChronographDTO domainToDTO(Chronograph domain)
	{
		ChronographDTO dto = new ChronographDTO();
		dto.average = domain.getAverage();
		dto.high = domain.getHigh();
		dto.low = domain.getLow();
		dto.variance = domain.getVariance();
		dto.standardDeviation = domain.getStandardDeviation();
		dto.muzzleEnergy = domain.getMuzzleEnergy();
		return dto;
	}
}
