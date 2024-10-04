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

import java.util.ResourceBundle;

/*
 Below java file has been created to perform CRUD operations for user API
 
 */

public class UserEndPoints2 {
	
	// Below is made static so that it can be accessed directly using UserEndPoints2. Return type is ResourceBundle 
	// Below is method created for getting URLs from the properties file
	static ResourceBundle getURL()
	{
		// Location is not required as .getBundle automatically gets properties file from resources folder
		// In .getBundle("routes"), "routes" is name of the properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Loads properties file
		// By using routes object, we can get all URLs from the properties file
		return routes;
	}

	// Below is being made public static so that it can be accessed anywhere in the project
	public static Response createUser(user payload){
		
		// Below we are providing the key as post_url, storing it in a variable and sending the same under when as post_url
		String post_url = getURL().getString("post_url");
		
		Response response = given() 
		// Below is what sort of data that is being sent
		// In CURL, -H represents header which is being passed below
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		
		.when()
		    .post(post_url);
		
		return response;		
	}
	
	
	public static Response readUser(String userName){
		
		
		String get_url = getURL().getString("get_url");
		
		Response response = given() 
		// Below we are taking parameter as username and providing the value parameter as below
		    .pathParam("username", userName)
		
		.when()
		    .get(get_url);
		
		return response;		
	}
	
	public static Response updateUser(String userName, user payload){

		String put_url = getURL().getString("put_url");

		Response response = given() 
			    .contentType(ContentType.JSON)
			    .accept(ContentType.JSON)
			    .pathParam("username", userName)
			    .body(payload)
			    
				.when()
			        .put(put_url);

		return response;		
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url = getURL().getString("delete_url");

		Response response = given()
	        .pathParam("username", userName)
		
		.when()
		     .delete(delete_url);
		
		return response;
		
	}

}
 