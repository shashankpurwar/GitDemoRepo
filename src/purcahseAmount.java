import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class purcahseAmount {
	@Test
	public void totalcost()
	{
		
		
		int sum =0;
		JsonPath js = new JsonPath(Testdata.rawdata());
		int count = js.getInt("courses.size()");
		
		for(int i=0;i<count;i++)
		{
			int price = js.get("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int total = price*copies;
			//System.out.println(total);
			 sum = sum+total;
		}
		System.out.println(sum);
		int oldAmount = js.get("dashboard.purchaseAmount");
		System.out.println("old amount is "+oldAmount+"");
		Assert.assertEquals(sum, oldAmount);
	}

}
