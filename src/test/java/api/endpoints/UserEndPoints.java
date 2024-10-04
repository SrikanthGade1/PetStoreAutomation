package api.endpoints;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
 Below java file has been created to perform CRUD operations for user API
 
 */

public class UserEndPoints {
	

	// Below is being made public static so that it can be accessed anywhere in the project
	public static Response createUser(user payload){
		
		Response response = given() 
		// Below is what sort of data that is being sent
		// In CURL, -H represents header which is being passed below
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		
		.when()
		    .post(Routes.post_url);
		
		return response;		
	}
	
	
	public static Response readUser(String userName){
		
		Response response = given() 
		// Below we are taking parameter as username and providing the value parameter as below
		    .pathParam("username", userName)
		
		.when()
		    .get(Routes.get_url);
		
		return response;		
	}
	
	public static Response updateUser(String userName, user payload){
		
		Response response = given() 
			    .contentType(ContentType.JSON)
			    .accept(ContentType.JSON)
			    .pathParam("username", userName)
			    .body(payload)
			    
				.when()
			        .put(Routes.put_url);

		return response;		
	}
	
	public static Response deleteUser(String userName) {
		
		Response response = given()
	        .pathParam("username", userName)
		
		.when()
		     .delete(Routes.delete_url);
		
		return response;
		
	}

}
