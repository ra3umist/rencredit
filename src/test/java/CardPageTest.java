import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject.CardPage;
import pageobject.HomePage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CardPageTest {
    CardPage cardPage;
    HomePage homePage;
    WebDriver driver;


    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\IdeaProjects\\rencredit\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void test1() {
        //1) Открыть сайт rencredit.ru
        homePage.open();
        //2) Перейти на страницу Карта
        cardPage = homePage.switchToCardPage();
        //3) Ввести в поля Фамилия, Имя, Мобильный телефон, e-mail значения
        cardPage.enterFirstName("Владислав");
        cardPage.enterLastName("Полеха");
        cardPage.enterPhone("9003192001");
        cardPage.enterEmail("vladislav.polekha@gmail.com");
        //4) Выбрать чекбокс Нет отчества
        cardPage.selectNoMiddleName();
        //5) Выбрать значение из выпадающего списка Где вы желаете получить карту
        cardPage.selectRegion("Пензенская область");
    }
}
