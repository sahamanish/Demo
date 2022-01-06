package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//Created By Saha Manish On 4/11/2021;
//Description: It is used for passing Config.properties Parameters;
public class Utils_Config {

	public Utils_Config(){

	}
	public static String user=System.getProperty("user.dir");
	public static String configclass1(String parameter) {
		try {
			Properties prop = new Properties();
			FileInputStream input = new FileInputStream(user+"\\src\\main\\java\\config\\config.properties");
			prop.load(input);
			String param=prop.getProperty(parameter);
			//System.out.println(param);
			return param;



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;



	}

}
