package com.rajesh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil
{

	private static Properties mainProperties = new Properties();

	/**
	 * This method is used to initialize the properties with the property value pair read from the file.
	 * 
	 * @param relativeFilepath
	 *            with reference to the jar
	 * @throws IOException
	 */
	public static void initializeProperties(String relativeFilepath)
	{

		FileInputStream file;
		try
		{
			file = new FileInputStream(relativeFilepath);
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException("Unable to read the file.\n Relative file path is " + relativeFilepath);
		}
		try
		{
			mainProperties.load(file);
			file.close();
		}
		catch (IOException e)
		{
			throw new RuntimeException("File not in proper format.\n Relative file path is " + relativeFilepath);
		}
		finally
		{
			try
			{
				file.close();
			}
			catch (IOException e)
			{
				throw new RuntimeException("Unable to close the file.\n Relative file path is " + relativeFilepath);
			}
		}

	}

	/**
	 * This method returns the corresponding value of the property. If property name not found, it return default value.
	 * 
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */

	public static String getProperty(String propertyName, String defaultValue)
	{

		return mainProperties.getProperty(propertyName, defaultValue);
	}

	/**
	 * This method returns the corresponding value of the property. If property name not found, it return null.
	 * 
	 * @param propertyName
	 * @return
	 */

	public static String getProperty(String propertyName)
	{

		return mainProperties.getProperty(propertyName);
	}

}
