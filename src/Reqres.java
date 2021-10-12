import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

@Test

public class Reqres {

	public void reqres() throws IOException {


		RestAssured.baseURI = "https://reqres.in";
		String Response = given().header("Content-Type" , "application/json")
				.body(new String (Files.readAllBytes(Paths.get("C:\\Users\\purwa\\Downloads\\RestAPI\\latestfile.jason"))))
		.when().post("api/users").then().assertThat().log().all().statusCode(201).body("job", equalTo("leader")).extract()
		.response().asString();
		
		JsonPath js = new JsonPath(Response);
		String ids = js.get("id");
		System.out.println(ids);
		
	}

}
