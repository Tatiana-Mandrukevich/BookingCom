package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BookingSteps {
    String city;

    @BeforeMethod
    public void initTest() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        options.addArguments("--disable-popup-blocking");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        setWebDriver(driver);

        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        Configuration.headless = false;
        Configuration.browserSize = "1024x768";
    }

    @Given("User is looking for hotels in {string} city")
    public void userIsLookingForHotelsInCity(String city) {
        this.city = city;
    }

    @When("User does search")
    public void userDoesSearch() {
        //open page
        //set value in city field
        //click on dates
        //select dates
        //click search
        open("https://www.booking.com/");
        $(By.name("ss")).setValue(city);
        $(By.cssSelector("[data-testid='searchbox-dates-container']")).click();
        $(By.cssSelector("[data-date='2025-03-25']")).click();
        $(By.cssSelector("[data-date='2025-04-05']")).click();
        $(By.cssSelector("[type='submit']")).click();
    }

    @Then("Hotel {string} should be on the search results page")
    public void hotelShouldBeOnTheSearchResultsPage() {

    }

    @AfterMethod
    public void endTest() {
        getWebDriver().quit();
    }

    @Then("The message contains \"([^\"]*)\"")
    public void theMessageIs(String message) {

    }

    @Given("User provide information:")
    public void userProvideInformation() {
    }

    @Then("Hotel {string} rating is {string}")
    public void hotelNorthBeachResortVillasRatingIs(int arg0, int arg1) {
    }
}
