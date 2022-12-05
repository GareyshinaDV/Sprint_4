package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class OrderPage { // POM для страницы заказа

    private WebDriver driver;  // Добавила поле driver

    private By nameField = By.xpath("//input[@placeholder = '* Имя']"); // Локатор поля ввода имени
    private By familyField = By.xpath("//input[@placeholder = '* Фамилия']"); // Локатор поля ввода фамилии
    private By addressField = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']"); // Локатор поля адреса доставки самоката
    private By metroField =  By.xpath("//input[@placeholder = '* Станция метро']"); //Локатор поля ввода наименования "Станция метро"
    private By metroInput = By.className("select-search__select"); // Локатор поля ввода станции метро
    private By phoneField =  By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']"); // Локатор поля ввода телефона
    private By orderDateField = By.xpath("//input[@placeholder = '* Когда привезти самокат']"); // Локатор даты доставки
    private By rentTermsField = By.className("Dropdown-root"); // Локатор поля срока доставки самоката
    private By commentsField = By.xpath("//input[@placeholder = 'Комментарий для курьера']"); // Локатор поля ввода комментария
    private By nextStepButton = By.xpath("//button[@class ='Button_Button__ra12g Button_Middle__1CSJM']"); // Локатор для кнопки с текстами "Далее" / "Заказать" / "Да"
    private By placeOrderPopup = By.xpath("//div[(@class = 'Order_ModalHeader__3FDaJ')]"); // Локатор модального окна
    private By successPlaceOrderPopup = By.xpath("//div[(@class = 'Order_ModalHeader__3FDaJ') and (text()='Заказ оформлен')]"); // Локатор текста "Заказ оформлен"

    public OrderPage(WebDriver driver)  // Конструктор класса
    {
        this.driver = driver;
    }
    // Метод заполнения формы оформления заказа
    public void fillInOrderingForm(String name, String family, String address, String metro, String phone, String orderDate, String timeTerms, String color, String comments ) {
        driver.findElement(nameField).sendKeys(name); // Ввод имени
        driver.findElement(familyField).sendKeys(family); // Ввод фамилии
        driver.findElement(addressField).sendKeys(address); // Ввод адреса доставки
        driver.findElement(metroField).sendKeys(metro); // Ввод станции метро
        driver.findElement(metroInput).click(); // Выбор станции метро
        driver.findElement(phoneField).sendKeys(phone); // Ввод телефона
        driver.findElement(nextStepButton).click(); // Клик по кнопке продолжения оформления заказа
        driver.findElement(orderDateField).sendKeys(orderDate); // Ввод даты доставки самоката
        driver.findElement(rentTermsField).click(); // Клик по полю срока аренды
        driver.findElement(By.xpath("//div[contains(text(), '" + timeTerms + "')]")).click(); // Выбор срока аренды
        driver.findElement(By.id(color)).click(); //Введи цвет самоката
        driver.findElement(commentsField).sendKeys(comments); // Ввод текста комментария
        driver.findElement(nextStepButton).click(); // Клик по кнопке продолжения оформления заказа
    }

    // Проверка доступности модального окна завершения оформления заказа
    public void orderConfirmationPopupIsEnabled(){
        WebElement element = driver.findElement(placeOrderPopup); // Нахождение модального окна
        Assert.assertTrue(driver.findElement(placeOrderPopup).isEnabled()); // Проверка доступности модального окна
    }

    // Метод нажатия на кнопку перехода далее по оформлению заказа
    public void clickOnNextStepButton(){
        WebElement element = driver.findElement(nextStepButton); // Поиск перехода кнопки продолжения оформления заказа
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element); // Скролл до элемента
        Assert.assertTrue(driver.findElement(nextStepButton).isEnabled()); // Проверка доступности кнопки
        driver.findElement(nextStepButton).click(); //Клик по кнопке
    }

    // Проверка наличия текста "Заказ оформлен" в модальном окне в случае успешного оформления заказа
    public void successPlacedOrder(){
        WebElement element = driver.findElement(successPlaceOrderPopup); // Нахождение модального окна с "текстом Заказ оформлен"
        Assert.assertTrue(driver.findElement(successPlaceOrderPopup).isEnabled()); // Проверка доступности модального окна
    }
}
