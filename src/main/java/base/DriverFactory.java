package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    WebDriver driver;
    public WebDriver getDriver(String browser){
        switch (browser) {
            case ("chrome"):
                System.setProperty("webdriver.chrome.driver", "src\\drivers\\windows\\chromedriver.exe");
                driver = new ChromeDriver();
                System.out.println("CH Driver is launched");
                break;
            case ("firefox"):
                System.setProperty("webdriver.gecko.driver", "src\\drivers\\windows\\geckodriver.exe");
                driver = new FirefoxDriver();
                System.out.println("FF Driver is launched");
                break;
            default:
                System.out.println("Can not start driver" + browser);
                System.setProperty("webdriver.chrome.driver", "src\\drivers\\windows\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }
}
