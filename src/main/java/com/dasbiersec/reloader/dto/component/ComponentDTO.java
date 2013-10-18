package com.dasbiersec.reloader.dto.component;

import com.dasbiersec.reloader.enums.ComponentType;

import java.math.BigDecimal;

public class ComponentDTO
{
	public Integer id;
	public String manufacturer;
	public String name;
	public ComponentType type;
	public BigDecimal amount;
	public BigDecimal cost;
}
