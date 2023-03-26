package demo;

import java.security.PublicKey;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

//Verify if Sum of all Course prices matches with Purchase Amount

public class SumValidation {
	@Test
	public void sumOfCourses() {
		JsonPath js=new JsonPath(Payload.CoursePrice());
		int purchaseAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		int courseCount=js.getInt("courses.size()");
		int sum =0;
		for(int i=0;i<courseCount;i++) {
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			//System.out.println(price);
			int amount=price*copies;
			sum=sum+amount;
		}	
		System.out.println(sum);
		Assert.assertEquals(purchaseAmount, sum);
	}

}
