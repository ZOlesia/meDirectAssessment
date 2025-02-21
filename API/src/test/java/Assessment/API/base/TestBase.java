package Assessment.API.base;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import Assessment.API.client.ApiClient;
import Assessment.API.constants.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public abstract class TestBase {
	protected ApiClient apiClient;
	protected static boolean isHealthCheckPassed = false;
	
	@BeforeClass
	public void setUp() {
		if (!isHealthCheckPassed) {
            throw new IllegalStateException("Test suite cannot proceed because the health check failed.");
        }
		apiClient = new ApiClient();
	}
	
	@BeforeSuite
	public void checkHealth() {
		Response response = RestAssured.get(ApiEndpoints.BASE_URL + ApiEndpoints.HEALTH_CHECK);
		if (response.getStatusCode() == 201) {
            isHealthCheckPassed = true;
        }
    }
}
