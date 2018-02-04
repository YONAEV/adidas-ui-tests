package test.java.adidas.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.adidas.utilities.GenericUtilities;

public class LandingPage {

    private WebDriver driver;
    private static GenericUtilities genericUtilities;

    //Page elements
    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitEmailButton;

    @FindBy(xpath = "//i[contains(@class,'icon-close')]")
    private WebElement modalCloseLink;

    @FindBy(xpath = "//input[@data-auto-id='searchinput']")
    private WebElement searchField;

    @FindBy(xpath = "//a[contains(@manual_cm_sp,'Sign in')]")
    private WebElement signInLink;


    public LandingPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities = new GenericUtilities( driver );
        PageFactory.initElements( driver, this );
    }

    public LandingPage enterNewsletterEmail(String email) {
        genericUtilities.waitForElementPresent( emailField );
        emailField.sendKeys( email );
        return this;
    }

    public NewsletterSubscribePage clickSubmitButtonOnEmailExpectingNewsletterSubscriptionPage() throws InterruptedException {
        genericUtilities.waitForElementPresent( submitEmailButton );
        genericUtilities.scrollInView( submitEmailButton );
        submitEmailButton.click();
        return new NewsletterSubscribePage( driver );
    }

    public static LandingPage get(WebDriver driver, String baseUrl) {
        driver.get( baseUrl );
        return new LandingPage( driver );
    }

    public LandingPage checkModalAndClose() {
        if (genericUtilities.isElementPresent( modalCloseLink, 2 )) {
            modalCloseLink.click();
        }
        return this;
    }

    public ProductGridPage searchForProductExpectingProductGrid(String searchQuery) {
        genericUtilities.waitForElementPresent( searchField );
        searchField.sendKeys( searchQuery );
        searchField.sendKeys( Keys.ENTER );
        return new ProductGridPage( driver );
    }

    public LoginPage clickLoginLink() {
        genericUtilities.waitForElementPresent( signInLink );
        signInLink.click();
        LoginPage loginPage = new LoginPage( driver );
        genericUtilities.waitForElementPresent( loginPage.emailField );
        return loginPage;
    }

}
