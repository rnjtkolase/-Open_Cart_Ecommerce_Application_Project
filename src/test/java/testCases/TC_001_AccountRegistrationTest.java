package testCases;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.Home_Page_Object;
import pageObjects.Registration_Page_Object;
import testBase.BaseClass;

//Here will do coding for test cases of Register functionality.

public class TC_001_AccountRegistrationTest extends BaseClass//And because of this extends also we no need to write WebDriver driver variable/driver instance at begining.
{
	

	//Here I am commenting two methods one is "void setUp()" & other is "void tear_down()", becuase I dont want to write here.
	//Why because this methods are common for all test because in every test case I need to set up browser and url and close browser, and I dont want to repeat it again & again so I will skip this here.
	//So Instead of writting in every test case , I will write this common methods in a single class i name it as Baseclass which will create under same package and will invoke in every test class.
	//And also in feature whatever required common method those also will keep in this Baseclass.
	//So here will write code only for Test.
	
	/*
	@BeforeClass
	void setup()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.opencart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}
	*/
	
	
	
	@Test(groups={"Regression","Master"})
	void test_Account_Registration() 
	{
		try {
		
		//Here we need to write one method to log each and every below events.
		logger.debug("Application logs........");
		logger.info("****TC_001_AccountRegistrationTest****");
		
		
		Home_Page_Object hp=new Home_Page_Object(driver);//To import the methods from Home_Page_Object class which is present in another package, just import by clicking on import, and pass the driver instance as parameter.
		hp.clickMyAccount();
		logger.info("-Clicked on My Account link-");
		
		hp.clickRegister();
		logger.info("-Clicked on Register link-");
		
		
		Registration_Page_Object regpage = new Registration_Page_Object(driver);
		logger.info("-Providing Customers Data-");
		regpage.setFirstName("Ranjit");//Here also we can pass random data by passing user defined method, and also if there is condition as first letter should be upper case then we can write like regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName("Kolase");
		regpage.setEmail(randomeString()+"@gmail.com");//we need to pass this data randamly.My expectation is that email automatically generated and new customer should be created.
		                                           //For that we need to use our own user defined methods which will create random numbers or strings at the run time.
		                                           //And this method may common it may usefull in every test, therefor we will create it in baseclass where we are keeping all reusable methods.
		                                           //Now we can directly call thos methods instead of hardcoding email.
		                                           //Now will convert the genereted data in to email format by concatinating.
		regpage.setPassword(randomeAlphaNumeric());
		
		regpage.checkPolicy();
	
		logger.info("-Checking policy-");
		
		
		regpage.Continuebtn();
		logger.info("-Clicked on Continue btn-");
		regpage.getConfirmationMsg();
		String conmsg=regpage.getConfirmationMsg();
		logger.info("-Varfying expected Msg-");
		
		Assert.assertEquals(conmsg,"Your Account Has Been Created!");
		
	}catch(Exception e)
	{
		Assert.fail();
	}
		
		
	}
	}
		
		
		
		
	
	


