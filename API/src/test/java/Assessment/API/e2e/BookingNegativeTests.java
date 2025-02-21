package Assessment.API.e2e;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Assessment.API.base.TestBase;
import Assessment.API.utils.JsonReader;
import Assessment.API.utils.TestData;
import io.restassured.response.Response;

public class BookingNegativeTests extends TestBase {

	public Logger logger = LogManager.getLogger(this.getClass());
	public final static int invalidId = 9999999;
	
	
	@Test(priority = 1)
	public void testCreateBookingWithMissingFields() {
		logger.info("************** Creating Booking with missing fields **************");
	    String invalidPayload = "{}";

	    Response response = apiClient.createBooking(invalidPayload);
	    Assert.assertEquals(response.getStatusCode(), 500, "Expected 400 Bad Request for missing fields!");
	    
	    logger.info("************** Finished creating booking with missing fields **************");
	}
	
	@Test(priority = 2)
	public void testCreateBookingWithInvalidData() {
		logger.info("************** Creating booking with invalid fields **************");
		
	    String invalidPayload = TestData.createBookingPayload("", "", -1, false, "invalid-date", "invalid-date", "");

	    Response response = apiClient.createBooking(invalidPayload);
	    Assert.assertEquals(response.getStatusCode(), 500, "Expected 400 Bad Request for invalid data!");
	    
	    logger.info("************** Finished creating booking with invalid fields **************");
	}
	
	@Test(priority = 3)
	public void testGetBookingWithInvalidId() {
		logger.info("************** Getting booking with invalid id **************");
		
	    Response response = apiClient.getBooking(invalidId);
	    Assert.assertEquals(response.getStatusCode(), 404, "Expected 404 Not Found for invalid booking ID!");
	    
	    logger.info("************** Finished getting booking with invalid id **************");
	}
	
	@Test(priority = 4)
	public void testGetBookingIdsWithInvalidDateFilter() {
		logger.info("************** Getting booking ids with invalid date filter **************");
		
	  Map<String, String> queryParams = new HashMap<>();
	    queryParams.put("checkin", "$$$");
	    queryParams.put("checkout", "$$$");
	    
	    Response response = apiClient.getBookings(queryParams);
	    Assert.assertEquals(response.getStatusCode(), 500, "Expected 500 Not Found for update with invalid payload");
	    
	    logger.info("************** Finished getting booking ids with invalid date filter **************");
	}
	
	@Test(priority = 5)
	public void testUpdateNonExistentBooking() {
		logger.info("************** Updating non existing booking **************");
		
	    String payload = TestData.createBookingPayload("GhostUser", "Brown", 111, true, "2018-01-01", "2019-01-01", "Breakfast");

	    Response response = apiClient.updateBooking(invalidId, payload);
	    Assert.assertEquals(response.getStatusCode(), 405, "Expected 404 Not Found when updating a non-existent booking!");
	    
	    logger.info("************** Finished updating non existing booking **************");
	}
	
	@Test(priority = 6)
	  public void testUpdateBookingWithInvalidData() {
		logger.info("************** Updating booking with invalid data **************");
		
		  Response getResponse = apiClient.getBookings(null);
		  List<Integer> bookingIds = getResponse.jsonPath().getList("bookingid");
		  if (bookingIds.size() > 0) {
			  String invalidPayload = TestData.createBookingPayload(null, null, 111, true, null, null, "Breakfast");
			  Response response = apiClient.updateBooking(bookingIds.get(0), invalidPayload);
		      Assert.assertEquals(response.getStatusCode(), 400, "Expected 404 Not Found for update with invalid payload");
		  }
		  
		  logger.info("************** Finished updating booking with invalid data **************");
	  }
	
	@Test(priority = 7)
	public void testPartialUpdateNonExistentBooking() {
	  logger.info("************** Partially updating non existing booking **************");
		
	  Map<String, Object> payload = new HashMap<>();
		payload.put("firstname", "Bianca");
		payload.put("lastname", "Censori");
		
      Response response = apiClient.partialUpdateBooking(invalidId, JsonReader.toJson(payload));
      Assert.assertEquals(response.getStatusCode(), 405, "Expected 404 Not Found when updating a non-existent booking!");
      
      logger.info("************** Finished partially updating non existing booking **************");
	}
  
  @Test(priority = 8)
	public void testPartialUpdateWithInvalidData() {
	  logger.info("************** Partially updating booking with invalid data **************");
	  
	  Response getResponse = apiClient.getBookings(null);
	  List<Integer> bookingIds = getResponse.jsonPath().getList("bookingid");
	  if (bookingIds.size() > 0) {
		  String invalidPayload = "{}";
		  Response response = apiClient.updateBooking(bookingIds.get(0), invalidPayload);
	      Assert.assertEquals(response.getStatusCode(), 400, "Expected 404 Not Found for update with invalid payload");
	  }
	  
	  logger.info("************** Finished partially updating booking with invalid data **************");
	}
	
	@Test(priority = 9)
	public void testDeleteNonExistentBooking() {
		logger.info("************** Deleting non existing booking **************");
	    Response response = apiClient.deleteBooking(invalidId);
	    Assert.assertEquals(response.getStatusCode(), 405, "Expected 404 Not Found for already deleted booking!");
	    logger.info("************** Finished deleting non existing booking **************");
	}
	
}
