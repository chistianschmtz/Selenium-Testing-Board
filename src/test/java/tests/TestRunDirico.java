package tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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


public class TestRunDirico {
  private static final String CLICKARGUMENT = "arguments[0].click();";
  private static String POSTTEXT = "Test";
  private WebDriver driver;

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
//        File currPath = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\screenshot.png");
        File currPath = new File(new JFileChooser().getCurrentDirectory().toString() + "\\screenshot.png");
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
    driver.get("https://www.dirico.io/");
  }

  @Test
  public void testDirico() throws InterruptedException {

    try {
      WebElement expoPopUp = (new WebDriverWait(driver, 45))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='sgpb-popup-dialog-main-div-theme-wrapper-3']")));
      expoPopUp.click();
    } catch (Exception e) {
      e.printStackTrace();
    }

    WebElement login = (new WebDriverWait(driver, 15))
      .until(ExpectedConditions.presenceOfElementLocated(By.linkText("LOGIN")));
    login.click();
    WebElement userName = (new WebDriverWait(driver, 15))
      .until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
    userName.click();
    userName.sendKeys("christian.schmtz@outlook.de");

    WebElement password = (new WebDriverWait(driver, 15))
      .until(ExpectedConditions.presenceOfElementLocated(By.name("Password")));
    password.click();
    password.sendKeys("Il@yda1993");

    WebElement loginBtn = (new WebDriverWait(driver, 15))
      .until(ExpectedConditions.presenceOfElementLocated(By.name("button")));
    loginBtn.click();

//        Status

    createNewFbContent((ChromeDriver) driver);
    setFbChannel((ChromeDriver) driver);
    addStatusPost((ChromeDriver) driver);
    publishContent((ChromeDriver) driver);

    //Image
    if (isPublished((ChromeDriver) driver)) {
      createNewFbContent((ChromeDriver) driver);
      setFbChannel((ChromeDriver) driver);
      addImagePost((ChromeDriver) driver);
      publishMediaContent((ChromeDriver) driver);
    }

    //Video
    if (isPublished((ChromeDriver) driver)) {
      createNewFbContent((ChromeDriver) driver);
      setFbChannel((ChromeDriver) driver);
      addVideoPost((ChromeDriver) driver);
      publishMediaContent((ChromeDriver) driver);

    }
  }

  public void createNewFbContent(ChromeDriver chromeDriver) {
    WebElement contentTile = (new WebDriverWait(chromeDriver, 15))
      .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='content/all']")));
    chromeDriver.executeScript(CLICKARGUMENT, contentTile);

    WebElement newTask = (new WebDriverWait(chromeDriver, 25))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Neuer Inhalt']")));
    chromeDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, newTask);

    WebElement facebook = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//md-card[2]//md-card-content[1]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, facebook);
  }

  private void publishContent(ChromeDriver chromeDriver) {
    WebElement immediatleyPublish = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Sofort veröffentlichen?')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, immediatleyPublish);
    chromeDriver.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    WebElement publish = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Veröffentlichen')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, publish);
  }

  private void publishMediaContent(ChromeDriver chromeDriver) {

    WebElement openSettings = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Einstellungen')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, openSettings);

    WebElement immediatleyPublish = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Sofort veröffentlichen?')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, immediatleyPublish);
    chromeDriver.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    WebElement publish = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Veröffentlichen')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, publish);
  }

  private void addStatusPost(ChromeDriver chromeDriver) {
    WebElement addFbText = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Hier klicken um Text hinzuzufügen...')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, addFbText);

    WebElement createContentText = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='notranslate public-DraftEditor-content']")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, createContentText);
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    createContentText.sendKeys(POSTTEXT);
  }

  private void addImagePost(ChromeDriver chromeDriver) {
    WebElement addFbImage = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Bild Post')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, addFbImage);

    WebElement openMediaLib = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Medienbibliothek')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, openMediaLib);

    WebElement addTestImage = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[1]//div[2]//div[1]//li[1]//div[1]//div[2]//div[2]//button[1]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, addTestImage);

    WebElement clickSettings = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Einstellungen')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, clickSettings);

  }

  private void addVideoPost(ChromeDriver chromeDriver) {
    WebElement addFbVideo = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Video Post')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, addFbVideo);

    WebElement openMediaLib = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Medienbibliothek')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, openMediaLib);

    WebElement addTestVideo = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='jss7 jss1'][@title='Zum Inhalt hinzufügen']")));
    chromeDriver.executeScript(CLICKARGUMENT, addTestVideo);

    WebElement clickSettings = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Einstellungen')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, clickSettings);
  }

  private void setFbChannel(ChromeDriver chromeDriver) {
    WebElement selectChannel = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='select-channelIds']")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, selectChannel);

    WebElement selectChannelDirico = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Dirico_Testprojekt')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, selectChannelDirico);

    chromeDriver.findElement(By.xpath("//body[@id='body']")).click();
  }

  private ChromeOptions setChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    options.addArguments("disable-infobars");
    options.addArguments("--disable-notifications");
    options.addArguments("--lang=de_DE");
    return options;
  }

  private boolean isPublished(ChromeDriver driver) {
    WebElement publishedTrue = (new WebDriverWait(driver, 30))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Veröffentlicht')]")));
    return publishedTrue.isDisplayed();
  }
}