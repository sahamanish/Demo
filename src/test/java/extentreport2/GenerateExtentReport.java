package extentreport2;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Test_Cases.dataAcc;
import Utilities.Hash_Map;
import Utilities.Utils_Config;
import extentReport.ExtentReportManager;
import io.restassured.response.Response;

public class GenerateExtentReport {
	public static ExtentTest test;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	static String URL=Utils_Config.configclass1("URL");
	public static SoftAssert softAssertion;
	@BeforeSuite
	public void startReport() {
		
		extent=ExtentReportManager.getReport();
		
	}
	

	
	public static void post(int i) throws IOException {
		List<Map<String, String>> Method1=Hash_Map.getexceldatainMap(0);
		softAssertion= new SoftAssert();
		//for(int i=0;i<Method1.size();i++) {
		System.out.println("------------------------+++++++++++++----------------------------");
			if(Method1.get(i).get("Execute").equalsIgnoreCase("Yes")) {
				
				test=extent.createTest(Method1.get(i).get("Test_Cases"));
//				Response response=Testbase.minimizecode(i);
				Response response=dataAcc.minimize2(i);
				int statuscode1=response.getStatusCode();
				test.log(Status.INFO, "Method= "+Method1.get(i).get("Methods"));
				test.log(Status.INFO, "The link we used: "+URL+Method1.get(i).get("end_points"));
				
				test.log(Status.INFO, "ResponseBody is= "+response.getBody().asString());
				test.log(Status.INFO, "Status code is= "+statuscode1);
				
				int i1=Integer.parseInt(Method1.get(i).get("Status_Code")); 
				test.log(Status.INFO, "Validate all users");
				softAssertion.assertEquals(statuscode1, i1,Method1.get(i).get("Test_Cases")+" Failed because Expected: "+statuscode1+" Actual: "+i1);
				//assertEquals(statuscode1,i1,Method1.get(i).get("Test_Cases")+" has failed");
				
				System.out.println(Method1.get(i).get("Status_Code")+" "+statuscode1);
				if(i1==statuscode1)
				{
					test.log(Status.PASS, "Passed test");
				}
				else {
					test.log(Status.FAIL, "Failed test "+statuscode1+" is.");
				}
				
				
			}
			
			
			
		//}
		
	}
	
		
	
	@AfterSuite
	public void finish() {
		softAssertion.assertAll();
		
		extent.flush();// it will instruct the Extentreport to write in html page 
	}
}
