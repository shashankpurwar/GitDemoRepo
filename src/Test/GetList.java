package Test;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;


public class GetList {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://reqres.in/api/users";
		ResourceList response = given().queryParam("page", 2).expect()
				.defaultParser(Parser.JSON).when().get()
		.as(ResourceList.class);
		/*
		 * System.out.println(response.getData().get(1).getId());
		 * System.out.println(response.getData().get(1).getAvatar());
		 * System.out.println(response.getData().get(1).getEmail());
		 */
		System.out.println(response.getPage());
		System.out.println(response.getPer_page());
		List<Data> data = response.getData();
		
		ArrayList<String> actual = new ArrayList<String>();
		for(int i =0;i<data.size();i++)
		{
			String s = response.getData().get(i).getLast_name();
			actual.add(s);
		}
		
		
		for(int i =0;i<data.size();i++)
		{
			if(response.getData().get(i).getEmail().equalsIgnoreCase("michael.lawson@reqres.in"))
			{
				System.out.println("Last name is  " +response.getData().get(i).getLast_name());
			}
			
					
		}
		
		String[] lastName = {"Lawson","Ferguson","Funke","Fields","Edwards", "Howell"};
		List<String> expceted = Arrays.asList(lastName);
		
		Assert.assertTrue(expceted.equals(actual));
		
		

		
		
	}

}
