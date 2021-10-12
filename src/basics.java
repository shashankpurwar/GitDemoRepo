import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;



public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key",  "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Frontline house\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n" 
				+ "    \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}").when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).extract().response().asString();
		
		//System.out.println(response);
		JsonPath js = new JsonPath(response); 
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		//update place
		
		String oldAddress = "70 winter walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+oldAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("/maps/api/place/update/json").then().assertThat()
		.statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		
		//Get place
		
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getResponse);
		String ActualAddress = js1.getString("address");
		System.out.println(ActualAddress);
		System.out.println(oldAddress);
		Assert.assertEquals(oldAddress, ActualAddress);


	}

}
