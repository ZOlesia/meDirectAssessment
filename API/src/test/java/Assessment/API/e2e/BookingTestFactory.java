package Assessment.API.e2e;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import Assessment.API.utils.JsonReader;

public class BookingTestFactory {
	@DataProvider(name = "bookingDataProvider")
    public Object[][] getBookingData() {
        List<Map<String, Object>> testData = JsonReader.readJsonArray("src/test/resources/testData.json");
        return testData.stream()
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
    }
	
	@Factory(dataProvider = "bookingDataProvider")
    public Object[] createTestInstances(Map<String, Object> bookingData) {
        return new Object[]{new BookingPositiveTests(bookingData)};
    }
}
