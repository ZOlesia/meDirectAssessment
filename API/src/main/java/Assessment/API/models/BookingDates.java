package Assessment.API.models;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
public class BookingDates {
    private String checkin;
    private String checkout;
}
