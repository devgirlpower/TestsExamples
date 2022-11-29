package testese2e;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestE2ESeleniumDesespero {
	  @Test
	    public void HomeAcessTest() {
	        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
	        WebDriver browser = new ChromeDriver();
	        browser.navigate().to("http://localhost:8080/clinic");
	        browser.quit();
	    }
}
