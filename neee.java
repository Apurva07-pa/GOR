package Demo_framework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class neee {
	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));

		try {

			// 1. Open login page

			driver.get("https://gor-pathology.web.app/");

			Thread.sleep(2000);

			// 2. Perform Login

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@kennect.io");

			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Qwerty@1234");

			driver.findElement(By.xpath("//span[text()='Login']")).click();

			Thread.sleep(3000);

			// 3. Validate Home Page load

			String actualresult = driver.getTitle();
			System.out.println(actualresult);
			String expectedresult = "GOR Pathology Web Portal";

			if (actualresult.equals(expectedresult)) {

				System.out.println("Login successful, Home page loaded.");

			} else {

				System.out.println(" Login failed.");

				return;

			}

			// 4. Select a test in Cost Calculator
			driver.findElement(By.xpath("//div[@class='MuiBox-root jss84']")).click();
			Thread.sleep(3000);

			driver.findElement(By.xpath(
					"//div[@role='listbox']//li[@id ='patient-test-option-2']//span[@class='MuiIconButton-label']"))
					.click();
			driver.findElement(By.xpath("//div[@class='MuiBox-root jss84']")).click();
			Thread.sleep(3000);

			driver.findElement(By.xpath(
					"//div[@role='listbox']//li[@id ='patient-test-option-1']//span[@class='MuiIconButton-label']"))
					.click();
			Thread.sleep(1000);

			// 5. Apply discount
			driver.findElement(By.xpath("//div[@class='MuiFormControl-root']")).click();
			driver.findElement(By.xpath("//div//li[text()='10%']")).click();

			// 6. Click Calculate
			WebElement cost = driver.findElement(By.xpath("//div[@class='MuiBox-root jss96']"));

			System.out.println(" Total cost displayed: " + cost.getText());

			// 7. Add a new patient

			driver.findElement(By.xpath("//a//span[text()='Add']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//input[@id='outlined-add-todo-input']")).sendKeys("snow");

			driver.findElement(By.xpath("//button//span[text()='Save']")).click();

			Thread.sleep(2000);
			WebElement successfullyadded = driver.findElement(By.xpath("//div[@class='MuiAlert-message']"));

			System.out.println(" Patient added " + successfullyadded.getText());
			Thread.sleep(5000);

			// Back to Dashboard

			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(50));
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul//a//span[text()='Dashboard']")));

			driver.findElement(By.xpath("//ul//a//span[text()='Dashboard']")).click();

			driver.findElement(By.xpath("//div//ul//li[@class='MuiListItem-container'][1]")).click();

		} catch (Exception e) {
			System.out.println(" Exception: " + e.getMessage());

		} finally {

			driver.quit();

			System.out.println(" Test completed. Browser closed.");

		}

	}
}
