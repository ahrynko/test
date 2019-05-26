import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// JUnit -пишем тесты
public class SignUpTest {
  private WebDriver driver;
  private SignUpPage page;

  @Before
  public void setUp(){
    System.setProperty("webdriver.chrome.driver", "D:\\My Java Projects\\drivers\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("https://www.spotify.com/us/signup");
  }

  @Test
  public void typeInvalidYear(){
    page = new SignUpPage(driver);
    page.setMonth("December")
            .typeDay("20")
            .typeYear("85")
            .setShare(true);
    Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
    Assert.assertFalse(page.isErrorVisible("Please enter a valid day of the month."));
  }

  @Test
  public void typeInvalidEmail(){
    page = new SignUpPage(driver);
    page.typeEmail("test@mail.test")
            .typeConfirmEmail("wrong@mail.test")
            .typeName("Testname")
            .clickSignUpButton();
    Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
  }

  @Test
  public void signUpWithEmptyPassword(){
    page = new SignUpPage(driver);
    page.typeEmail("wrongs@mail.test")
            .typeConfirmEmail("wrongs@mail.test")
            .typeName("Testname")
            .clickSignUpButton();
    Assert.assertTrue(page.isErrorVisible("Please choose a password."));

  }

  @Test
  public void typeInvalidValues(){
    page = new SignUpPage(driver);
    page.typeEmail("testmaul")
            .typeConfirmEmail("wrongs@mail.test")
            .typePassword("qwer!234EWR")
            .typeName("dsfdfgg")
            .setSex("Male")
            .setShare(false)
            .clickSignUpButton();
    Assert.assertEquals(6,page.getErrors().size());
    Assert.assertEquals("Please enter your birth month.",page.getErrorByNumber(3));
  }


  @After
  public void tearDown(){
    driver.quit();
  }

}
