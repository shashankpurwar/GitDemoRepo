package Pojo;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;



public class OauthCode {

	public static void main(String[] args) {
		
		String accessToken = "ya29.a0ARrdaM-0oJ7EiUATqfu4k_Hxr5vdmT4cVeAVlSJJLJZEi9Rpel2jYrh_srJ7-hb-Yi7l_oM60PbDp9ocmsil7WJVw4a3YVFmyF_ugzjickd1C5rnxE4lUhmunLHClmXTXdPZuD880r6LYSsudVp03SfXF5F82g";
		RestAssured.baseURI = "https://rahulshettyacademy.com/getCourse.php";
		GetCourses gs =given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when().get().as(GetCourses.class);
		//System.out.println(gs.getExpertise());
		//System.out.println(gs.getLinkedIn());
		//System.out.println(gs.getCourses().getApi().get(1).getCourseTitle());
		
		List<Api> gc=gs.getCourses().getApi();
		//System.out.println(gc);
		
		for(int i=0;i<gc.size();i++)
		{
			if(gs.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(gc.get(i).getPrice());
			}
		}
				
			ArrayList<String> actual = new ArrayList<String>();
			List<WebAutomation> gw =gs.getCourses().getWebAutomation();
			for(int i=0;i<gw.size();i++)
			{
				String title = gs.getCourses().getWebAutomation().get(i).getCourseTitle();
				actual.add(title);
				
			}
			
			//compare course titles of webAutomation course
			
			//expected
			String[] courseTitle = {"Selenium Webdriver Java", "Cypress", "Protractor"};
			List<String> expected = Arrays.asList(courseTitle);
			
			Assert.assertTrue(actual.equals(expected));
			
			
			
		
		
		
		
	}

}
