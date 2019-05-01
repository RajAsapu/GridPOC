import page_object.HomePage;

public class Factory {
    private HomePage homePage;

    public Factory(HomePage homePage){
        this.homePage=homePage;
    }
    public HomePage getHomePage() {
        return homePage;
    }
}
