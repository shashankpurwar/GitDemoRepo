import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;
@Test
public class Jira {
	
	public static void jira()
	{
		//Login in JIRA
		RestAssured.baseURI = "http://localhost:8080";
		String response =given().log().all().header("Content-Type", "application/json")
		.body("{ \"username\": \"purwar.shashank\", \"password\": \"Pallavi@123\" }")
		.when().post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String session = "JSESSIONID="+js.get("session.value");
		System.out.println(session);
		
		//Create defect
		RestAssured.baseURI = "http://localhost:8080";
		String defresp = given().log().all().header("Content-Type", "application/json").header("cookie", session)
		.body("{\r\n"
				+ "\"fields\": {\r\n"
				+ "\"project\": \r\n"
				+ "{\r\n"
				+ "\"key\": \"AS\"\r\n"
				+ "},\r\n"
				+ "\"summary\": \"First Defect from automation\",\r\n"
				+ "\"description\": \"this is my First automation Defect\",\r\n"
				+ "\"issuetype\": {\r\n"
				+ "\"name\": \"Bug\"\r\n"
				+ "}\r\n"
				+ "}\r\n"
				+ "}").when().post("/rest/api/2/issue").then().assertThat()
		.statusCode(201).extract().response().asString();
		
		JsonPath js1 = new JsonPath(defresp);
		String defectid = js1.get("id").toString();
		String keygen = js1.get("key");
		System.out.println(defectid);
		System.out.println(keygen);

				
		//add comment in existing defect
		RestAssured.baseURI = "http://localhost:8080";
		
		String responsecom =given().log().all().pathParam("id", defectid).header("Content-Type", "application/json").header("cookie", session)
		.body("{\r\n"
				+ "    \"body\": \"comment 1 added successfully \",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").when().log().all().post("/rest/api/2/issue/{id}/comment").then().assertThat().statusCode(201)
						.extract().response().asString();
				JsonPath js3 = new JsonPath(responsecom);
				String commentid = js3.get("id");
				System.out.println(commentid);
				
				
				//adding second comment
				given().log().all().pathParam("id", defectid).header("Content-Type", "application/json").header("cookie", session)
						.body("{\r\n"
								+ "    \"body\": \"comment 1 added successfully \",\r\n"
								+ "    \"visibility\": {\r\n"
								+ "        \"type\": \"role\",\r\n"
								+ "        \"value\": \"Administrators\"\r\n"
								+ "    }\r\n"
								+ "}").when().log().all().post("/rest/api/2/issue/{id}/comment").then().assertThat().statusCode(201)
										.extract().response().asString();
								
				//adding third comment
				given().log().all().pathParam("id", defectid).header("Content-Type", "application/json").header("cookie", session)
				.body("{\r\n"
						+ "    \"body\": \"comment 1 added successfully \",\r\n"
						+ "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n"
						+ "    }\r\n"
						+ "}").when().log().all().post("/rest/api/2/issue/{id}/comment").then().assertThat().statusCode(201)
								.extract().response().asString();
						
						
				
		
				
		//Delete the defectID
		
		/*
		 * RestAssured.baseURI = "http://localhost:8080"; given().header("Content-Type",
		 * "application/json").header("cookie", session)
		 * .when().delete("/rest/api/2/issue/"+defectid+"").then().assertThat().
		 * statusCode(204);
		 */
		
		
		//add attachment
		RestAssured.baseURI = "http://localhost:8080";
		given().log().all().pathParam("defectno", defectid)
		.header("cookie", session)
		.header("Content-Type", "multipart/form-data")
		.header("X-Atlassian-Token", "no-check")
		.multiPart("file", new File("attachmet.txt"))
		.when().post("/rest/api/2/issue/{defectno}/attachments").then().log().all().assertThat().statusCode(200);
		
		// get defect details
		
		String defectdetails = given().log().all().header("cookie", session).pathParam("getdefect", defectid)
				.queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{getdefect}").then().log().all()
		.assertThat().statusCode(200).extract().response()
		.asString();
		System.out.println(defectdetails);
		
		

			
		
	}
	
	
	
	
	
	
	
	
	

}
