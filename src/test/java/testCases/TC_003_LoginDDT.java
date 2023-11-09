package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Home_Page_Object;
import pageObjects.Login_Page_Object;
import pageObjects.MyAccount_page_object;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass
{
	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void TestLoginDDT(String email, String password,String exp)//This method should take the data from dataProvider, for this at annotation level we need to provide class name as parameter like @Test(dataProvider="LoginData"), whwere "LoginData" is a dataProvider name as mentioned in DataProvider class.
	                                                       //This method should take three parameter from dataprovider (String email, String password,String exp) because excel having three column , therefore we have pass three parameter in this method.
	                      //If dataProvider is present in same class then this is enough like @Test(dataProvider="LoginData").
	                     //But now dataProvider is present in some other class of some other package, therefor we need to add one more parameter along with dataProvider name like @Test(dataProvider="LoginData",dataProviderClass=DataProviders.class).
	                     //So now this login method will know exactly from where the data coming from which dataprovider data is coming from.
	{
		
		logger.info("*****Starting TC_003_LoginDDT*****");
		try {
		Home_Page_Object hp=new Home_Page_Object(driver);
		hp.clickMyAccount();
		logger.info("-clicked On MyAccountLink-");
		hp.clickLogin();
		logger.info("-clicked On LoginLink-");
		
		Login_Page_Object lp=new Login_Page_Object(driver);
		logger.info("-Providing Login Details-");
		lp.setEmailAddress(rb.getString("email"));//Previously we sent the email and password from properties file. But now we have to send different set of data from excel file by using dataprovider.
		lp.setPassword(rb.getString("password"));
		lp.clickLoginBtn();
		logger.info("-Clicked on Login Btn-");
		
		//And once we click on login btn we can do validation as below.
		//Here validation logic is some different.
		//Because even if login details are valid, login may failed or may pass & even if login details are Invalid, login may failed or pass. this type of combination we need to create by using if condition as below.
		//Because we can not depends on target page validation only. in this case we need help of excel sheet where we mentioned valid & invalida login details.
		//So after click on login btn we get target page as below
		MyAccount_page_object  accountPage=new MyAccount_page_object(driver);
		boolean trgtPage=accountPage.isMyAccountPageExist();
		
		if(exp.equals("Valid"))//condition1: where data is valid
		{
			if(trgtPage==true)//combination1: data valid and and target page got as expected, Test is passed
			{
			accountPage.clickLogOutBtn();
			Assert.assertTrue(true);
			}
			else//combination2:data valid and and target page not getting, Test is Failed
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equals("Invalid"))//condition2: where data is Invalid
		{
			if(trgtPage==true)//combination1: data Invalid and and target page got as expected, Test Failed
			{
				accountPage.clickLogOutBtn();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);//combination2:data Invalid and and target page false, Test is passed because this is negative test cases
			}
			
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******Finished TC_003_LoginDDT******");
	
	}

}
