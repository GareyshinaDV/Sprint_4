import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Test;
import pageobject.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static pageobject.UrlConstants.MAIN_PAGE_URL;

@RunWith(Parameterized.class)
public class TestForFAQ {
    private int number; // Поле класса для номера вопроса
    private String answer; // Поле класса для ответа
    private WebDriver driver;

    // Конструктов класса
    public TestForFAQ(int number, String answer){
        this.number = number;
        this.answer = answer;
    }
    // Параметризация тестовых данных
    @Parameterized.Parameters
    public static Object[][] getDataOrder() {
        return new Object[][]{ // Номер вопроса, правильный ответ
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

        @Before
        public void runDriver() {
            // Создание драйвера для браузера Chrome
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            // Переход на страницу тестового приложения заказа Самоката
            driver.get(MAIN_PAGE_URL);
        }

        @Test  // Тест соответствий ответов на вопросы
        public void answerForQuestionsTest() {
        MainPage objMainPage = new MainPage(driver); // /Создание объекта класса главной страницы
        objMainPage.clickOnAccordionPanelByNumberOfQuestion(number); // Клик по плашке с вопросом
        String textOnPage = objMainPage.getAnswerOfQuestionsByNumber(number); // Получение текста ответа после клика по плашке с вопросом
        assertThat("Текст на странице не соответствует ожидаемому", textOnPage, equalTo(answer)); // Сравнение текста, полученного на странице, с ожидаемым
        }

    @After
    public void teardown() {
        //Закрытие браузер
        driver.quit();
    }
    }






