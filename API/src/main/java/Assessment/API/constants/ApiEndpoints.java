package Assessment.API.constants;

import Assessment.API.config.Config;

public class ApiEndpoints {
	public static final String BASE_URL = Config.getProperty("base_url");
    public static final String CREATE_BOOKING = "/booking";
    public static final String GET_BOOKING = "/booking/{bookingId}";
    public static final String GET_BOOKINGS = "/booking";
    public static final String UPDATE_BOOKING = "/booking/{bookingId}";
    public static final String DELETE_BOOKING = "/booking/{bookingId}";
    public static final String CREATE_TOKEN = "/auth";
    public static final String HEALTH_CHECK = "/ping";
}
