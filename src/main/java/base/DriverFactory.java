package base;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class DriverFactory {
    WebDriver driver;
    String systemOS = System.getProperty("os.name").toLowerCase();
    String windowsDrivers = "src" + File.separator + "drivers" + File.separator + "windows" + File.separator;
    String windowsMac = "src" + File.separator + "drivers" + File.separator + "mac" + File.separator;
    String windowsLinux = "src" + File.separator + "drivers" + File.separator + "linux" + File.separator;


    public WebDriver getDriver(String browser) {
        switch (browser) {
            case ("chrome"):

                if (systemOS.contains("win"))  {System.setProperty("webdriver.chrome.driver", windowsDrivers + "chromedriver.exe");}
                else if(systemOS.contains("mac")) {System.setProperty("webdriver.chrome.driver", windowsMac + "chromedriver");}
                else if (systemOS.contains("nix")) {System.setProperty("webdriver.chrome.driver", windowsLinux + "chromedriver");}

                driver = new ChromeDriver();
//                System.out.println("CH Driver is launched");
                break;

            case ("firefox"):

                if (systemOS.contains("win"))  {System.setProperty("webdriver.gecko.driver", windowsDrivers + "geckodriver.exe");}
                else if(systemOS.contains("mac")) {System.setProperty("webdriver.gecko.driver", windowsMac + "geckodriver");}
                else if(systemOS.contains("nix")) {System.setProperty("webdriver.gecko.driver", windowsLinux + "geckodriver");}

                driver = new FirefoxDriver();
//                System.out.println("FF Driver is launched");
                break;

            default:
                System.out.println("Can not start driver" + browser);

                if (systemOS.contains("win"))  {System.setProperty("webdriver.chrome.driver", windowsDrivers + "chromedriver.exe");}
                else if(systemOS.contains("mac")) {System.setProperty("webdriver.chrome.driver", windowsMac + "chromedriver");}
                else if(systemOS.contains("nix")) {System.setProperty("webdriver.chrome.driver", windowsLinux + "chromedriver");}

                driver = new ChromeDriver();
//                System.out.println(System.getProperty("os.name"));
                break;
        }
        return driver;
    }
}
