package com.rajesh.vfs.util;

import com.rajesh.PropertiesUtil;
import com.rajesh.selenium.driver.SeleniumUtil;

public class VFSUtil
{

	public static void initializeEnvironment()
	{
		// TODO
		PropertiesUtil.initializeProperties("Properties.properties");
		openVFSApplication();
		login();
	}

	private static void login()
	{
		SeleniumUtil.setTextInputByButtonID("txtEmail", PropertiesUtil
				.getProperty(VFSConstants.VFS_USERNAME_PROPERTY_NAME));
		SeleniumUtil.setTextInputByButtonID("txtPassword1", PropertiesUtil
				.getProperty(VFSConstants.VFS_PASSWORD_PROPERTY_NAME));
		SeleniumUtil.clickElementByXpath("//input[@type='submit']");
	}

	private static void openVFSApplication()
	{
		SeleniumUtil.loadURL("https://www.vfsvisaonline.com/DHAFosOnlineVAF");
	}

}
