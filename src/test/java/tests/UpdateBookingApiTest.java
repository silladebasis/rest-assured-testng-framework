package tests;

import apis.CreateBookingAPI;
import apis.UpdateBookingAPI;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static payloads.PayloadHelper.createBookingPayload;

public class UpdateBookingApiTest extends BaseTest{

    @Test(dataProvider = "bookingData")
    public void testUpdateBookingApi(String firstName,String lastName,boolean depositPaid,String additionalNeeds,Long totalPrice,
                                     String checkInDate,String checkOutDate){
        var createBookingAPI = new CreateBookingAPI();
        var updateBookingAPI = new UpdateBookingAPI();
        var response = createBookingAPI.createBooking(createBookingPayload(firstName,lastName,additionalNeeds)).
                                       then().extract().response();
        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        assertThat(bookingId, Matchers.is(Matchers.notNullValue()));

        var updateResponse = updateBookingAPI.updateBooking(createBookingPayload("Selenium","RestAsured","Automation"),bookingId).
                                             then().extract().response();
        Assert.assertEquals(updateResponse.getStatusLine(),"HTTP/1.1 200 OK");
    }

    public static void main(String[] args) {
        var faker = Faker.instance();
        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();
        var depositPaid = faker.bool().bool();
        var additionNeeds = faker.food().fruit();

        //var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //var futureDate = LocalDate.now().plusDays(faker.number().randomNumber(2,true)).format(dateFormatter);
        var futureDate = TestDataHelper.getFutureDate(20,DateTimeFormatter.ISO_DATE);
        System.out.println(firstName.concat(" ").concat(lastName).concat("and I need " + additionNeeds).
                                    concat(" and I have paid deposit : " + depositPaid));

        System.out.println("I will check in at : " + futureDate);

    }
}
