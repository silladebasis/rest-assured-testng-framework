package constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiPaths {
    GETBOOKING("/booking/{bookingId}"),
    GETBOOKINGIDS("/booking"),
    CREATEBOOKING("/booking"),
    DELETEBOOKING("/booking/{bookingId}"),
    UPDATEBOOKING("/booking/{bookingId}");
    private final String apiPath;
}
