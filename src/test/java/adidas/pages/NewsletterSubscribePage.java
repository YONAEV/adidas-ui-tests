package test.java.adidas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.adidas.utilities.GenericUtilities;

public class NewsletterSubscribePage {

    private WebDriver driver;
    private GenericUtilities genericUtilities;

    //Page elements
    @FindBy(css = "#email-send-form #dwfrm_profile_customer_email")
    private WebElement emailField;

    @FindBy(id = "newslettersubmitbuttonwomen")
    private WebElement signUpButton;

    @FindBy(className = "confirm-msg")
    public static WebElement confirmationMessage;

    public NewsletterSubscribePage(WebDriver driver) {
        this.driver = driver;
        genericUtilities=new GenericUtilities( driver );
        PageFactory.initElements(driver, this);
    }

    public String getEmailValue(){
        genericUtilities.waitForElementPresent( emailField );
        return emailField.getAttribute( "value" );
    }

    //I am returning the news letter page here as clicking on the button shows a confirmation message but it is still
    // related to news letter page
    public NewsletterSubscribePage clickSignupButton(){
        genericUtilities.waitForElementPresent( signUpButton );
        signUpButton.click();
        return this;
    }
}
