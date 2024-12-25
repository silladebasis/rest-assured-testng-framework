package apis;

import base.BaseApi;
import constants.ApiPaths;
import io.restassured.response.Response;

public class CreateBookingAPI extends BaseApi {
    public CreateBookingAPI(){
        super();
    }
    public Response createBooking(Object payload){
        Response response = postRequest(ApiPaths.CREATEBOOKING.getApiPath(), payload);
        return response;
    }
}
