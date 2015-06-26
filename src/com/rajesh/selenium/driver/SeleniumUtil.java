package com.rajesh.selenium.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtil
{
	public static void loadURL(String url)
	{
		WebDriver driver = DriverConfig.getDriverInstance();
		driver.get(url);
		driver.manage().window().maximize();
		SeleniumUtil.hangon(3000);
	}

	public static void hangon(int delay)
	{
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException e)
		{
			// TODO
		}
	}

	private static WebElement getElementById(String buttonID)
	{
		WebDriver driver = DriverConfig.getDriverInstance();
		int count = 0;
		WebElement element = null;
		while (count < 2)
		{
			try
			{
				element = driver.findElement(By.id(buttonID));
				break;
			}
			catch (Exception e)
			{
				hangon(2000);
				count++;
			}
		}
		return element;
	}

	public static WebElement getElementByXpath(String xpath)
	{
		WebDriver driver = DriverConfig.getDriverInstance();
		int count = 0;
		WebElement element = null;
		while (count < 2)
		{

			try
			{
				element = driver.findElement(By.xpath(xpath));
				break;
			}
			catch (Exception e)
			{
				hangon(2000);
				count++;
			}
		}
		return element;
	}

	public static void setTextInputByButtonID(String buttonID, String textValue)
	{
		WebElement element = getElementById(buttonID);
		element.sendKeys(textValue);
	}

	public static void clickElementByXpath(String xpath)
	{
		WebElement element = getElementByXpath(xpath);
		element.click();
		hangon(2000);
	}
}
