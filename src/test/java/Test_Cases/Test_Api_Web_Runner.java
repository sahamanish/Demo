package Test_Cases;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.Hash_Map;
import extentReport.ExtentReportManager;
import extentreport2.GenerateExtentReport;
import selinium_codes.browser_launcher;

public class Test_Api_Web_Runner {
	@BeforeSuite
	public void startReport() {

		GenerateExtentReport.extent=ExtentReportManager.getReport();

	}

	@Test (dataProvider = "data-provider")
	public void apiweb(int i) throws Exception {
		List<Map<String, String>> Method1=Hash_Map.getexceldatainMap(0);
		//		for(int i=0;i<Method1.size();i++) 
		//		{
		if(Method1.get(i).get("Environment").equalsIgnoreCase("API")) {
			GenerateExtentReport.post(i);
		}
		else if (Method1.get(i).get("Environment").equalsIgnoreCase("WEB")) {
			browser_launcher.openMoneyControl();
		}
		//		}



	}

	@DataProvider (name = "data-provider")
	public Object[] testrunner123() throws IOException  {
		List<Map<String, String>> Method1;
		int count_yes=0;
		Method1 = Hash_Map.getexceldatainMap(0);
		for(int i=0;i<Method1.size();i++) {
			if(Method1.get(i).get("Execute").equalsIgnoreCase("Yes")) 
			{
				count_yes++;
			}
		}

		Object[] newarr = new Object[count_yes];
		int u=0; 
		for(int i=0;i<Method1.size();i++) {

			if(Method1.get(i).get("Execute").equalsIgnoreCase("Yes")) 
			{

				newarr[u]=i;
				//					return new Object[] {Method1.get(i).get("Sr")};


			}
			else if(Method1.get(i).get("Execute").equalsIgnoreCase("No")) {
				continue;
			}
			u++;

		}
		Arrays.sort(newarr);
		return newarr;
	}
	
	@AfterSuite
	public void finish() {
		GenerateExtentReport.softAssertion.assertAll();
		
		GenerateExtentReport.extent.flush();// it will instruct the Extentreport to write in html page 
	}
}
