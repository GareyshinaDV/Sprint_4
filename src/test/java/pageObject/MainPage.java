package pageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {  // POM для главной страницы
    private WebDriver driver;  // Добавила поле driver
    private By orderButtonTop = By.className("Button_Button__ra12g"); // Локатор кнопки "Заказать" в хедере страницы
    private By orderButtonMiddle = By.xpath(".//button[@class ='Button_Button__ra12g Button_Middle__1CSJM']"); // Локатор кнопки "Заказать" под лентой действий с самокатом

    public MainPage(WebDriver driver)  // Конструктор класса
    {
        this.driver = driver;
    }
    public void clickOnButtonTop() {   // Метод клика на кнопку "Заказать" в хедере страницы
        WebElement element = driver.findElement(orderButtonTop); // Поиск кнопки "Заказать" в хедере страницы
        Assert.assertTrue(driver.findElement(orderButtonTop).isEnabled()); // Убедиться, что кнопка кликабельна
        driver.findElement(orderButtonTop).click(); //Клик по кнопке
    }

    public void clickOnButtonMiddle() {   // Метод клика на кнопку "Заказать" в средней части страницы
        WebElement element = driver.findElement(orderButtonMiddle); // Поиск кнопки "Заказать" в средней части страницы
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element); // Скролл до кнопки
        Assert.assertTrue(driver.findElement(orderButtonMiddle).isEnabled()); // Убедиться, что кнопка кликабельна
        driver.findElement(orderButtonMiddle).click(); //Клик по кнопке
    }

    public void clickOnAccordionPanelByNumberOfQuestion(int number) {
        WebElement element = driver.findElement(By.id("accordion__heading-" + number)); // Поиск плашки вопроса по его номеру
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element); // Скролл до плашки с вопросом
        Assert.assertTrue(driver.findElement(By.id("accordion__heading-" + number)).isEnabled()); // Проверка доступности клика по плашке вопроса
        driver.findElement(By.id("accordion__heading-" + number)).click(); // Клик по плашке с вопросом
    }

    public String getAnswerOfQuestionsByNumber(int number){
        WebElement element = driver.findElement(By.xpath("//div[@id = 'accordion__panel-"+number+"']/p")); // Поиск плашки вопроса по его номеру
        return element.getText(); // Вывод текста ответа
    }
}


