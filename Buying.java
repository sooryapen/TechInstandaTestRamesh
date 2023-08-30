package Selenium_proj;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Buying {
	
	  private static WebDriverWait wait;
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

        usernameField.sendKeys("demouser@microsoft.com");
        passwordField.sendKeys("Pass@word1");
        loginButton.submit();
        Thread.sleep(1000);

        // Step 2: Select Brand and Product
        WebElement brandDropdown = driver.findElement(By.id("CatalogModel_BrandFilterApplied"));
        WebElement productDropdown = driver.findElement(By.id("CatalogModel_TypesFilterApplied"));
        Select brandSelect = new Select(brandDropdown);
        brandSelect.selectByVisibleText(".NET");
        Select productSelect = new Select(productDropdown);
        productSelect.selectByVisibleText("T-Shirt");
        WebElement nextButton = driver.findElement(By.xpath("//input[@type='image']"));
        nextButton.click();
      
        // Step 3: Place the Order
        
        
        WebElement addToBasketButton = driver.findElement(By.xpath("//span[contains(text(),'NET Bot Black Sweatshirt')]//parent::div//parent::form//input[contains(@value,'ADD TO BASKET')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", addToBasketButton);
   	 Thread.sleep(1000);
   	addToBasketButton.click();  
   	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(1000));
    WebElement checkoutButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn esh-basket-checkout text-white']")));
    checkoutButton.submit();
    WebElement payNowButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value,'Pay Now')]")));
    payNowButton.click();
   
       

        // Verify the "Thanks for your Order!" message
        WebElement orderConfirmationMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thanks for your Order')]"));
        String expectedMessage = "Thanks for your Order!";
        String actualMessage = orderConfirmationMessage.getText();
      
       // Assert
        Assert.assertEquals(actualMessage, expectedMessage);
        
    

        driver.quit();

	}

}
