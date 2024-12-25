package payloads;

import java.util.HashMap;
import java.util.Map;

public class PayloadHelper {
    public static Map<String,Object> createBookingPayload(String firstName, String lastName, String additionalNeeds){
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
}
