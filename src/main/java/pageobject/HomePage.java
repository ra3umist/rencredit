package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class HomePage {
    private WebDriver driver;
    private static final String URL = "https://rencredit.ru/";

    @FindBy(xpath = "//span[text()='Оформить карту']/..")
    private WebElement cards;

    @FindBy(xpath = "//a[text()='Вклады']")
    private WebElement contibutions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public CardPage switchToCardPage() {
        cards.click();
        return new CardPage(driver);
    }

    public ContributionsPage switchToContributionPage() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        contibutions.click();
        return new ContributionsPage(driver);
    }


}
