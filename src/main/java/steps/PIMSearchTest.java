package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMSearchTest {

    private final WebDriver driver = new ChromeDriver();
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    private void logIn() {

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement userNameInput=driver.findElement(By.name("username"));
        userNameInput.sendKeys("Admin");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement passwordInput=driver.findElement(By.name("password"));
        passwordInput.sendKeys("admin123");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

    }

    @Given("the user is logged in and on the PIM page")
    public void theUserIsLoggedInAndOnThePIMPage() {
        logIn();

        WebElement pimMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
        pimMenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='PIM']")));
    }

    @When("the user enters {string} in the Employee Name field")
    public void theUserEntersInTheEmployeeNameField(String employeeName) {
        WebElement employeeNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        employeeNameInput.sendKeys(employeeName);
    }

    @And("the user clicks the Search button")
    public void theUserClicksTheSearchButton() {
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button--secondary') and text()=' Search ']")));
        searchButton.click();

        // Arama sonuçlarının yüklendiğini doğrulayın
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-background-container']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Then("the user closes the browser tab")
    public void theUserClosesTheBrowserTab() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // Eğer tarayıcıda sadece bir sekme açıksa, bu komut tüm tarayıcıyı kapatır.
    }
}
