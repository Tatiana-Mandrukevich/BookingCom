package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
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
        $(By.name("ss")).setValue(city);
        $(By.cssSelector("[data-testid='searchbox-dates-container']")).click();
        $(By.cssSelector("[data-date='2025-04-07']")).click();
        $(By.cssSelector("[data-date='2025-04-14']")).click();
        $(By.cssSelector("[type='submit']")).click();
    }

    @Then("Hotel {string} should be on the search results page")
    public void hotelShouldBeOnTheSearchResultsPage(String hotelName) {
        ElementsCollection hotelsTitlesList = Selenide.$$(By.cssSelector("[data-testid='title']")).shouldHave(sizeGreaterThan(0));;
        ArrayList<String> hotelNames = new ArrayList();

        for(SelenideElement element : hotelsTitlesList) {
            hotelNames.add(element.getText());
        }

        Assert.assertTrue(hotelNames.contains(hotelName));
    }

    @Then("Hotel {string} rating is {string}")
    public void hotelRatingIs(String hotel, String rate) {
        String hotelRate = $x(String.format("//*[contains(text(),'%s')]/ancestor::div[@data-testid='property-card-container']//*[@data-testid='review-score']/div/div", hotel)).getText();
        Assert.assertEquals(hotelRate.split(" ")[1], rate);
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

    @And("User changes language to {string}")
    public void userChangesLanguage(String language) {
        $(By.cssSelector("[data-testid='header-language-picker-trigger']")).click();
        $x(String.format("//*[text()='%s']", language)).click();
    }

    @When("User filters by {string} and selects {string}")
    public void userFilters(String filterName, String filterValue) {
        $x(String.format("//*[text()='%s']//ancestor::fieldset//*[text()='%s']//ancestor::div[@data-filters-item]//*[@type=\"checkbox\"]", filterName, filterValue)).click();
    }

    @And("User goes to the site Booking.com")
    public void userGoesToTheSiteBookingCom() {
        open("https://www.booking.com/");
    }
}
