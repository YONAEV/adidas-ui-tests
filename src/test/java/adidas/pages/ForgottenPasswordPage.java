package test.java.adidas.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.adidas.utilities.GenericUtilities;

public class ForgottenPasswordPage {

    private WebDriver driver;
    private static GenericUtilities genericUtilities;

    @FindBy(id="dwfrm_requestpassword_email")
    public WebElement emailField;

    @FindBy(xpath = "//*[@class='recaptcha-checkbox-checkmark']")
    private WebElement captchaCheckbox;

    @FindBy(xpath="//span[contains(@class,'recaptcha-checkbox-checked')]")
    private WebElement captchaChecked;

    @FindBy(name = "dwfrm_requestpassword_send")
    private WebElement resetPasswordButton;

    @FindBy(className = "alert alert-success ")
    public static WebElement successMessage;

    private static String frameXpath="//iframe[contains(@src,'https://www.google.com/recaptcha')]";

    public ForgottenPasswordPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities = new GenericUtilities( driver );
        PageFactory.initElements( driver, this );
    }

    public ForgottenPasswordPage enterEmail(String email){
        genericUtilities.waitForElementPresent( emailField );
        emailField.sendKeys( email );
        return this;
    }

    public ForgottenPasswordPage checkCaptchaCheckbox(){
        driver.switchTo().frame( driver.findElement( By.xpath( frameXpath ) ) );
        genericUtilities.waitForElementPresent( captchaCheckbox);

        captchaCheckbox.click();
        genericUtilities.waitForElementPresent( captchaChecked );
        return this;
    }

    public ForgottenPasswordPage clickResetPasswordButton(){
        genericUtilities.waitForElementPresent( resetPasswordButton );
        resetPasswordButton.click();
        return this;
    }
}
