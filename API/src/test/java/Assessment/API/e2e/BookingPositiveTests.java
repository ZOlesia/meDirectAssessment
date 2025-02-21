package Assessment.API.e2e;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.databind.ObjectMapper;

import Assessment.API.utils.JsonReader;
import Assessment.API.utils.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Assessment.API.base.TestBase;
import Assessment.API.config.Config;
import Assessment.API.constants.ApiEndpoints;
import Assessment.API.models.Booking;
import Assessment.API.models.BookingDates;

public class BookingPositiveTests extends TestBase {
	private int bookingId;
	private Map<String, Object> bookingData;
	public Logger logger;
	
    public BookingPositiveTests(Map<String, Object> bookingData) {
        this.bookingData = bookingData;
        logger = LogManager.getLogger(this.getClass());
    }
	
	@Test(priority = 1)
	public void testCreateBooking() {
		logger.info("************** Creating Booking **************");
		
		Response response = apiClient.createBooking(bookingData);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for successful booking creation!");
		softAssert.assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected Content-Type!");
		softAssert.assertNotNull(response.getBody(), "Response body should not be null!");
		
		bookingId = response.jsonPath().getInt("bookingid");
		softAssert.assertTrue(bookingId > 0, "Invalid booking ID!");
		
		Map<String, Object> createdBooking = response.jsonPath().getMap("booking");
		assertBookingDataMatches(createdBooking, bookingData);
		
		Response getResponse = apiClient.getBooking(bookingId);
		softAssert.assertEquals(getResponse.getStatusCode(), 200, "Failed to retrieve booking!");
		
	    Map<String, Object> retrievedBooking = getResponse.jsonPath().getMap("$");
		assertBookingDataMatches(retrievedBooking, createdBooking);
		softAssert.assertAll();
		
		logger.info("************** Booking is created **************");
	}
	
	
	@Test(priority = 2, dependsOnMethods = "testCreateBooking")
    public void testGetBooking() {
		logger.info("************** Getting booking **************");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(bookingId > 0, "Booking ID should be greater than 0 before retrieving!");
		
        Response response = apiClient.getBooking(bookingId);
		
        softAssert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK for retrieving booking!");
        softAssert.assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected Content-Type!");
        softAssert.assertNotNull(response.getBody(), "Response body should not be null");
        
        Map<String, Object> retrievedBooking = response.jsonPath().getMap("$");
        assertBookingDataMatches(retrievedBooking, bookingData);
        softAssert.assertAll();
        
        logger.info("************** Booking is displayed **************");
    }
	
	
	@Test(priority = 3, dependsOnMethods = "testCreateBooking")
	public void testGetAllBookings() {
		logger.info("************** Getting all bookings **************");
		
	    Response response = apiClient.getBookings(null);
	    
	    SoftAssert softAssert = new SoftAssert();
	    softAssert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for retrieving all bookings!");
	    softAssert.assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected Content-Type!");
	    softAssert.assertNotNull(response.getBody(), "Response body should not be null!");
	    
	    List<Integer> bookingIds = response.jsonPath().getList("bookingid");
	    softAssert.assertTrue(bookingIds.size() > 0, "No bookings found!");
	    softAssert.assertTrue(bookingIds.contains(bookingId), "Previously added booking ID not found in the response!");
	    softAssert.assertAll();
	    
	    logger.info("************** All bookings are displayed **************");
	}
	
	
	@Test(priority = 4, dependsOnMethods = "testCreateBooking")
	public void testGetBookingsWithNameFilters() {
		logger.info("************** Getting booking with name filters **************");
		
	    Map<String, String> queryParams = new HashMap<>();
	    queryParams.put("firstname", bookingData.get("firstname").toString());
	    queryParams.put("lastname", bookingData.get("lastname").toString());
	    
	    Response response = apiClient.getBookings(queryParams);
	    
	    SoftAssert softAssert = new SoftAssert();
	    softAssert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for retrieving filtered bookings!");
	    softAssert.assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected Content-Type!");
	    softAssert.assertNotNull(response.getBody(), "Response body should not be null!");
	    
	    List<Integer> bookingIds = response.jsonPath().getList("bookingid");
	    softAssert.assertTrue(bookingIds.size() > 0, "No bookings found with the specified name filters!");
	    softAssert.assertTrue(bookingIds.contains(bookingId), "Previously added booking ID not found in the response!");
	    softAssert.assertAll();
	    
	    logger.info("************** Bookings with name filters are displayed **************");
	}
	
	
	// API is broken https://restful-booker.herokuapp.com/apidoc/index.html
	@Test(priority = 5, dependsOnMethods = "testCreateBooking")
	public void testGetBookingsWithDateFilters() {
		logger.info("************** Getting booking with date filters **************");
		
	    Map<String, String> queryParams = new HashMap<>();
	    Map<String, String> bookingDates = (Map<String, String>) bookingData.get("bookingdates");
	    queryParams.put("checkin", bookingDates.get("checkin").toString());
	    queryParams.put("checkout", bookingDates.get("checkout").toString());
	    
	    Response response = apiClient.getBookings(queryParams);
	    
	    SoftAssert softAssert = new SoftAssert();
	    softAssert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for retrieving filtered bookings!");
	    softAssert.assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected Content-Type!");
	    softAssert.assertNotNull(response.getBody(), "Response body should not be null!");
	    
	    List<Integer> bookingIds = response.jsonPath().getList("bookingid");
	    softAssert.assertTrue(bookingIds.size() > 0, "No bookings found with the specified date filters!");
	    softAssert.assertTrue(bookingIds.contains(bookingId), "Previously added booking ID not found in the response!");
	    
	    softAssert.assertAll();
	    
	    logger.info("************** Bookings with date filters are displayed **************");
	}
	
	
	@Test(priority = 6, dependsOnMethods = "testCreateBooking")
    public void testUpdateBooking() {
		logger.info("************** Updating booking **************");
		
		Booking payload = Booking.builder()
                .firstname("Kim")
                .lastname("Kardashian")
                .totalprice(1200)
                .depositpaid(true)
                .bookingdates(BookingDates.builder()
                        .checkin("2021-01-01")
                        .checkout("2022-01-01")
                        .build())
                .additionalneeds("keep Kanye West away")
                .build();
		
		
        Response response = apiClient.updateBooking(bookingId, JsonReader.toJson(payload));
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected Content-Type!");
        softAssert.assertNotNull(response.getBody(), "Response body should not be null!");
        
        Response getResponse = apiClient.getBooking(bookingId);
        softAssert.assertEquals(getResponse.getStatusCode(), 200, "Failed to retrieve booking!");
		
		Map<String, Object> retrievedBooking = getResponse.jsonPath().getMap("$");
		assertBookingDataMatches(retrievedBooking, response.jsonPath().getMap("$"));
		softAssert.assertAll();
		
		logger.info("************** Booking is updated **************");
    }
	
