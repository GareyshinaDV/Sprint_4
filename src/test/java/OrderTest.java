import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.OrderPage;

import static pageobject.UrlConstants.MAIN_PAGE_URL;

@RunWith(Parameterized.class)
public class OrderTest {

    // Поля класса
    private final String name;
    private final String family;
    private final String address;
    private final String metro;
    private final String phone;
    private final String orderDate;
    private final String timeTerms;
    private final String color;
    private final String comments;

    private WebDriver driver;

    // Конструктор класса
    public OrderTest(String name, String family, String address, String metro, String phone, String orderDate, String timeTerms, String color, String comments){
        this.name = name;
        this.family = family;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.orderDate = orderDate;
        this.timeTerms = timeTerms;
        this.color = color;
        this.comments = comments;
    }

    // Параметризация тестовых данных
    @Parameterized.Parameters
    public static Object[][] getDataOrder() {
        return new Object[][]{ // Варианты цветов самоката серый / черный, разная длительность срока аренды
                {"Дарья", "Гарейшина", "Москва, улица Бибиревская, дом 3", "Бибирево", "+79778015997", "03.12.2022\n", "сутки", "black", "Если будет, то с розовыми цветами"},
                {"Игнат", "Свердлов", "Краснобогатырская, дом 2, строение 3", "Преображенская площадь", "89296400916", "01.01.2023\n", "двое суток", "grey", "Полный заряд батареи"},
                {"Айза", "Игнатьева", "Семеновская улица 7", "Речной вокзал", "+79778015998", "01.02.2023\n", "трое суток", "black", ""},
                {"Асстра", "Петрова", "Новая улица 99, 258", "Черкизово", "+79778015997", "28.02.2023\n", "четверо суток", "grey", "Комментарий новый"},
                {"Инга", "Страстная", "к.926 кв 148", "Черкизово", "+79778015997", "28.06.2023\n", "пятеро суток", "black", "Комментарий новый 888777"},
                {"Анна", "Новикова", "к.926", "Зябликово", "+79778015998", "05.12.2022\n", "шестеро суток", "grey", ""},
                {"Андрей", "Новиков", "театр Советов", "Марьина Роща", "+79778015997", "31.12.2022\n", "семеро суток", "black", "Мой второй номер +79296400988"},
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

    @Test  // Тест прохождения позитивного сцерания при заказе через кнопку "Заказать" с хедере главной страницы
    public void orderTestTopButton() {
        MainPage objMainPage = new MainPage(driver); //Создание объекта класса главной страницы
        objMainPage.clickOnButtonTop(); // Клик на кнопку "Заказать" в хедере страницы
        OrderPage objOrderPage = new OrderPage(driver); // Создание объекта классса страницы заказа самоката
        objOrderPage.fillInOrderingForm(name, family, address, metro, phone, orderDate, timeTerms, color, comments); // Заполнение полей формы заказа
        objOrderPage.orderConfirmationPopupIsEnabled(); // Проверка доступности модального окна завершения оформления заказа
        objOrderPage.clickOnNextStepButton(); // Клик на кнопку подтверждения оформления заказа. В chrome баг, кнопка некликабельна, следовательно на следующий шаг теста перейти не получится
        objOrderPage.successPlacedOrder(); // Проверка наличия текста "Заказ оформлен" в модальном окне в случае успешного оформления заказа.
    }

    @Test // Тест прохождения позитивного сценария при заказе через кнопку "Заказать" в средней части страницы
    public void orderTestMiddleButton() {
        MainPage objMainPage = new MainPage(driver); //Создание объекта класса главной страницы
        objMainPage.clickOnButtonMiddle(); // Клик на кнопку "Заказать" в средней части страницы
        OrderPage objOrderPage = new OrderPage(driver); // Создание объекта классса страницы заказа самоката
        objOrderPage.fillInOrderingForm(name, family, address, metro, phone, orderDate, timeTerms, color, comments); // Заполнение полей формы заказа
        objOrderPage.orderConfirmationPopupIsEnabled(); // Проверка доступности модального окна завершения оформления заказа
        objOrderPage.clickOnNextStepButton(); // // Клик на кнопку подтверждения оформления заказа. В chrome баг, кнопка некликабельна, следовательно на следующий шаг теста перейти не получится
        objOrderPage.successPlacedOrder(); // Проверка наличия текста "Заказ оформлен" в модальном окне в случае успешного оформления заказа
    }


    @After
    public void teardown() {
    //Закрытие браузер
    driver.quit();
    }
}







