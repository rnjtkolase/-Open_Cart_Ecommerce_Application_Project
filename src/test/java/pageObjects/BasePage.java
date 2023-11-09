package pageObjects;

//Here saperatly we are creating constructor only instead of writing constructor for all object class, this cunstroctor will common for all object class. because we need to create 2 object class to test registration functionality where 2 pages of an application are involved one is home page and another is Registration page. 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class BasePage 
{
	  public  WebDriver driver;//Webdriver variable
	
	//Constructor
	public BasePage(WebDriver driver) 
	{
		this.driver=driver;//driver initiated, and this represent current class
		PageFactory.initElements(driver, this);//PageFactory to avoid locator.
	}
	
}