	@Test(priority = 7, dependsOnMethods = "testCreateBooking")
    public void testPartailUpdateBooking() {
		logger.info("************** Partially updating booking **************");
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("firstname", "Bianca");
		payload.put("lastname", "Censori");
		
        Response response = apiClient.partialUpdateBooking(bookingId, JsonReader.toJson(payload));
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals("application/json; charset=utf-8", response.getContentType(), "Unexpected Content-Type!");
        softAssert.assertNotNull(response.getBody(), "Response body should not be null!");
        
        Response getResponse = apiClient.getBooking(bookingId);
        softAssert.assertEquals(getResponse.getStatusCode(), 200, "Failed to retrieve booking!");
		
		Map<String, Object> retrievedBooking = getResponse.jsonPath().getMap("$");
		assertBookingDataMatches(retrievedBooking, response.jsonPath().getMap("$"));
		softAssert.assertAll();
		
		logger.info("************** Booking is updated **************");
    }
	
	@Test(priority = 8, dependsOnMethods = "testCreateBooking")
    public void testDeleteBooking() {
		logger.info("************** Deleting booking **************");
		
        Response response = apiClient.deleteBooking(bookingId);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 201, "Expected 201 Created on delete!");
        softAssert.assertNotNull(response.getBody(), "Response body should not be null!");
        softAssert.assertEquals(response.getBody().asString(), "Created", "Expected response body to be 'Created'!");
        
        Response getResponse = apiClient.getBooking(bookingId);
        softAssert.assertEquals(getResponse.getStatusCode(), 404, "Expected 404 Not Found after deletion!");
        softAssert.assertAll();
        
        logger.info("************** Booking is deleted **************");
    }
	
    private void assertBookingDataMatches(Map<String, Object> actual, Map<String, Object> expected) {
    	SoftAssert softAssert = new SoftAssert();
    	
    	softAssert.assertEquals(expected.get("firstname"), actual.get("firstname"), "First name mismatch!");
    	softAssert.assertEquals(expected.get("lastname"), actual.get("lastname"), "Last name mismatch!");
    	softAssert.assertEquals(expected.get("totalprice"), actual.get("totalprice"), "Total price mismatch!");
    	softAssert.assertEquals(expected.get("depositpaid"), actual.get("depositpaid"), "Deposit paid mismatch!");
    	softAssert.assertEquals(expected.get("additionalneeds"), actual.get("additionalneeds"), "Additional needs mismatch!");

        Map<String, String> expectedDates = (Map<String, String>) expected.get("bookingdates");
        Map<String, String> actualDates = (Map<String, String>) actual.get("bookingdates");

        softAssert.assertEquals(expectedDates.get("checkin"), actualDates.get("checkin"), "Check-in date mismatch!");
        softAssert.assertEquals(expectedDates.get("checkout"), actualDates.get("checkout"), "Check-out date mismatch!");
        
        softAssert.assertAll();
    }
}
