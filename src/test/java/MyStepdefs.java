import com.google.common.base.Verify;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.BrowserInit;

public class MyStepdefs {

    private Logger log = LoggerFactory.getLogger(MyStepdefs.class);
    private BrowserInit browserInit;
    private Factory factory;

    public MyStepdefs(Factory factory,BrowserInit browserInit){
        this.browserInit=browserInit;
        this.factory=factory;
    }

    @Given("the user navigates to {string}")
    public void the_user_navigates_to(String url) {
       browserInit.navigateToUrl(url);
    }

    @When("the user selects the state as (.*)")
    public void the_user_selects_the_state_as_Georgia(String state) {
       factory.getHomePage().setChooseState(state);
    }

    @When("profession as (.*)")
    public void profession_as_Website_design(String profession) {
        factory.getHomePage().setCob(profession.trim());
    }

    @When("starts a quote")
    public void starts_a_quote() {
       factory.getHomePage().setGetAQuote();
    }

    @Then("recommended products has to be displayed")
    public void recommended_products_has_to_be_displayed() {
        Verify.verify(browserInit.driver.findElement(By.xpath("//*[text()='Professional Liability']")).isDisplayed(),"Recommedned Products Page is not displayed");
    }

}
