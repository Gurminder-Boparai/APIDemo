package demo;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js=new JsonPath(Payload.CoursePrice());
		
		//Print No of courses returned by API
		int courseCount=js.getInt("courses.size()");
		System.out.println(courseCount);
		
		//Print Purchase Amount
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//Print Title of the first course
		String title=js.get("courses[0].title");
		System.out.println(title);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<courseCount;i++) {
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println( js.get("courses["+i+"].price").toString());	
		}
		
		//Print no of copies sold by RPA Course
		for(int i=0;i<courseCount;i++) {
			String titleC=js.get("courses["+i+"].title");
			if(titleC.equalsIgnoreCase("rpa")) {
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
		}
	}
}
