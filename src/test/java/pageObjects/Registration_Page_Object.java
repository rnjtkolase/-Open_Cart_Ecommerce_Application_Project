package pageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//This is page object class for Registration page


public class Registration_Page_Object extends BasePage 
{
	public Registration_Page_Object(WebDriver driver)
	{
		super(driver);
		
	}
	
	//Find Elements of Registration Page
	@FindBy(name="firstname") WebElement txtfirstName;
	@FindBy(name="lastname") WebElement txtlastName;
	@FindBy(name="email") WebElement txtEmail;
	@FindBy(name="password") WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
	@FindBy(xpath="//button[normalize-space()='Continue']") WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement ConfirmRegistrationmsg;
	
	
	
	//Action Methods
	public void setFirstName(String fName)
	{
		txtfirstName.sendKeys(fName);
	}
	public void setLastName(String Name)
	{
		txtlastName.sendKeys(Name);
	}
	public void setEmail(String emailId)//Here we are validating registration functinality,and this email fiels accept only static data,so every time this email field need unique emailid, beacuse with the same email we can not register every time and therefore test will failed.
	                                    //Therefor we need to generate this email id randomly at the run time.
	                                    //Data is two type one is static data which neve change meanse we can use same data multipel times and other is Dynamic/Random data which we have to keep changing.  
	{
		txtEmail.sendKeys(emailId);
	}
	public void setPassword(String psswrd) 
	{
		txtPassword.sendKeys(psswrd);
		
	}
	
	public void checkPolicy()
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", chkPolicy);
		chkPolicy.click();
	
	   
	}
	public void Continuebtn()
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", btnContinue);
		chkPolicy.click();
		 btnContinue.click();
	}
	public String getConfirmationMsg()//This method will return text therefore we have written String instead of void.
	{
		try
		{
		return(ConfirmRegistrationmsg.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	

	
	
  
}

