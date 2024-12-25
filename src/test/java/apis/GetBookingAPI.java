package apis;

import base.BaseApi;
import constants.ApiPaths;
import io.restassured.response.Response;

public class GetBookingAPI extends BaseApi {
    public GetBookingAPI(){
        super();
    }
    public Response getAllBookingIds(){
        Response response = getRequest(ApiPaths.GETBOOKINGIDS.getApiPath());
        return response;
    }
    public Response getBookingId(int id){
        Response response = getRequest(ApiPaths.GETBOOKING.getApiPath(),"bookingId",id);
        return response;
    }
}
