package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Home_Page_Object;
import pageObjects.Login_Page_Object;
import pageObjects.MyAccount_page_object;
import testBase.BaseClass;

//Here we have to reffere three object pages which are Home_page, Login_page, MyAccount_page and will create there object variable to reffer here.
public class TC_002_LogInTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void test_Login()
	{
		try {
		logger.info("*******Starting TC_002_LogInTest********");
		
		//Object of all three pages class
		Home_Page_Object hp=new Home_Page_Object(driver);
		hp.clickMyAccount();
		logger.info("-clicked On MyAccountLink-");
		hp.clickLogin();
		logger.info("-clicked On LoginLink-");
		
		Login_Page_Object lp=new Login_Page_Object(driver);
		logger.info("-Providing Login Details-");
		lp.setEmailAddress(rb.getString("email"));//rb is a variable which we have created in BaseClass to access the key and value from properties file.
		lp.setPassword(rb.getString("password"));
		lp.clickLoginBtn();
		logger.info("-Clicked on Login Btn-");
	
		
		
		MyAccount_page_object  accountPage=new MyAccount_page_object(driver);
		boolean trgtPage=accountPage.isMyAccountPageExist();//isMyAccountPageExist() this method returning boolean value
		Assert.assertEquals(trgtPage, true,"Login Failed");//here we are validating MyAcount is displayed or not.
		                                                   //"Login Failed" this msg will display when it failed.
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		
		
		logger.info("*******Finished TC_002_LogInTest********");
	}
		
}
