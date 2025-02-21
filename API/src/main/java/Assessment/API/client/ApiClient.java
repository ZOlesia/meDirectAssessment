package Assessment.API.client;

import java.util.Map;

import Assessment.API.config.Config;
import Assessment.API.constants.ApiEndpoints;
import Assessment.API.exceptions.ApiException;
import Assessment.API.utils.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {	
	private final String baseUrl = Config.getProperty("base_url");
	
	public ApiClient() {
		RestAssured.baseURI = baseUrl;
	}
	
	public Response createBooking(String payload) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.body(payload)
				.post(baseUrl + ApiEndpoints.CREATE_BOOKING);
	}
	
	public Response createBooking(Map<String, Object> payload) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post(baseUrl + ApiEndpoints.CREATE_BOOKING);
    }
	
	public Response getBooking(int bookingId) {
		return RestAssured.given()
				.get(baseUrl + ApiEndpoints.GET_BOOKING.replace("{bookingId}", String.valueOf(bookingId)));
	}
	
	public Response getBookings(Map<String, String> queryParams) {
	    RequestSpecification request = RestAssured.given();
	    if (queryParams != null && !queryParams.isEmpty()) {
	        request.queryParams(queryParams);
	    }
	    return request.get(baseUrl + ApiEndpoints.CREATE_BOOKING);
	}
	
	public Response updateBooking(int bookingId, String payload) {
        return RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Cookie", "token=" + TokenManager.getToken())
            .body(payload)
            .put(baseUrl + ApiEndpoints.UPDATE_BOOKING.replace("{bookingId}", String.valueOf(bookingId)));
    }
	
	public Response partialUpdateBooking(int bookingId, String payload) {
	    return RestAssured.given()
	            .header("Content-Type", "application/json")
	            .header("Cookie", "token=" + TokenManager.getToken())
	            .body(payload)
	            .patch(baseUrl + ApiEndpoints.UPDATE_BOOKING.replace("{bookingId}", String.valueOf(bookingId)));
	}
	
	public Response deleteBooking(int bookingId) {
        return RestAssured.given()
            .header("Cookie", "token=" + TokenManager.getToken())
            .delete(baseUrl + ApiEndpoints.DELETE_BOOKING.replace("{bookingId}", String.valueOf(bookingId)));
    }
	
	public Response createToken(String payload) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.body(payload)
				.post(baseUrl + ApiEndpoints.CREATE_TOKEN);
	}
	
	public Response healthCheck() {
		return RestAssured.given()
				.get(baseUrl + ApiEndpoints.HEALTH_CHECK);
	}
}
