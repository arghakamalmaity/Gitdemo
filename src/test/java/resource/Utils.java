package resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecificationBuilder() throws IOException
	{
		
		if(req==null)
		{
		PrintStream ps = new PrintStream(new FileOutputStream("Log.txt"));
		req=new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURI")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(ps))
				.addFilter(ResponseLoggingFilter.logResponseTo(ps))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalValues(String key) throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\argha.maity\\Downloads\\My folder\\API testing\\API_workspace\\APIframework\\src\\test\\java\\resource\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPathValues(Response response, String keyValue)
	{
		String response2 = response.asString();
		JsonPath js = new JsonPath(response2);
		
		return js.get(keyValue).toString();
	}
	

}
