package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CardPage {
    private WebDriver driver;
    private static final String URL = "https://rencredit.ru/app/card/cc_full";

    @FindBy(id = "t1")
    private WebElement lastNameField;

    @FindBy(id = "t2")
    private WebElement firstNameField;

    @FindBy(id = "t3")
    private WebElement middleNameField;

    @FindBy(xpath = "//body[@class='blank-page']/div[@class='page-content js-page-content']/div[@class='wrapper']/div[@id='section_1']/div[@class='order-form-block__content']/div[@class='order-form']/form[@class='js-validation-form js-step-form js-double-protect']/div[@class='order-form__step js-required-step js-step-form-item']/div[@class='form-row']/div[@class='form-check-row form-check-row_text_large']/label/div[1]")
    private WebElement noMiddleNameCheckBox;

    @FindBy(id = "t4")
    private WebElement phoneField;

    @FindBy(id = "t38")
    private WebElement emailField;

    @FindBy(id = "s2")
    private WebElement regionDropDownList;

    public CardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Общие данные']")));
    }

    public void enterLastName(String lastName) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String script = String.format("arguments[0].value='%s';", lastName);
        lastNameField.click();
        jse.executeScript(script, lastNameField);
    }

    public void enterFirstName(String firstName) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String script = String.format("arguments[0].value='%s';", firstName);
        firstNameField.click();
        jse.executeScript(script, firstNameField);
    }

    public void selectNoMiddleName() {
        String attClass = noMiddleNameCheckBox.getAttribute("class");
        if(!attClass.contains("checked")) noMiddleNameCheckBox.click();
    }

    public void enterPhone(String number) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String script = String.format("arguments[0].value='%s';", number);
        phoneField.click();
        jse.executeScript(script, phoneField);
    }

    public void enterEmail(String email) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String script = String.format("arguments[0].value='%s';", email);
        emailField.click();
        jse.executeScript(script, emailField);
    }

    public void selectRegion(String region) {
        Select select = new Select(regionDropDownList);
        select.selectByVisibleText(region);
    }
}
