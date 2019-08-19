package tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestRunFacebook {
  private static final String CLICKARGUMENT = "arguments[0].click();";
  private ChromeDriver driver;

  @Rule
  public final TestRule watchman = new TestWatcher() {
    @Override
    public Statement apply(Statement base, Description description) {
      return super.apply(base, description);
    }

    // This method gets invoked if the test fails for any reason:
    @Override
    protected void failed(Throwable e, Description description) {
      // Print out the error message:
      System.out.println(description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n");
      // Now you can do whatever you need to do with it, for example take a screenshot
      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      try {
        File currPath = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\screenshot.png");
        System.out.println("Screenshot at: " + currPath.toString());
        FileHandler.copy(scrFile, currPath);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }

    // This method gets called when the test finishes, regardless of status
    // If the test fails, this will be called after the method above
    @Override
    protected void finished(Description description) {
      if (driver != null) {
        driver.quit();
      }
    }
  };

  @Before
  public void setUp() throws Exception {
    String driverExecutablePath = "./src/main/java/resources/chromedriver76.exe";
    System.setProperty("webdriver.chrome.driver", new File(driverExecutablePath).getCanonicalPath());
    driver = new ChromeDriver(setChromeOptions()); //instantiate a browser
    driver.get("https://www.facebook.de/");
  }

  @DisplayName("Selenium Facebook Test")
  @Test
  public void testFacebook() throws Exception {

    WebElement usernameFb = driver.findElement(By.xpath("//input[@id='email']"));
    usernameFb.click();
    usernameFb.sendKeys("christian.schmtz@outlook.de");

    driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);

    WebElement passwordFb = driver.findElement(By.xpath("//input[@id='pass']"));
    passwordFb.click();
    passwordFb.sendKeys("Il@yda1993");
    passwordFb.sendKeys(Keys.RETURN);

    WebElement projectDirico = (new WebDriverWait(driver, 20))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Dirico_Testprojekt')]")));
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.executeScript(CLICKARGUMENT, projectDirico);

    WebElement posts = (new WebDriverWait(driver, 20))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='_2yav'][contains(text(),'Beiträge')]")));
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.executeScript(CLICKARGUMENT, posts);

////-----------------------Löschen---------------------------------------------------------------------------

    deletePost(driver);
  }

  private void deletePost(ChromeDriver chromeDriver) {

    WebElement postOptions = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Beitragsoptionen')]")));
    chromeDriver.executeScript(CLICKARGUMENT, postOptions);

    WebElement markAll = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_2pi9 _4h2r']//button[@class='_1gcq _29c- _1gco _5e9w']")));
    chromeDriver.executeScript(CLICKARGUMENT, markAll);

    WebElement actions = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Handlungen')]")));
    chromeDriver.executeScript(CLICKARGUMENT, actions);

    WebElement delete = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Löschen')]")));
    chromeDriver.executeScript(CLICKARGUMENT, delete);

    WebElement permanentdelete = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Löschen")));
    chromeDriver.executeScript(CLICKARGUMENT, permanentdelete);
  }

  private ChromeOptions setChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    options.addArguments("disable-infobars");
    options.addArguments("--disable-notifications");
    options.addArguments("--lang=de_DE");
    return options;
  }
}