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
    public void testH2TagText_WhenSearchingCityCountry () throws InterruptedException {
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


//        Thread.sleep(2000);




        driver.quit();
//                driver.close();
    }

}
