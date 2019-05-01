package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.BrowserInit;


public class HomePage {
    @FindBy(name = "state")
    WebElement chooseState;
    @FindBy(xpath="//select[@name='cob']/option")
    WebElement cob;
    @FindBy(linkText = "Get a Quote")
    WebElement getAQuote;

    private BrowserInit browserInit;

    public HomePage(BrowserInit browserInit){
        this.browserInit=browserInit;
        PageFactory.initElements(this.browserInit.driver,this);
    }
    public WebElement getChooseState() {
        return chooseState;
    }

    public void setChooseState(String chooseState) {
        selectFromDropDown(chooseState,1);
    }

    public WebElement getCob() {
        return cob;
    }

    public void setCob(String cob) {
       selectFromDropDown(cob,2);
    }

    public WebElement getGetAQuote() {
        return getAQuote;
    }

    public void setGetAQuote() {
        this.getAQuote.click();
    }

    private void waitFor(int seconds){
        try {
            Thread.sleep(1000*seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void selectFromDropDown(String select,int index){
        Actions actions=new Actions(this.browserInit.driver);
        waitFor(4);
        actions.moveToElement(this.browserInit.driver.findElement(By.xpath("(//span[@class='select2-selection__arrow'])["+index+"]")))
                .click().perform();
        waitFor(2);
        actions.sendKeys(this.browserInit.driver.findElement(By.xpath("//input[@class='select2-search__field']")),select).perform();
        actions.click(this.browserInit.driver.findElement(By.xpath("//li[normalize-space(text())='" + select +"']"))).perform();
    }
}
