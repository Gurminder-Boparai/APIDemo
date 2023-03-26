package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

import static io.restassured.RestAssured.*;

public class BasicsAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//validate if add place is working as expected
				//given- all inputs details
				//when- submit the API -resource & http method
				//then- validate the response
				
				RestAssured.baseURI="https://rahulshettyacademy.com";
				String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
				.header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
				System.out.println(response);
				//JsonPath Class
				JsonPath js=new JsonPath(response); //for parsing json
				String placeID =js.getString("place_id");
				System.out.println(placeID);
				//Add pLace-->update Place with new address
				//-->Get Place to validate if new address is present in response
				
				//Update Place
				
				String newAddress="70 Summer walk, Canada";
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+placeID+"\",\r\n"
						+ "\"address\":\""+newAddress +"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "")
				.when().put("maps/api/place/update/json")
				.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
			
				//Get Place
				String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", placeID)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
				
				JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
				String actualAddress=js1.getString("address");
				
				//Cucumber Junit,Testng
				Assert.assertEquals(actualAddress, "70 Summer walk, Canada");
	
	
	}
			
			

}


