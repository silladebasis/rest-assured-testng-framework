package tests;

import apis.CreateBookingAPI;
import apis.GetBookingAPI;
import apis.UpdateBookingAPI;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class CreateBookingApiTest extends BaseTest {
    @Test
    public void testCreateBooking(){
        var createBookingAPI = new CreateBookingAPI();
        var updateBookingAPI = new UpdateBookingAPI();
        var response = createBookingAPI.createBooking(createBookingPayload("Automation","Rest Assured","Postman")).
                                       then().extract().response();
        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        assertThat(bookingId, Matchers.is(Matchers.notNullValue()));
    }
    private Map<String, Object> createBookingPayload(String firstName, String lastName, String additionalNeeds) {
        Map<String,Object> requestPayload = new HashMap<>();
        Map<String,Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin","2025-01-01");
        bookingDatesMap.put("checkout","2025-02-01");
        requestPayload.put("firstname",firstName);
        requestPayload.put("lastname",lastName);
        requestPayload.put("totalprice",120);
        requestPayload.put("depositpaid",true);
        requestPayload.put("bookingdates",bookingDatesMap);
        requestPayload.put("additionalneeds",additionalNeeds);
        return requestPayload;
    }
    @DataProvider(name = "bookingDataWithLoop")
    public Object[][] getBookingData(){
        var faker = TestDataHelper.getFaker();
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;
        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Object[] objects = new Object[]{name.firstName(), name.lastName(), faker.bool().bool(),
                    faker.food().fruit(), faker.number().randomNumber(3, true),
                    TestDataHelper.getFutureDate(10, dateFormatter),
                    TestDataHelper.getFutureDate(15, dateFormatter)};
            list.add(objects);
        }
        return list.toArray(new Object[0][]);
    }
}
