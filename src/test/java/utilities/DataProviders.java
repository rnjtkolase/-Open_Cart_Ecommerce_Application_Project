package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{

	//DataProvider 1
	
	@DataProvider(name="LoginData")//"LoginData" is data provider name every data provider should have name and with this name we will provide data provider methods to the test cases.
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//location of excel sheet where we have to get the data from excel.
		                                                   //"./" is representing the current project directry, inside this testdata folder, and inside this we have excel file.
		
		//Now we have to read the data from excel sheet therefore we have created an object of ExcelUtility Class. and we are passing the path also.
		//And by using this object we can access all the methods of ExcelUtility class.
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);//"1" is row number it can be any row number.
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
	return logindata;//returning two dimension array
				
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
