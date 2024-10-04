package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.user;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	user userPayload;
	
	public Logger logger; // for logs

	// We need to generate test data before each run. For that reason we are using
	// before class annotation
	@BeforeClass
	public void setUp() {

		// We created reference above for which we are creating an object below
		// Whatever data has been prepared needs to be passed to the user POJO class
		faker = new Faker();
		userPayload = new user();

		// In the below we are generating data and assigning to the POJO class
		// userPayload contains generated data that needs to be passed in tests
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// Above data needs to be passed to POST request as done below
		
		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("*****************Creating User********************");

		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("*****************User Created********************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {

		logger.info("*****************Reading User Info********************");
		// Need to check if this keyword is required as getUserName returns the same
		// value
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();

		// Only if we are using assertions, we need to use .getStatusCode(). Otherwise we can use .statusCode()
		// response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*****************User Information Read********************");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());

		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();

		Assert.assertEquals(response.statusCode(), 200);
		
		// Above assertion can be verified in below format as well
		// Below is chai assertion which comes along with Rest Assured
		response.then().log().body().statusCode(200);

		// Checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test (priority = 4)
	public void testDeleteUserByName() {
		
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
