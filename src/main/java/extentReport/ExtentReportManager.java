package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	static String add1=System.getProperty("user.dir")+"/ExtentReport/MyOwnReport.html";
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	
	
	public static ExtentReports getReport() {
		if(extent==null) {
			extent=new ExtentReports();
			spark = new ExtentSparkReporter(add1);//it is an observer
			spark.config().setDocumentTitle("MyFirstTest");
			spark.config().setReportName("Testing Methods from my Project");
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setEncoding("utf-8");
			extent.attachReporter(spark);
			extent.setSystemInfo("Company Name", "AQM Technelogies");
			extent.setSystemInfo("Project Name", "TEst Version 5");
			extent.setSystemInfo("Tester Name", "Mohit");
		}
		return extent;
		
	}
}
