import database.DataBaseManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject.ContributionsPage;
import pageobject.HomePage;


import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ContributionsPageTest {
    WebDriver driver;
    HomePage homePage;
    ContributionsPage contributionsPage;
    DataBaseManager dataBaseManager;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\IdeaProjects\\rencredit\\src\\main\\resources\\chromedriver.exe");

        HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        dataBaseManager = DataBaseManager.getInstance();
        dataBaseManager.getConnection();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void test1() {
        try {
            //1) Открыть сайт rencredit.ru

            dataBaseManager.writeStartTime(new Date(), "Открыть сайт rencredit.ru");
            homePage.open();
            dataBaseManager.writeEndTime(new Date(), "Открыть сайт rencredit.ru");

            //2) Перейти на страницу Вклад;

            dataBaseManager.writeStartTime(new Date(), "Перейти на страницу Вклад");
            contributionsPage = homePage.switchToContributionPage();
            dataBaseManager.writeEndTime(new Date(), "Перейти на страницу Вклад");

            //3) Выбрать чекбокс "В отделении банка";

            dataBaseManager.writeStartTime(new Date(), "Выбрать чекбокс В отделении банка");
            contributionsPage.selectBankBranchCheckBox();
            dataBaseManager.writeEndTime(new Date(), "Выбрать чекбокс В отделении банка");

            //4) Ввести сумму вклада

            dataBaseManager.writeStartTime(new Date(), "Ввести сумму вклада");
            contributionsPage.enterAmount("123112");
            dataBaseManager.writeEndTime(new Date(), "Ввести сумму вклада");

            //5) Передвинуть ползунок На срок;

            dataBaseManager.writeStartTime(new Date(), "Передвинуть ползунок На срок");
            contributionsPage.dragPeriodSlide(ContributionsPage.MonthPeriod.NINE_MONTHS);
            dataBaseManager.writeEndTime(new Date(), "Передвинуть ползунок На срок");

            //6) Выгрузить Печатную Форму Общие условия по вкладам;

            dataBaseManager.writeStartTime(new Date(), " Выгрузить Печатную Форму ");
            contributionsPage.downloadFile();
            dataBaseManager.writeEndTime(new Date(), " Выгрузить Печатную Форму");
        } finally {
            dataBaseManager.closeConnection();
        }
    }
}
