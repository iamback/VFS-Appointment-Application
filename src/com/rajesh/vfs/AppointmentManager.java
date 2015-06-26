package com.rajesh.vfs;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.WebElement;

import com.rajesh.PropertiesUtil;
import com.rajesh.selenium.driver.SeleniumUtil;
import com.rajesh.vfs.util.VFSConstants;
import com.rajesh.vfs.util.VFSUtil;

public class AppointmentManager
{

	/**
	 * @param args
	 */

	public AppointmentManager()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		VFSUtil.initializeEnvironment();
		processRequest();
	}

	private static void processRequest()
	{
		String requestType = PropertiesUtil.getProperty(VFSConstants.REQUEST_TYPE_PROPERTY_NAME);
		String referenceNo = PropertiesUtil.getProperty(VFSConstants.VFS_REFERENCE_NO_PROPERTY_NAME);

		switch (requestType)
		{
			case "ScheduleAppointment":
				SeleniumUtil.clickElementByXpath("//td/a[text()='" + referenceNo + "']");
				SeleniumUtil.hangon(3000);
				SeleniumUtil
						.clickElementByXpath("//div[@id='dvPrint']/input[@name='ctl00$ContentPlaceHolder1$btnSchAppointment']");
				SeleniumUtil.clickElementByXpath("//div/input[@value='CONTINUE']");
				break;

			case "Reschedule":
				SeleniumUtil
						.clickElementByXpath("//td/a[text()='" + referenceNo + "']/../../td/a[text()='Reschedule']");
				SeleniumUtil.hangon(3000);
				SeleniumUtil.clickElementByXpath("//div/input[@value='RESCHEDULE']");
				break;
		}

		while (true)
		{
			if (bookAppointment("current"))
			{
				break;
			}
			else
			{
				SeleniumUtil
						.clickElementByXpath("//div[@id='calendar']/table/tbody/tr/td/span[@class='fc-button fc-button-next fc-state-default fc-corner-left fc-corner-right']");
				SeleniumUtil.hangon(5000);

				if (bookAppointment("next"))
				{
					break;
				}
				else
				{
					SeleniumUtil
							.clickElementByXpath("//div[@id='calendar']/table/tbody/tr/td/span[@class='fc-button fc-button-prev fc-state-default fc-corner-left fc-corner-right']");
					SeleniumUtil.hangon(5000);
				}
			}
		}

	}

	private static boolean bookAppointment(String month)
	{
		WebElement element = SeleniumUtil.getElementByXpath("//td[@style='background-color: rgb(188, 237, 145);']");
		if (element != null
				&& SeleniumUtil.getElementByXpath("//td[@style='background-color: rgb(188, 237, 145);']/div") != null)
		{
			String date = element.getText();
			element.click();

			SeleniumUtil.clickElementByXpath("//table[@id='timeBandListTable1']/tbody/tr[2]/td/input");
			SeleniumUtil.clickElementByXpath("//input[@type='submit']");
			sendMail(date, month);
			return true;
		}
		return false;
	}

	private static void sendMail(String day, String month)
	{

		final String username = PropertiesUtil.getProperty(VFSConstants.EMAIL_USERNAME_PROPERTY_NAME);
		final String password = PropertiesUtil.getProperty(VFSConstants.EMAIL_PASSWORD_PROPERTY_NAME);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(username, password);
				}
			});

		try
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
			message.setSubject("Appointment booked");
			message.setText("Dear User," + "\n\n Appointment booked on " + day.replace("\n", "") + " of " + month
					+ " month. \n Regards,\nRajesh");

			Transport.send(message);

		}
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}
}
