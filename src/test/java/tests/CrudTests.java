package tests;

import apis.CreateBookingAPI;
import apis.DeleteBookingAPI;
import apis.GetBookingAPI;
import apis.UpdateBookingAPI;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static payloads.PayloadHelper.createBookingPayload;

public class CrudTests extends BaseTest{
    @Test(description = "CRUD Operation on Restful Booker API",dataProvider = "bookingData")
    public void crudTest(String firstName,String lastName,boolean depositPaid,String additionalNeeds,Long totalPrice,
                         String checkInDate,String checkOutDate){
        var createBookingAPI = new CreateBookingAPI();
        var getBookingAPI = new GetBookingAPI();
        var updateBookingAPI = new UpdateBookingAPI();
        var deleteBookingAPI = new DeleteBookingAPI();
        var response = createBookingAPI.createBooking(createBookingPayload(firstName,lastName,additionalNeeds)).
                                       then().extract().response();
        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        assertThat(bookingId, Matchers.is(Matchers.notNullValue()));

        //Get the booking details
        var getBookingResponse = getBookingAPI.getBookingId(bookingId).then().extract().response();
        assertThat(getBookingResponse.getStatusCode(), Matchers.equalTo(200));
        assertThat(getBookingResponse.jsonPath().get("firstname"),Matchers.is(Matchers.equalTo(firstName)));

        //Update the booking details
        var updateResponse = updateBookingAPI.updateBooking(createBookingPayload("Selenium","RestAsured","Automation"),bookingId).
                                             then().extract().response();
        Assert.assertEquals(updateResponse.getStatusLine(),"HTTP/1.1 200 OK");

        //Delete the booking
        var deleteResponse =  deleteBookingAPI.deleteBooking(bookingId);
        Assert.assertEquals(deleteResponse.getStatusLine(),"HTTP/1.1 201 Created");
    }
}
