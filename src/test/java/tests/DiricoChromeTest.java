package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

class DiricoChromeTest {

  private static final String CLICKARGUMENT = "arguments[0].click();";
  private static String POSTTEXT = "Test-Beitrag";

  private static void deletePost(ChromeDriver chromeDriverFb) {
    List<WebElement> deleteResultMenu = (new WebDriverWait(chromeDriverFb, 180))
      .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@data-testid,'post_chevron_button')]")));
    for (int i = 0; i < deleteResultMenu.size(); i++) {
      chromeDriverFb.executeScript(CLICKARGUMENT, deleteResultMenu.get(i));

      WebElement delete = (new WebDriverWait(chromeDriverFb, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Von der Seite entfernen')]")));
      chromeDriverFb.executeScript(CLICKARGUMENT, delete);

      WebElement deleteFinally = (new WebDriverWait(chromeDriverFb, 20))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='ok']")));
      chromeDriverFb.executeScript(CLICKARGUMENT, deleteFinally);
      System.out.println("Post gelöscht!");
    }
  }

  private static void publishContent(ChromeDriver chromeDriver) {
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

  private static void publishMediaContent(ChromeDriver chromeDriver) {

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

  private static void addStatusPost(ChromeDriver chromeDriver) {
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

  private static void addImagePost(ChromeDriver chromeDriver) {
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

  private static void addVideoPost(ChromeDriver chromeDriver) {
    WebElement addFbVideo = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Video Post')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, addFbVideo);

    WebElement openMediaLib = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Medienbibliothek')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, openMediaLib);

//    WebElement setFilter = (new WebDriverWait(chromeDriver, 10))
//      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='jss499']//div[3]//button[1]")));
//    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//    chromeDriver.executeScript(CLICKARGUMENT, setFilter);

    WebElement addTestVideo = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='jss7 jss1'][@title='Zum Inhalt hinzufügen']")));
    chromeDriver.executeScript(CLICKARGUMENT, addTestVideo);

    WebElement clickSettings = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Einstellungen')]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, clickSettings);
  }

  private static void setFbChannel(ChromeDriver chromeDriver) {
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

  private static void createNewFbContent(ChromeDriver chromeDriver) {
    WebElement contentTile = (new WebDriverWait(chromeDriver, 15))
      .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='content/all']")));
    chromeDriver.executeScript(CLICKARGUMENT, contentTile);

    WebElement newTask = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='md-fab md-mini md-warn md-button md-default-theme md-ink-ripple']")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, newTask);

    WebElement facebook = (new WebDriverWait(chromeDriver, 10))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//md-card[2]//md-card-content[1]")));
    chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    chromeDriver.executeScript(CLICKARGUMENT, facebook);
  }

  private static ChromeOptions setChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    options.addArguments("disable-infobars");
    options.addArguments("--disable-notifications");
    return options;
  }

  private static boolean isPublished(ChromeDriver driver) {
    WebElement publishedTrue = (new WebDriverWait(driver, 15))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Veröffentlicht')]")));

    return publishedTrue.isDisplayed();
  }

  @Test
  void main() {
    boolean testResult = false;
//    System.setProperty("webdriver.chrome.driver", "C:\\Users\\csc\\Dropbox\\247Grad\\Selenium-Testing-Board\\chromedriver.exe");
    try {
      System.setProperty("webdriver.chrome.driver", new File("./src/main/java/resources/chromedriver.exe").getCanonicalPath());
    } catch (IOException e) {
      e.printStackTrace();
    }

    ChromeOptions options = setChromeOptions();
    ChromeDriver chromeDriver = new ChromeDriver(options);
    chromeDriver.get("https://www.dirico.io/");

//    try {
      WebElement login = (new WebDriverWait(chromeDriver, 15))
        .until(ExpectedConditions.presenceOfElementLocated(By.linkText("LOGIN")));
      login.click();

      WebElement userName = (new WebDriverWait(chromeDriver, 15))
        .until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
      userName.click();
      userName.sendKeys("christian.schmtz@outlook.de");

      WebElement password = (new WebDriverWait(chromeDriver, 15))
        .until(ExpectedConditions.presenceOfElementLocated(By.name("Password")));
      password.click();
      password.sendKeys("Il@yda1993");

      WebElement loginBtn = (new WebDriverWait(chromeDriver, 15))
        .until(ExpectedConditions.presenceOfElementLocated(By.name("button")));
      loginBtn.click();

//    WebElement popUp = (new WebDriverWait(chromeDriver, 10))
//      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Ok']")));
//    popUp.click();

      //Status
      createNewFbContent(chromeDriver);
      setFbChannel(chromeDriver);
      addStatusPost(chromeDriver);
      publishContent(chromeDriver);

      //Image
      if (isPublished(chromeDriver)) {
        createNewFbContent(chromeDriver);
        setFbChannel(chromeDriver);
        addImagePost(chromeDriver);
        publishMediaContent(chromeDriver);
      }

      //Video
      if (isPublished(chromeDriver)) {
        createNewFbContent(chromeDriver);
        setFbChannel(chromeDriver);
        addVideoPost(chromeDriver);
        publishMediaContent(chromeDriver);
      }
//    //----------------------------------------------------------------------------------------------------------------
//    //Facebook checken
//
      ChromeDriver chromeDriverFb = new ChromeDriver(options);
      chromeDriverFb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      chromeDriverFb.get("https://www.facebook.de/");

      WebElement usernameFb = chromeDriverFb.findElement(By.xpath("//input[@id='email']"));
      usernameFb.click();
      usernameFb.sendKeys("christian.schmtz@outlook.de");

      chromeDriverFb.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);

      WebElement passwordFb = chromeDriverFb.findElement(By.xpath("//input[@id='pass']"));
      passwordFb.click();
      passwordFb.sendKeys("Il@yda1993");
      passwordFb.sendKeys(Keys.RETURN);

      WebElement projectDirico = (new WebDriverWait(chromeDriverFb, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Dirico_Testprojekt')]")));
      chromeDriverFb.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      chromeDriverFb.executeScript(CLICKARGUMENT, projectDirico);

      WebElement posts = (new WebDriverWait(chromeDriverFb, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='_2yav'][contains(text(),'Beiträge')]")));
      chromeDriverFb.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      chromeDriverFb.executeScript(CLICKARGUMENT, posts);

      WebElement result = (new WebDriverWait(chromeDriverFb, 15))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + POSTTEXT + "')]")));

////-----------------------Löschen---------------------------------------------------------------------------

//    deletePost(chromeDriverFb);

      if (result.getText().equals(
        POSTTEXT
      )) {
        testResult = true;
        assert testResult;
      }

//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    chromeDriver.close();
//    chromeDriverFb.close();
  }
}