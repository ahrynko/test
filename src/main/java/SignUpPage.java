      //WARNING!!! The default import statement introduced is --  import com.sun.tools.javac.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class SignUpPage {
  private WebDriver driver;

  public SignUpPage(WebDriver driver) {
    this.driver = driver;
  }
  // Page Object -нахождение элементов
  private By emailField = By.cssSelector("input#register-email");
  private By confirmEmailField = By.cssSelector("input#register-confirm-email");
  private By passwordField = By.cssSelector("input#register-password");
  private By nameField = By.cssSelector("input#register-displayname");
  private By mounthDropDown = By.cssSelector("select#register-dob-month");
  private String mounthDropDownOption = "//select[@id='register-dob-month']/option[text()='%s']";
  private By dayField = By.cssSelector("input#register-dob-day");
  private By yearField = By.cssSelector("input#register-dob-year");
  private String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input";
  private By shareCheckbox = By.cssSelector("input#register-thirdparty");
  private By registerButton = By.cssSelector("a#register-button-email-submit");
  private By errorLabel = xpath("//label[@class = 'has-error' and string-length(text())>0]");
  private String errorLabelText = "//label[@class = \"has-error\" and text()=\"%s\"]";  //для подстановки сообщ с кавычками (\")

  // Page Object -написание методов
  public SignUpPage typeEmail(String email) {    // метод для заполнения email
    driver.findElement(emailField).sendKeys(email);
    return this;
  }

  public SignUpPage typeConfirmEmail(String email) {    // метод для заполнения email
    driver.findElement(confirmEmailField).sendKeys(email);
    return this;
  }

  public SignUpPage typePassword(String password) {    // метод для заполнения pass
    driver.findElement(passwordField).sendKeys(password);
    return this;
  }

  public SignUpPage typeName(String name) {    // метод для заполнения name
    driver.findElement(nameField).sendKeys(name);
    return this;
  }

  public SignUpPage setMonth(String month) {    // метод для open dropDown
    driver.findElement(mounthDropDown).click();
    new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(xpath(format(mounthDropDownOption, month)))).click();
    return this;
  }

  public SignUpPage typeDay(String day) {    // метод для заполнения day
    driver.findElement(dayField).sendKeys(day);
    return this;
  }

  public SignUpPage typeYear(String year) {    // метод для заполнения year
    driver.findElement(yearField).sendKeys(year);
    return this;
  }

  public SignUpPage setSex(String value) {    // метод для the choice RadioButton
    driver.findElement(xpath(format(sexRadioButton, value))).click();
    return this;
  }

  public SignUpPage setShare(boolean value) {    // метод для нажатия чекбокса
    WebElement checkbox = driver.findElement(shareCheckbox);
    if (!checkbox.isSelected() == value){
         checkbox.click();
    }
    return this;
  }

  public void clickSignUpButton(){              // метод для нажатия кнопки Sign Up
    driver.findElement(registerButton).click();
  }

  public java.util.List<WebElement> getErrors(){        // метод для поиска всех ошибок списка
    return driver.findElements(errorLabel);
  }

  public String getErrorByNumber(int number ){         // метод для получения ошибок по номеру ошибки
    return getErrors().get(number -1).getText();
  }

  public boolean isErrorVisible(String message ){      // метод для получения видимых ошибок на странице
    return driver.findElements(xpath(format(errorLabelText, message))).size()> 0
            && driver.findElements(xpath(format(errorLabelText, message))).get(0).isDisplayed();
  }

}