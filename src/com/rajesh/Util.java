package com.rajesh;


public class Util
{

	public static boolean isEmpty(String value)
	{
		if (value == null || "".equals(value))
		{
			return true;
		}
		return false;
	}
}
