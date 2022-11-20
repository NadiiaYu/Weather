import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NadiiaYuTest {

//    /TC_1_1  - Тест кейс:
//    //1. Открыть страницу https://openweathermap.org/
//    //2. Набрать в строке поиска город Paris
//    //3. Нажать пункт меню Search
//    //4. Из выпадающего списка выбрать Paris, FR
//    //5. Подтвердить, что заголовок изменился на "Paris, FR"


    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/nadyayurchenko/IdeaProjects/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);
        WebElement searchCityField = driver.findElement(
                new By.ByXPath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchbuton = driver.findElement(
                new By.ByXPath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchbuton.click();
        Thread.sleep(5000);

/*
        WebElement dropdownMenuEl = driver.findElement(
                new By.ByClassName("search-dropdown-menu"));
        WebElement parisFRLiEl =
                dropdownMenuEl.findElement(new By.ByXPath("//li/span[text() = 'Paris, FR']"));
        parisFRLiEl.click();
*/

        WebElement parisFRChoiceInDropDownMenu = driver.findElement(
                new By.ByXPath("//ul[@class = 'search-dropdown-menu']//li/span[contains(text(), 'Paris, FR')]")
        );
        parisFRChoiceInDropDownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и
//// что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    public void testGuideUrlHeader() {
        System.setProperty("webdriver.chrome.driver", "/Users/nadyayurchenko/IdeaProjects/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);
//        Thread.sleep(5000);

        WebElement guideElementInMenu = driver.findElement(
                By.xpath("//a[@href='/guide']")
        );
        guideElementInMenu.click();
        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        driver.quit();
    }


//    1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах


    @Test
    public void test2_IsTempInF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/nadyayurchenko/IdeaProjects/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTempSymbol = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

//        WebElement menuImperial = driver.findElement(
//                By.xpath("//div[@class='switch-container']/div[@class='option'][contains (text(), 'Imperial')]"));

        WebElement menuImperial2 = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[@class='option'][2]")
        );

//        System.err.println(menuImperial2.getText());
        menuImperial2.click();
        WebElement tempF = driver.findElement(
                By.xpath("//span[@class='heading']")
        );

        String tempInF = tempF.getText();
        String actualResult = tempInF.substring((tempInF.length() - 2));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }

//    TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the
// site to work. We also use non-essential cookies to help us improve our services".
// Any data collected is anonymised. You can allow all cookies or manage them individually.”
// 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”


    @Test

    public void test3() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/nadyayurchenko/IdeaProjects/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";


        driver.get(url);
        Thread.sleep(10000);

        WebElement cookiesTextEl = driver.findElement(
                By.className("stick-footer-panel__description"));
//                By.xpath("//div[@id = 'stick-footer-panel']//p[contains(text (), 'We use cookies which')]"));
        String actualResult1 = cookiesTextEl.getText();

        WebElement cookiesButton = driver.findElement(
                By.xpath("//button[@class = 'stick-footer-panel__link']"));
        String actualResult2 = cookiesButton.getText();

        WebElement cookiesLink = driver.findElement(
                By.xpath("//a[@class = 'stick-footer-panel__link']"));
        String actualResult3 = cookiesLink.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();

    }

//        TC_11_04
//        1.  Открыть базовую ссылку
//        2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test

    public void test4() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/nadyayurchenko/IdeaProjects/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";



    }
}
