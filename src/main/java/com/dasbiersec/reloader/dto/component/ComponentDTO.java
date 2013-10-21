package com.dasbiersec.reloader.dto.component;

import com.dasbiersec.reloader.enums.ComponentType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = BrassDTO.class, name = "Brass"),
		@JsonSubTypes.Type(value = PowderDTO.class, name = "Powder"),
		@JsonSubTypes.Type(value = PrimerDTO.class, name = "Primer"),
		@JsonSubTypes.Type(value = BulletDTO.class, name = "Bullet"),
})
public class ComponentDTO
{
	public Integer id;
	public String manufacturer;
	public String name;
	public ComponentType type;
	public BigDecimal amount;
	public BigDecimal cost;
}
