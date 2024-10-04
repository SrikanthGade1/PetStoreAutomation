package api.endpoints;

/*

URI --> https://petstore.swagger.io/

Create User(POST) --> https://petstore.swagger.io/v2/user
Get User(GET) --> https://petstore.swagger.io/v2/user/{{username}}
Update User(PUT) --> https://petstore.swagger.io/v2/user/{{username}}
Delete User(POST) --> https://petstore.swagger.io/v2/user/{{username}}  

*/


public class Routes {

	/*
	 * In the below:
	   Public - We can access anywhere in the project
	   static - We can use the variable directly by using class name without need for creation of object
	   String - We declared it as a String as its string type
	 */
	public static String base_url = "https://petstore.swagger.io/v2";
	
	// User Module
	// As base url is common, we are taking base_url and concatenating with required strings
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String put_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";
}
