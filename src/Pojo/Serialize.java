package Pojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;

public class Serialize {

	public static void main(String[] args) {
		
		
		SerilizePOJO sp = new SerilizePOJO();
		sp.setAccuracy(50);
		sp.setAddress("29, side layout, cohen 09");
		sp.setPhone_number("(+91) 983 893 3937");
		sp.setName("Frontline house");
		sp.setWebsite("http://google.com");
		sp.setLanguage("French-IN");
		
		SerializeLocationPOJO sl = new SerializeLocationPOJO();
		sl.setLat(-38.383494);
		sl.setLng(33.427362);
		sp.setLocation(sl);
		
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("shoe park");
		ar.add("shop");
		sp.setTypes(ar);
		
		//With request and response spec builder

	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.setContentType("application/json")
	.addQueryParam("key", "qaclick123").build();
	
	ResponseSpecification BuildResponse = new ResponseSpecBuilder().expectContentType("application/json")
			.expectStatusCode(200).build();
	
	RequestSpecification resp =given().log().all().spec(req)
			.body(sp);
			
			Response response = resp.when().post("/maps/api/place/add/json").then().log().all().spec(BuildResponse)
			.extract().response();
			
			String completeResponse = response.asString();
			System.out.println(completeResponse);
		
		
	//Without  request and response Spec Builder
	/*RestAssured.baseURI = "https://rahulshettyacademy.com";
		String resp =given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(sp).when().post("/maps/api/place/add/json").then().log().all()
		.statusCode(200).extract().response().asString();*/
		
		//String ResponseString = resp.asString();
		
		System.out.println(resp);
		
		
	}

}
