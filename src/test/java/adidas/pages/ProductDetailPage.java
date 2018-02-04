package test.java.adidas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.adidas.utilities.GenericUtilities;

public class ProductDetailPage {

    private WebDriver driver;
    private GenericUtilities genericUtilities;

    @FindBy(xpath = "//div[contains(@class,'subtitle')]")
    private WebElement subtitle;

    @FindBy(xpath = "//i[contains(@class,'icon-heart-empty')]")
    private WebElement addToWishListButton;

    @FindBy(xpath = "//i[contains(@class,'icon-heart-filled')]")
    public static WebElement removeFromWishListButton;

    @FindBy(xpath = "//span[contains(@class,'wishlist_icon')]")
    private WebElement wishlisticon;


    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities = new GenericUtilities( driver );
        PageFactory.initElements( driver, this );
    }

    public String getSubtitle() {
        genericUtilities.waitForElementPresent( subtitle );
        return subtitle.getText();
    }

    public ProductDetailPage addProductToWishlist() {
        if (genericUtilities.isElementPresent( addToWishListButton, 2 )) {
            addToWishListButton.click();
        }
        return this;
    }

    public int getWishlistCount() {
        return Integer.parseInt( wishlisticon.getText() );
    }
}
