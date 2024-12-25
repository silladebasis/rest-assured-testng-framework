package apis;

import base.BaseApi;
import constants.ApiPaths;
import io.restassured.response.Response;

public class DeleteBookingAPI extends BaseApi {
    public Response deleteBooking(int id){
        return deleteRequest(ApiPaths.DELETEBOOKING.getApiPath(),"bookingId",id);
    }
}
