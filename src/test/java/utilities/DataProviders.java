package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
  // Data Provider 1
	@DataProvider(name = "LoginData")
	public String [][] getData() throws IOException
	{
		String path = ".\\testData\\LoginData.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrow = xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellCount("sheet1", 1);
		
	 String logindata[][]= new String [totalrow][totalcols];
	 
	 for (int i=1;i<=totalrow;i++)
	 {
		 for (int j = 0;j<=totalcols;j++)
		 {
			 logindata[i-1][j]= xlutil.getCellData("sheet1", i, j);
		 }
	 }
	 
	 return logindata;
	}
}
