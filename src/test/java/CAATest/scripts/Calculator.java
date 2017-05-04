package CAATest.scripts;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.ios.IOSDriver;
import java.net.URL;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Calculator {

    private IOSDriver driver;
    private int sumResult = 0;

    @Before
    public void startApp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "10.3"); //Replace this with your Android version
        caps.setCapability("deviceName", "iPhone 6"); //Replace this with your simulator/device 
        caps.setCapability("app", "http://appium.github.io/appium/assets/TestApp7.1.app.zip"); //Replace this with app path in your system
        
        //For real device
        /*caps.setCapability("app", "/Users/dennischeong/Desktop/TestApp/TestApp.ipa"); //Build by yourself!
        caps.setCapability("xcodeOrgId", "AVHMB383Q7");
        caps.setCapability("xcodeSigningId", "iPhone Developer");
        caps.setCapability("udid", "545546248e1d6d4a730ad08fde91e5f7b71f2630");*/
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    }

    @After
    public void quitApp() {
        driver.quit();
    }

    @Given("^A random number in first field$")
    public void A_random_number_in_first_field() {
        int randomNumber = (int )(Math.random() * 10 + 1);
        sumResult += randomNumber;
        driver.findElement(By.id("IntegerA")).sendKeys(randomNumber+"");
    }

    @And("^A random number in second field$")
    public void A_random_number_in_second_field() {
        int randomNumber = (int )(Math.random() * 10 + 1);
        sumResult += randomNumber;
        driver.findElement(By.id("IntegerB")).sendKeys(randomNumber+"");
    }

    @When("^Click on CalculateSum button$")
    public void Click_on_CalculateSum_button() {
        driver.findElementByAccessibilityId("ComputeSumButton").click();
    }

    @Then("^I can see the sum of both numbers$")
    public void I_can_see_the_sum_of_both_numbers() {
        Assert.assertEquals(sumResult, Integer.parseInt(driver.findElementByAccessibilityId("Answer").getText()));
    }
}
