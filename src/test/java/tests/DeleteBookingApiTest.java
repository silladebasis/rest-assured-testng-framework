package tests;

import apis.CreateBookingAPI;
import apis.DeleteBookingAPI;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static payloads.PayloadHelper.createBookingPayload;

public class DeleteBookingApiTest {
    DeleteBookingAPI deleteBookingAPI;
    CreateBookingAPI createBookingAPI;
    @BeforeClass
    public void initClass(){
        createBookingAPI = new CreateBookingAPI();
        deleteBookingAPI = new DeleteBookingAPI();
    }
    @Test
    public void testDeleteAPI(){
        var response = createBookingAPI.createBooking(createBookingPayload("Automation","Rest Assured","Postman")).
                                       then().extract().response();
        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println(bookingId);
        assertThat(bookingId, Matchers.is(Matchers.notNullValue()));

        var deleteResponse =  deleteBookingAPI.deleteBooking(bookingId);
        Assert.assertEquals(deleteResponse.getStatusLine(),"HTTP/1.1 201 Created");
    }
}
