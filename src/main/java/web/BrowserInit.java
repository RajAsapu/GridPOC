package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserInit {
    private static final Logger log = LoggerFactory.getLogger(BrowserInit.class);
    private long timeout = 30;
    public  WebDriver driver = null;

    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--incognito");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--ignore-certificate-errors");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setJavascriptEnabled(true);
        capabilities.setPlatform(Platform.ANY);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        try {
            driver = new RemoteWebDriver(new URL(System.getenv("HUB_URL")+"/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        log.info("Browser launched");
    }

    public void quitBrowser() {
        driver.quit();
        log.info("Browser is closed");
    }

    public void navigateToUrl(String url) {
        driver.manage().deleteAllCookies();
        driver.navigate().to(url);
        log.info("Navigated to " + url);
    }
}
