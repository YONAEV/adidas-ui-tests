package test.java.adidas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.adidas.utilities.GenericUtilities;

public class LoginPage {

    private WebDriver driver;
    private static GenericUtilities genericUtilities;

    @FindBy(id="dwfrm_login_username")
    public WebElement emailField;

    @FindBy(xpath = "//a[@data-title='Forgotten Your Password?']")
    public WebElement forgottenPasswordLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities = new GenericUtilities( driver );
        PageFactory.initElements( driver, this );
    }

    public ForgottenPasswordPage clickForgottenPasswordLink(){
        genericUtilities.waitForElementPresent( forgottenPasswordLink );
        forgottenPasswordLink.click();
        ForgottenPasswordPage forgottenPasswordPage=new ForgottenPasswordPage( driver );
        genericUtilities.waitForElementPresent( forgottenPasswordPage.emailField );
        return forgottenPasswordPage;
    }
}
