package Selenium_proj;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class OrderStatus {
	
	/*
	 * Scenario: Verify Order Details Status
	 * 
	 * Steps:
	 * 
	 * Open the website. Click on the dropdown and select "My orders".
	 * Click on the order "Detail". Get all detail sections.
	 *  Compare the actual text in each detail section with the expected text.
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		 
        // Step 1: Log In
        driver.get("http://localhost:5106");
        driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		// wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		
        WebElement usernameField = driver.findElement(By.id("Input_Email"));
       
        WebElement passwordField = driver.findElement(By.id("Input_Password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Log in']"));

        usernameField.sendKeys("admin@microsoft.com");
        passwordField.sendKeys("Pass@word1");
        loginButton.submit();
        Thread.sleep(1000);
        WebElement arrowDownImage = driver.findElement(By.cssSelector("img.esh-identity-image[src='/images/arrow-down.png']"));
        arrowDownImage.click();

        // Click on the div with text "My orders"
        WebElement myOrdersDiv = driver.findElement(By.xpath("//div[text()='My orders']"));
        myOrdersDiv.click();

        // Click on the anchor with text "Detail"
        WebElement detailLink = driver.findElement(By.xpath("//a[text()='Detail']"));
        detailLink.click();

        // Find all detail sections
        List<WebElement> detailSections = driver.findElements(By.xpath("//article[@class='esh-orders-detail-items row']//following::section"));


        String expectedText = "1, 08/29/2023 12:21:36 +00:00, $39.00, Pending, NET Bot Black Sweatshirt, $ 19.50";
        List<String> expectedParts = Arrays.asList(expectedText.split(","));

        // Iterate through each detail section
        for (WebElement detailSection : detailSections) {
            String actualText = detailSection.getText();
            List<String> actualTextLines = Arrays.asList(actualText.split("\n"));

            boolean isMatch = false;
            for (String part : expectedParts) {
                if (actualTextLines.contains(part.trim())) {
                    isMatch = true;
                    System.out.println("Actual and expected text are Equal" + ":" + actualText + " "+ "=" +expectedText);
                    break;
                }
            }

          
        }

        driver.quit();

}
}
