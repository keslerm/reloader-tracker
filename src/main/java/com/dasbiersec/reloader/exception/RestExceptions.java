package com.dasbiersec.reloader.exception;

public class RestExceptions
{
	public static class ItemNotFoundException extends RuntimeException
	{
		public ItemNotFoundException(String s)
		{
			super(s);
		}
	}
}
