package Assessment.API.models;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
public class Booking {
	private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
