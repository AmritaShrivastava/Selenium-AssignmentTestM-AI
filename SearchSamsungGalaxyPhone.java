
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchSamsungGalaxyPhone {

    WebDriver driver;
    WebDriverWait wait;

    @Test(priority = 1)
    void launchAmazonWebsite() throws Exception {

        String username = "amritashrivastava1997";
        String accessKey = "LT_vIys2a1Bdv6N14LYyrZ6mHMreg3NhLmQpTr4yTV16lixgUj";

        // Browser capabilities
        MutableCapabilities browserOptions = new MutableCapabilities();
        browserOptions.setCapability("browserName", "Chrome");
        browserOptions.setCapability("browserVersion", "latest");
        browserOptions.setCapability("platformName", "Windows 11");

        // LambdaTest specific capabilities
        MutableCapabilities ltOptions = new MutableCapabilities();
        ltOptions.setCapability("project", "Amazon Automation");
        ltOptions.setCapability("build", "Parallel Test Build");
        ltOptions.setCapability("name", "Search Galaxy Test");
        ltOptions.setCapability("selenium_version", "4.21.0");

        // Attach LT options
        browserOptions.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(
                new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
                browserOptions
        );

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
    }



    @Test(priority = 2)
    void searchIphone() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Galaxy");
        driver.findElement(By.id("nav-search-submit-button")).click();
    }
    @Test(priority=3)
	void addToCart()
	{
		driver.findElement(By.id("a-autoid-1-announce")).click(); //here 1st listed galaxy is showing
	}


    @Test(priority = 4)
    void printIphonePrice() {

        // Wait for price element of first product
        WebElement price = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//span[@class='a-price-whole'])[1]")
                )
        );

        String galaxyPrice = price.getText();
        System.out.println("Selected Galaxy Price is:" + galaxyPrice);
    }

    @AfterClass
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
