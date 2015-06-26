package com.rajesh.selenium.driver;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.rajesh.PropertiesUtil;
import com.rajesh.vfs.util.VFSConstants;

public class DriverConfig
{

	private static DriverConfig DriverConfig;
	private static WebDriver driver;

	private DriverConfig()
	{
		setUp();
	}

	public static WebDriver getDriverInstance()
	{
		if (null == DriverConfig)
		{
			DriverConfig = new DriverConfig();
		}
		return DriverConfig.getDriver();
	}

	private WebDriver getDriver()
	{
		return driver;
	}

	protected void setUp()
	{
		File file = new File(PropertiesUtil.getProperty(VFSConstants.WEB_DRIVER_FILE_PATH_PROPERTY_NAME));
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();

		/*try
		{
			 File file = new File("C:/Cordys/defaultInst/cws/IEDriverServer.exe");
			 System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}
		catch (Exception e)
		{
			 File file = new File("C:/Cordys/defaultInst/cws/IEDriverServer.exe");
			 System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}*/

	}

	protected void finalize() throws Throwable
	{
		cleanUp();
		super.finalize();
	}

	public static void cleanUp()
	{
		driver.quit();
	}
}