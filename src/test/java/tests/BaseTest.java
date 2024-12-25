package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class BaseTest {
    protected final Faker faker = TestDataHelper.getFaker();
    @DataProvider(name = "bookingData",parallel = true)
    public Object[][] getBookingData(){
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;
        return IntStream.range(0,5)
                        .mapToObj(i -> new Object[]{name.firstName(),name.lastName(),faker.bool().bool(),
                                faker.food().fruit(),faker.number().randomNumber(3,true),
                                TestDataHelper.getFutureDate(10,dateFormatter),
                                TestDataHelper.getFutureDate(15,dateFormatter)})
                        .toArray(Object[][]::new);
    }
}
