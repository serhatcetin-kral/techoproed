package com.techproed.javadevapi01;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseHerOkuApp;
public class GetRequest07 extends TestBaseHerOkuApp {

	/*
	 * 
	 * When 
	  		I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/5 
	  Then 
		  HTTP Status Code should be 200
		  And response content type is “application/JSON” 
		  And response body should be like; 
		  { 
		  	"firstname": "Sally", 
		    "lastname": "Ericsson", 
		    "totalprice": 111,
		    "depositpaid": false, 
		    "bookingdates": { "checkin": "2017-05-23", 
		                      "checkout":"2019-07-02" }
		  }
	 * 
	 * 
	 */
	
	
	
	@Test
	public void get01() {
		
		spec.pathParams("bookingName", "booking",
				"idValue",5);
		//set ehe expected data
		Response response=given().spec(spec).when().get("/{bookingName}/{idValue}");
		response.prettyPrint();
		
		
		
		
		//assert
		//1.way:
//		response.
//		then().
//		assertThat().
//		statusCode(200).
//		contentType(ContentType.JSON).
//		body("firstname", equalTo("Jim"),
//			 "lastname", equalTo("Smith"),
//			 "totalprice", equalTo(676),
//			 "depositpaid", equalTo(false),
//			 "bookingdates.checkin", equalTo("2020-05-21"),
//			 "bookingdates.checkout", equalTo("2020-12-02")
//			 );
//	
		
		//2.way use "jsonpath"  class
		
		
		JsonPath json=response.jsonPath();
		assertEquals("Mark",json.getString("firstname"));
		assertEquals("Smith",json.getString("lastname"));
		assertEquals(355,json.getInt("totalprice"));
		assertEquals(false,json.getBoolean("depositpaid"));
		assertEquals("2018-02-25",json.getString("bookingdates.checkout"));
		
		
		//3.way jsonpath+sofassert
		
		//1.step
		SoftAssert softAssert=new SoftAssert();
		
		
		//2.way
		softAssert.assertEquals(json.getString("firstname"),"Sally");
		softAssert.assertEquals(json.getString("lastname"),"Jones");
		softAssert.assertEquals(json.getInt("totalprice"),289);
		softAssert.assertEquals(json.getBoolean("deposit"),false);
		softAssert.assertEquals(json.getString("bookingdates.checkin"),"2017-01-26");
		softAssert.assertEquals(json.getString("bookingdates.checkout"),"2017-01-26");
		
		//3.step
		softAssert.assertAll();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
	
	
}
