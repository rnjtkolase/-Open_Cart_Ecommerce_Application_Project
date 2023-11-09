package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page_Object extends BasePage 
{
	public Login_Page_Object(WebDriver driver)
	{
		super (driver);
	}
	
	//Elements of login page
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmailAddress;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement btnLogin;
	
	//Action Metods
	public void setEmailAddress(String email)
	{
		txtEmailAddress.sendKeys(email);
	}
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);//This email & password will get from actual test randomlly
	}
	public void clickLoginBtn()
	{
		btnLogin.click();
	}

}
