package Assessment.API.utils;

import java.io.InputStream;

import com.google.gson.JsonObject;

public class TestData {
	public static String createBookingPayload(String firstname, String lastname, Integer totalprice, Boolean depositpaid,
            String checkin, String checkout, String additionalneeds) {
		JsonObject payload = new JsonObject();
		
		if (firstname != null && !firstname.isEmpty()) {
			payload.addProperty("firstname", firstname);
		}
		if (lastname != null && !lastname.isEmpty()) {
			payload.addProperty("lastname", lastname);
		}
		if (totalprice != null && totalprice > 0) {
			payload.addProperty("totalprice", totalprice);
		}
		if (depositpaid != null) {
			payload.addProperty("depositpaid", depositpaid);
		}
		if ((checkin != null && !checkin.isEmpty()) || (checkout != null && !checkout.isEmpty())) {
			JsonObject bookingDates = new JsonObject();
			
			if (checkin != null && !checkin.isEmpty()) {
				bookingDates.addProperty("checkin", checkin);
	        }
			if (checkout != null && !checkout.isEmpty()) {
				bookingDates.addProperty("checkout", checkout);
	        }
			payload.add("bookingdates", bookingDates);
		}
		
		if (additionalneeds != null && !additionalneeds.isEmpty()) {
			payload.addProperty("additionalneeds", additionalneeds);
		}
		return payload.toString();
	}
		

	public static String createTokenPayload(String username, String password) {
		JsonObject payload = new JsonObject();
		payload.addProperty("username", username);
		payload.addProperty("password", password);
		return payload.toString();
	}
}
