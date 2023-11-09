package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public  class BaseClass 
{
	public static WebDriver driver;
	
	public Logger logger;//Logger is a class to configure logging, and that variable we need to initialize inside the setUp() method.
	
	public ResourceBundle rb;//ResourceBundle Is a predefined class which is imported from "java.util.ResourceBundle;"  to get the data from properties file.
	                         //Now with this rb variable we need to do setup in method as shown in setUP() method. which will load config.properties file in this Base class/in this test.
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")//This Annotation we need to mention here to recieve the parameters which we have created in xml 
	             //That xml we have added in testBase package and we have added a tag called <parameter name="browser" value="Chrome"/>.
	             //Parameter should recieved in setup() method therefore we need to creat a string variable like "public void setUp(String br)" as below.
	             //By usin using this parameter we can launch the specific browser which we mentioned in xml.
	public void setUp(String br) 
	{
		rb=ResourceBundle.getBundle("config");//to Load config.properties file.here we can write file name as just "config" or "config.properties" .properties extention is optional.
		                                      //Now by using this rb we able to access the variables & there values from properties file
		                                      //We access the variable as shown in launching application below
		logger=LogManager.getLogger(this.getClass());//LogManager is a predifined class which have getLogger() method.
		                              //In this method we have to pass current class name randamly like (this.getClass()), which will indentify the log is for which perticular test and all related informaton.
	                                  //Only this statement we have to write here for generating logs.
		                              //And also we will use this logger variable in every test case to generet the log.
		//Below ChromeOptions statememnt we write just to avoid a message that "crome is being controlled by automation"
		//and option variable we need to add like "driver=new ChromeDriver(options);"while launching a browser. 
		//ChromeOptions options=new ChromeOptions();
		//options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
		
		
		//For specific browser we are using if....else statement as below
		if(br.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if (br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();//This stetment is used to delet browser specific coockies , if prepopulated information which is stored in the webpage that will be cleared.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://demo.opencart.com/");//Here insted of hard coding url , we are taking it from propeties file as below
		driver.get(rb.getString("appURL"));//"appURL" is a key/variable written in properties file.
		driver.manage().window().maximize();
		
		
		
		
	}
	/*
	 @AfterClass(groups= {"Master","Sanity","Regression"})
	public void tear_down()
	{
		driver.close();;
	}
	*/
	
	
	//This is not necessary
	/*private ChromeOptions setExperimentalOptions(String string, String[] strings) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	//User Defined java methods to generate numbers and Strings Randomly.
		//And this two methods are purely Java methods which we can call from anywhere.here we are not using any annotation because this are not TestNG methods.
		
	public String randomeString()//This is user defined method randomeString().
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);//In java RandomStringUtils is a predefined class which we got it from java.lang3 dependancy from jar file of which we have already added in pom.xml.
		                                               //In this class there is a static method randomAlphabetic(). it will take number from 1 to 9. this method will generate randum string which will have 5 charecters or if we pass 20 then it will generet string which will have 20 charecters. And it any random charecters from A to Z
		                                               //That generated string will store in a variable and will return it.
		return(generatedString);
	}
	
	public String randomeNumber()
	{
		String generatedstring2 = RandomStringUtils.randomNumeric(10);//It will generet 10 digit number.Suppose we have to generate OTP with 4 numbers then we will pass 4.
		                                                              //This also should be in string format only because sendKeys() method accept only string data.
		return(generatedstring2);
	}
	
	//To generate alphanumeric, will add above both methods as below.
	public String randomeAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(5);
		String num=RandomStringUtils.randomNumeric(3);
		return(str+num);// Also we can add special carecter like return(str+"@"+num).
	}
	
	//Below is user defined method for capture screenshot
	public String captureScreen(String tname) throws IOException //this method will in ExtentReportManager class as shown in ExtentReportManager class. 
	{
		//Below this three statement generet the timestamp using existing predefined classes.
		//In java we have predefined class SimpleDateFormat to generet timestamp. for this we need to creat an object of this class as below.
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");//new SimpleDateFormat("yyyyMMddhhmmss") this will create an object by using new key word to generet the timestamp and also we need to specify the format like ("yyyyMMddhhmmss")
		Date dt=new Date();//Here create an object of Date class
		String timestamp=df.format(dt);//in this format we have to pass date class object
		
		//Above three statement can be write simple in single statement as below
		//String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());//Genereted timestamp that I am going to store in a veriable called String timeStamp.
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);//This method will capture the screen shot and will store in a variable called source.
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";//System.getProperty("user.dir") is for current project location.

		//below statement will keep in try catch block , because if screen shot is not captured properly then try block will execute and will through an exception
		try {
			FileUtils.copyFile(source, new File(destination));
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
		return destination;//returning the location of screen shot in string format where we are copying.this will return  location of screen shot there for type of this method is string -> public String captureScreen(String tname) throws IOException
	}
	

}
