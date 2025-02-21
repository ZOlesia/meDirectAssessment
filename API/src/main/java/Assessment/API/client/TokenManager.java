package Assessment.API.client;

import Assessment.API.config.Config;
import Assessment.API.constants.ApiEndpoints;
import Assessment.API.exceptions.ApiException;
import Assessment.API.utils.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {
	private static String token;
	
	public static String getToken() {
        if (token == null) {
            generateToken();
        }
        return token;
    }
	
	private static void generateToken() {
        String baseUrl = Config.getProperty("base_url");
        String username = Config.getProperty("username");
        String password = Config.getProperty("password");

        String payload = TestData.createTokenPayload(username, password);
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post(baseUrl + ApiEndpoints.CREATE_TOKEN);

        if (response.getStatusCode() != 200) {
            throw new ApiException("Failed to generate token: " + response.getBody().asString());
        }

        token = response.jsonPath().getString("token");
    }
}
