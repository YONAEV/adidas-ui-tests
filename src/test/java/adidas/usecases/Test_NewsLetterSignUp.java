package test.java.adidas.usecases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.adidas.base.TestBase;
import test.java.adidas.config.Config;
import test.java.adidas.pages.LandingPage;
import test.java.adidas.pages.NewsletterSubscribePage;
import test.java.adidas.utilities.GenericUtilities;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/*
1. Open adidas.fi
2. Scroll down to the email address field
3. Enter a random email address and click submit button
4. Validate that the page has email address pre populated and it is the same as it was entered in step 3
5. Click Sign Up button
6. Validate that confirmation message section shows up
 */

public class Test_NewsLetterSignUp extends TestBase {

    private WebDriver driver;
    private NewsletterSubscribePage newsletterSubscribePage;

    @Test(dataProvider = "testableBrowsers")
    public void testNewsLetterSignUp(String browser, String version, String platform, Method method) throws IOException, InterruptedException {

        driver = setDriver( browser, version, platform, method );

        GenericUtilities genericUtilities = new GenericUtilities( driver );
        String email=genericUtilities.generateRandomEmail();
        newsletterSubscribePage = LandingPage.get( driver, Config.appURL )
                .checkModalAndClose( )
                .enterNewsletterEmail( email )
                .clickSubmitButtonOnEmailExpectingNewsletterSubscriptionPage();
        String actualEmail=newsletterSubscribePage.getEmailValue();

        assertThat("Email saved on newsletter subscribe page was not the same that was sent on landing page",
                actualEmail,
                equalTo(email));
        newsletterSubscribePage.clickSignupButton();
        assertThat( "Confirmation message was not displayed on clicking the Sign Up button",
                genericUtilities.isElementPresent( newsletterSubscribePage.confirmationMessage,5 ), is(true) );
    }

}
