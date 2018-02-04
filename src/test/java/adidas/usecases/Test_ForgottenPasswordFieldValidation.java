package test.java.adidas.usecases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.adidas.base.TestBase;
import test.java.adidas.config.Config;
import test.java.adidas.pages.ForgottenPasswordPage;
import test.java.adidas.pages.LandingPage;
import test.java.adidas.pages.LoginPage;
import test.java.adidas.utilities.GenericUtilities;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/*
1. Open adidas.fi
2. Click login link
3. Click Forgotten password link
4. Click reset button without entering email or submitting captcha
5. Validate that correct error messages show up
 */

public class Test_ForgottenPasswordFieldValidation extends TestBase {
    private WebDriver driver;
    private LoginPage loginPage;
    private ForgottenPasswordPage forgottenPasswordPage;
    private final String EMAIL_MISSING_ERROR = "Please enter a valid e-mail address";
    private final String CAPTCHA_MISSING_ERROR = "Please verify that you are not a robot";

    @Test(dataProvider = "testableBrowsers")
    public void testNewsLetterSignUp(String browser, String version, String platform, Method method) throws IOException, InterruptedException {

        driver = setDriver( browser, version, platform, method );

        GenericUtilities genericUtilities = new GenericUtilities( driver );

        loginPage = LandingPage.get( driver, Config.appURL )
                .checkModalAndClose()
                .clickLoginLink();
        forgottenPasswordPage = loginPage.clickForgottenPasswordLink();
        forgottenPasswordPage
                .clickResetPasswordButton();
        assertThat( "Error message about missing email was not displayed when trying to submit the forgotten " +
                        "password form without email",
                genericUtilities.isElementPresent( By.xpath( String.format( genericUtilities.textLocator, "div", EMAIL_MISSING_ERROR )
                ), 5 ), is( true ) );

        assertThat( "Error message about missing captcha was not displayed when trying to submit the forgotten " +
                        "password form without captcha",
                genericUtilities.isElementPresent( By.xpath( String.format( genericUtilities.textLocator, "div", CAPTCHA_MISSING_ERROR )
                ), 5 ), is( true ) );
    }
}
