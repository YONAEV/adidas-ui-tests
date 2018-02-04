package test.java.adidas.usecases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.adidas.base.TestBase;
import test.java.adidas.config.Config;
import test.java.adidas.pages.LandingPage;
import test.java.adidas.pages.ProductDetailPage;
import test.java.adidas.pages.ProductGridPage;
import test.java.adidas.utilities.GenericUtilities;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

/*
1. Open adidas.fi
2. Enter "jackets" in search text box
3. Filter for WOMENS jackets
4. Filter for product type as jackets
5. Filter for sport as "Yoga"
6. Select a product with subtitle "Women adidas by Stella McCartney" and open it
7. Validate that correct product was displayed
8. Add the product to wishlit
9. Validate that the number on the wishlist icon on the top nav now shows 1
 */

public class Test_SearchForWomensJacket extends TestBase {

    private WebDriver driver;
    private ProductGridPage productGridPage;
    private GenericUtilities genericUtilities;
    private ProductDetailPage productDetailPage;
    private final String SUBTITLE = "Women adidas by Stella McCartney";

    @Test(dataProvider = "testableBrowsers")
    public void testSearchForWomensJacket(String browser, String version, String platform, Method method) throws IOException, InterruptedException {

        driver = setDriver( browser, version, platform, method );
        genericUtilities = new GenericUtilities( driver );
        productGridPage = LandingPage.get( driver, Config.appURL )
                .checkModalAndClose()
                .searchForProductExpectingProductGrid( "jacket" );
        productDetailPage = productGridPage.selectGender( ProductGridPage.gender.WOMEN )
                .selectProductType( "Jackets" )
                .selectSport( "Yoga" )
                .selectProductBySubtitle( SUBTITLE );
        assertThat( "Correct product wasn't displayed when opening a product by subtitle",
                productDetailPage.getSubtitle(),
                equalToIgnoringCase( SUBTITLE ) );

        productDetailPage.addProductToWishlist();

        assertThat( "On adding the product to wishlist, the button did not change to remove from wishlist button",
                genericUtilities.isElementPresent( productDetailPage.removeFromWishListButton, 5 )
                , is( true ) );
        assertThat( "Email saved on newsletter subscribe page was not the same that was sent on landing page",
                productDetailPage.getWishlistCount(),
                equalTo( 1 ) );
    }
}
