import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.BrowserInit;

public class Hooks {
    private Logger log = LoggerFactory.getLogger(Hooks.class);
    private BrowserInit browserInit;

    public Hooks(BrowserInit browserInit){
        this.browserInit=browserInit;
    }

    @Before
    public void startUp(Scenario scenario){
        log.info("Running:"+scenario.getName());
        this.browserInit.openBrowser();
    }

    @After
    public void tearDown(Scenario scenario){
        log.info(scenario.getStatus()+"-"+scenario.getName());
        byte[] screenshot = ((TakesScreenshot) browserInit.driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
        this.browserInit.quitBrowser();
    }
}
