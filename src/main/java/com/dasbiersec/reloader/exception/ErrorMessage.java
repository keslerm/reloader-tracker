package com.dasbiersec.reloader.exception;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

public class ErrorMessage
{
	private List<String> errors;

	public ErrorMessage()
	{

	}

	public ErrorMessage(List<String> errors)
	{
		this.errors = errors;
	}

	public ErrorMessage(String error)
	{
		this(Collections.singletonList(error));
	}

	public List<String> getErrors()
	{
		return errors;
	}

	public void setErrors(List<String> errors)
	{
		this.errors = errors;
	}
}
