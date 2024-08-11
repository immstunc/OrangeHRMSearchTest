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

public class RecruitmentSearchTest {

    private final WebDriver driver = new ChromeDriver();
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.quit();
    }

    @Given("I am on the OrangeHRM login page")
    public void iAmOnTheOrangeHRMLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }



    @And("I log in with valid credentials")
    public void iLogInWithValidCredentials() {

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

    @When("I navigate to the Recruitment page")
    public void iNavigateToTheRecruitmentPage() {

        WebElement recruitmentMenu = driver.findElement(By.xpath("//span[text()='Recruitment']"));
        recruitmentMenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Recruitment']")));
    }

    @And("I select write candidate name as {string}")
    public void iSelectWriteCandidateNameAs(String arg0) {

        WebElement inputField = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        inputField.sendKeys(arg0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @Then("I click the Search button")
    public void iClickTheSearchButton() {
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button--secondary') and text()=' Search ']")));
        searchButton.click();

        // Arama sonuçlarının yüklendiğini doğrulayın (isteğe bağlı)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-background-container']")));
    }



}
