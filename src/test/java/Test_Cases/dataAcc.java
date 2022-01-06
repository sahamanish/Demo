package Test_Cases;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import Utilities.Hash_Map;
import Utilities.Utils_Config;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//Created by Saha Manish On 04/11/2021;
//Description:This class is to run testng and collect alldata and show output; 
public class dataAcc {
	
	public static RequestSpecification httprequest;
	public static Response response;
	static String URL=Utils_Config.configclass1("URL");
	static String URL1=Utils_Config.configclass1("URL1");
	static JSONObject requestparm=new JSONObject();

	
	public static Response minimize2(int i) throws IOException {
		List<Map<String, String>> Method1=Hash_Map.getexceldatainMap(0);
		List<Map<String, String>> Method2=Hash_Map.getexceldatainMap(1);
		RestAssured.baseURI=URL;
		httprequest=RestAssured.given();
		

		String Methodname = Method1.get(i).get("Methods");
		if (Methodname.equalsIgnoreCase("get")) //For GET.....
		{
			response=httprequest.request(Method.GET,Method1.get(i).get("end_points"));
			//System.out.println(Method1.get(i).get("Methods")+" "+Method1.get(i).get("end_points"));

		}
		else if (Methodname.equalsIgnoreCase("post") || (Methodname.equalsIgnoreCase("put")) || (Methodname.equalsIgnoreCase("patch"))) //For POST.,PUT,PATCH.....
		{

			for(int j=0;j<Method2.size();j++) 
			{
				if(Method1.get(i).get("Test_Cases").equals(Method2.get(j).get("Test_Cases"))){
					int index=j;
					for(int h=1;h<Hash_Map.list.size();h++) {
						String header1=Hash_Map.list.get(h);
						requestparm.put(header1, Method2.get(index).get(header1));
						System.out.println("inside"+header1+" "+Method2.get(index).get(header1));
						//GenerateExtentReport.test.log(Status.INFO, "The data we "+Method1.get(i).get("Methods")+" is "+header1+Method2.get(index).get(header1));
					}
					httprequest.header("Content-Type","application/json");
					httprequest.body(requestparm.toJSONString());
					System.out.println("Requestbody is "+requestparm.toJSONString());

				}
			}
			if(Methodname.equalsIgnoreCase("post")){
				response=httprequest.request(Method.POST,Method1.get(i).get("end_points"));
			}
			if(Methodname.equalsIgnoreCase("put")){
				response=httprequest.request(Method.PUT,Method1.get(i).get("end_points"));
			}
			if(Methodname.equalsIgnoreCase("patch")){
				response=httprequest.request(Method.PATCH,Method1.get(i).get("end_points"));
			}



		}
		else if (Methodname.equalsIgnoreCase("delete")) 
		{
			httprequest.header("Content-Type", "application/json");	

			// Delete the request and check the response
			response = httprequest.request(Method.DELETE,Method1.get(i).get("end_points"));		
		}

		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:"+ responseBody);
		int statuscode1=response.getStatusCode();
		int was=Integer.parseInt(Method1.get(i).get("Status_Code"));
		System.out.println("Statuscode is:"+ statuscode1);
		
		

		if(statuscode1==was) 
		{
			Hash_Map.writexcel(true,i+1);
		}
		else {
			Hash_Map.writexcel(false,i+1);
		}
		return response;

	}
}
