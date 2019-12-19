package acial.selenium.exercices;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Exercice2 {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  public class SameClassDataProvider 
  {
	  
  }
  @DataProvider(name = "salaries")
      public Object[][] dataProviderMethod() {
          return new Object[][] { { "jean", "ug", "767868" }, { "jacques", "tgfy", "8468434" }, {"hnwtnh", "whbwnn", "867534"} };
      }
  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:\\Formation\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test(priority=1)
  public void testLoging() throws Exception {
  
	  driver.get("http://www.universitedutest.com/OrangeHRM/");
    driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys("Admin");
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("Nantes$2020");
    driver.findElement(By.id("btnLogin")).click();
  }
  
  @Test(dataProvider = "salaries", priority=2)
  public void testAjouterEmploye(String prenom , String nom , String identifiant) throws Exception 
  {
	  driver.findElement(By.xpath("//li[2]/a/b")).click();
    driver.findElement(By.id("menu_pim_addEmployee")).click();
    driver.findElement(By.id("firstName")).sendKeys(prenom);
    driver.findElement(By.id("lastName")).sendKeys(nom);
    driver.findElement(By.id("employeeId")).clear();
    driver.findElement(By.id("employeeId")).sendKeys(identifiant);
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.xpath("//ul/li[2]/input")).click();
    driver.findElement(By.id("btnSave")).click();
  }
 
  @Test(priority=5)
  public void testLogin() throws Exception {
	  driver.get("http://www.universitedutest.com/OrangeHRM/");
    driver.findElement(By.id("welcome")).click();
    driver.findElement(By.linkText("Logout")).click();
  }
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
