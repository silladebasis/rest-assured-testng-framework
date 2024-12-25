package apis;

import base.BaseApi;
import constants.ApiPaths;
import io.restassured.response.Response;

public class UpdateBookingAPI extends BaseApi {
    public Response updateBooking(Object payload,int id){
        Response response = putRequest(ApiPaths.UPDATEBOOKING.getApiPath(), payload,"bookingId",id);
        return response;
    }
}
