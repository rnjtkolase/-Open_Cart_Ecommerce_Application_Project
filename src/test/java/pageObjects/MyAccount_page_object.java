package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount_page_object extends BasePage 
{
	public MyAccount_page_object(WebDriver driver)
	{
		super(driver);
	}
	
	//Elements of MyAccount page, here we are checking  that My Account text is present on page or not. Note: in page object we do not verify anything.
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement txtMyAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")WebElement logOutBtn;//This element we are locating to logout from current user loged in , because we need to pass different different login details through datadriven.
	
	//Action method
	
	public boolean isMyAccountPageExist()
	{
		try {
		return(txtMyAccount.isDisplayed());//If this txt displayed then this block will execute and will return true only else catch block will execute and then return false.
		}
		catch(Exception e)
		{
			return(false);
		}
	}
	
	public void clickLogOutBtn()
	{
		logOutBtn.click();
	}
	

}
