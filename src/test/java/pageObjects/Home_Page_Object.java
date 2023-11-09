package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//This is page object class for Home Page.
//This object class also having there own cunstroctor.



public class Home_Page_Object extends BasePage//Now here BasePage is a parent class of Home_Page_Object. 
{
	//Constructor of Home_Page_Object class 
	public Home_Page_Object(WebDriver driver)
	{
		super(driver);//Here we are invoking the parent class cunstroctor by using super keyword,super is a key word which we use to invoke parent class variable,super keyword can use to invoke parent class method, it also can use to invoke parent class constructor.
	}
	
	//Find elements by using @Find annotation because we are using PageFactory approch which we have defined in parent class.
	//Element1:My Account
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement linkMyaccount;
	
	//Element2:Register 
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement linkRegister;
	
	//Element3:logIn link
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement loginLink;
	
	
	//Action Methods
	public void clickMyAccount() 
	{
		
		linkMyaccount.click();//This will click on MyAccount
		
	}
	
	public void clickRegister()
	{
		linkRegister.click();//This will click on Register	
	}
	
	public void clickLogin()
	{
		loginLink.click();
	}
	
	
	
  
}
