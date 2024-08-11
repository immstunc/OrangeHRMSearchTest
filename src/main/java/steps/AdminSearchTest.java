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

public class AdminSearchTest {

    private final WebDriver driver = new ChromeDriver();
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        driver.close();
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

    @Given("the user is logged in and on the Admin page")
    public void theUserIsLoggedInAndOnTheAdminPage() {
        logIn();

        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']")));
        adminMenu.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Admin']")));
    }

    @When("the user enters {string} in the Username field")
    public void theUserEntersInTheUsernameField(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        usernameInput.sendKeys(username);
    }

    @And("the user clicks the Search button")
    public void theUserClicksTheSearchButton() {
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button--secondary') and text()=' Search ']")));
        searchButton.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-background-container']")));
    }

    @Then("the user closes the browser tab")
    public void theUserClosesTheBrowserTab() {
        driver.close();
        // Eğer tarayıcıda sadece bir sekme açıksa, bu komut tüm tarayıcıyı kapatır.
    }
}

