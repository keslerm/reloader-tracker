package com.dasbiersec.reloader.dto.log;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ChronographDTO
{
	public Integer low;
	public Integer high;
	public BigDecimal average;
	public BigDecimal variance;
	public BigDecimal standardDeviation;
	public BigDecimal muzzleEnergy;

	public List<Integer> fps;
}
