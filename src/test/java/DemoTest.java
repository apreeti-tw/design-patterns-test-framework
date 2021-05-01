import org.example.PageObjects.TravelHomePage;
import org.example.Utilities.Base;
import org.example.Utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DemoTest extends Base {
    WebDriver driver;
    TravelHomePage travelHomePage;

    @BeforeTest
    public void setup() throws IOException {
        driver = initialiseDriver();
        travelHomePage = new TravelHomePage(driver);
    }

    @Test (dataProvider = "getData")
    public void flightTest(HashMap<String, String> bookingDetails) {
        travelHomePage.goTo("https://rahulshettyacademy.com/dropdownsPractise/");
        System.out.println(travelHomePage.getFooter().getFlightAttr());
        System.out.println(travelHomePage.getNavigation().getFlightAttr());

        travelHomePage.setTripStrategy("ONEWAY");
        travelHomePage.checkFlightAvailability(bookingDetails);
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> bookingDetails = Utilities.getMap(System.getProperty("user.dir")+"/src/test/java/org/data/BookingDetails.json");
        return new Object[][] {
                {bookingDetails.get(0)}, {bookingDetails.get(1)}
        };
    }
}
