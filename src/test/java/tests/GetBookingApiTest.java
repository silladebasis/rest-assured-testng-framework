package tests;

import apis.GetBookingAPI;
import listeners.RetryAnalyzer;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetBookingApiTest extends BaseTest {

    @Test(description = "Retrieves all booking Ids")
    public void testGetAllBookings(){
        var getBookingAPI = new GetBookingAPI();
        var response = getBookingAPI.getAllBookingIds().then().extract().response();
        assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }
    @Test(description = "Gets the specific booking details")
    public void testGetBookingDetailsById(){
        var getBookingAPI = new GetBookingAPI();
        var response = getBookingAPI.getBookingId(412).then().extract().response();
        assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }
}
