import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {


		JsonPath js = new JsonPath(Testdata.rawdata());
		//Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);
		
		//Print Title of the first course
		
		String Title = js.get("courses[0].title");
		System.out.println(Title);
		
		//Print All course titles and their respective Prices
		
		for (int i =0;i<count;i++)
		{

			String t = js.get("courses["+i+"].title");
			String p =js.get("courses["+i+"].price").toString();
			System.out.println(t + p);
		}
		
		//Print no of copies sold by RPA Course
		
		
		for (int i =0;i<count;i++)
		{

			String t = js.get("courses["+i+"].title");
			if(t.equalsIgnoreCase("RPA"))
			{
				//String price = js.get("courses["+i+"].copies").toString();
				int price1 = js.get("courses["+i+"].copies");
				System.out.println(price1);
				break;
			}
		}
		
		for(int i=0;i<count;i++)
		{
			int price = js.get("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int total = price*copies;
			System.out.println(total);
		}
		
		
				
		
		

		
	}
}

		
		
		
		
		
		
	


