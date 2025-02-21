package Assessment.API.e2e;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Assessment.API.base.TestBase;
import Assessment.API.config.Config;
import Assessment.API.utils.TestData;
import io.restassured.response.Response;

public class TokenTest extends TestBase {
	
	public Logger logger = LogManager.getLogger(this.getClass());
	private final static String username = Config.getProperty("username");
	private final static String password = Config.getProperty("password");
    
    @Test
    public void testTokenCreation() {
        logger.info("************** Creating Token **************");
		
        String payload = TestData.createTokenPayload(username, password);
		Response response = apiClient.createToken(payload);
		
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Token creation failed!");
        softAssert.assertNotNull(response.jsonPath().getString("token"), "Token should not be null!");
        softAssert.assertAll();
		
		logger.info("************** Token is created **************");
    }
}
