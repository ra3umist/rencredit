package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ContributionsPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='calculator__check-row-field check-deposit']//div[@class='jq-checkbox calculator__check']")
    public WebElement atBankBranchCheckbox;

    @FindBy(xpath = "//input[@name='amount']")
    private WebElement amountField;

    @FindBy(xpath = "//a[text()='Общие условия по вкладам']")
    private WebElement pdfFile;

    //***********<Слайдер Периода>*************
    public enum MonthPeriod {
        THREE_MONTHS,
        SIX_MONTHS,
        NINE_MONTHS,
        TWELVE_MONTHS,
        FIFTEEN_MONTHS,
        EIGHTEEN_MONTHS
    }

    @FindBy(xpath = "//div[@data-property='period']//span[@class='ui-slider-handle ui-state-default ui-corner-all']")
    private WebElement periodSliderHandle;

    @FindBy(xpath = "//div[@data-property='period']//div[contains(@class, 'range-scale__item range-scale__item_0')]")
    private WebElement threeMonthsPeriodScale;

    @FindBy(xpath = "//div[@data-property='period']//div[contains(@class, 'range-scale__item range-scale__item_1')]")
    private WebElement sixMonthsPeriodScale;

    @FindBy(xpath = "//div[@data-property='period']//div[contains(@class, 'range-scale__item range-scale__item_2')]")
    private WebElement nineMonthsPeriodScale;

    @FindBy(xpath = "//div[@data-property='period']//div[contains(@class, 'range-scale__item range-scale__item_3')]")
    private WebElement twelveMonthsPeriodScale;

    @FindBy(xpath = "//div[@data-property='period']//div[contains(@class, 'range-scale__item range-scale__item_4')]")
    private WebElement fifteenMonthsPeriodScale;

    @FindBy(xpath = "//div[@data-property='period']//div[contains(@class, 'range-scale__item range-scale__item_5')]")
    private WebElement eighteenMonthsPeriodScale;
    //***********</Слайдер Периода>*************

    public ContributionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Рассчитайте доходность по вкладу']")));
    }

    public void selectBankBranchCheckBox() {
        if(!atBankBranchCheckbox.isSelected()) atBankBranchCheckbox.click();
    }

    public void enterAmount(String amount) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String script = String.format("arguments[0].value='%s';", amount);
        amountField.click();
        jse.executeScript(script, amountField);
    }

    public void dragPeriodSlide(MonthPeriod month) {
        switch (month) {
            case THREE_MONTHS:
                new Actions(driver).dragAndDrop(periodSliderHandle, threeMonthsPeriodScale).build().perform(); break;
            case SIX_MONTHS:
                new Actions(driver).dragAndDrop(periodSliderHandle, sixMonthsPeriodScale).build().perform(); break;
            case NINE_MONTHS:
                new Actions(driver).dragAndDrop(periodSliderHandle, nineMonthsPeriodScale).build().perform(); break;
            case TWELVE_MONTHS:
                new Actions(driver).dragAndDrop(periodSliderHandle, twelveMonthsPeriodScale).build().perform(); break;
            case FIFTEEN_MONTHS:
                new Actions(driver).dragAndDrop(periodSliderHandle, fifteenMonthsPeriodScale).build().perform(); break;
            case EIGHTEEN_MONTHS:
                new Actions(driver).dragAndDrop(periodSliderHandle, eighteenMonthsPeriodScale).build().perform(); break;
        }
    }

    public void downloadFile() {
        pdfFile.click();
    }



}
