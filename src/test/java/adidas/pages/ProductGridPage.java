package test.java.adidas.pages;

import org.apache.commons.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import test.java.adidas.utilities.GenericUtilities;

public class ProductGridPage {

    private WebDriver driver;
    private GenericUtilities genericUtilities;

    public enum gender{
        MEN,
        WOMEN,
        KIDS
    }
    private String filterLinkByGender="//a[@data-refname='gender' and @data-refvalue='%s']";
    private String filterLinkByProductType="//a[@data-refname='producttype' and @data-refvalue='%s']";
    private String filterLinkBySport="//a[@data-refname='sport' and @data-refvalue='%s']";
    private String productLinkBySubtitle="//span[.='%s']/..";
    private String genderFilterSelected="//div[@data-context='filter_gender:%s']";
    private String productTypeFilterSelected="//div[@data-context='filter_productType:%s']";
    private String sportFilterSelected="//div[@data-context='filter_sport:%s']";

    public ProductGridPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities=new GenericUtilities( driver );
        PageFactory.initElements(driver, this);
    }

    public ProductGridPage selectGender(ProductGridPage.gender gender) throws InterruptedException {
        genericUtilities.waitForElementPresent( By.xpath( String .format( filterLinkByGender, WordUtils.capitalizeFully
                (gender.toString()) ) ) );

       driver.findElement(  By.xpath( String .format( filterLinkByGender, WordUtils.capitalizeFully
               (gender.toString()) ) ) ).click();
       genericUtilities.waitForElementPresent( By.xpath( String.format( genderFilterSelected,WordUtils.capitalizeFully
               (gender.toString()) ) ) );
        return this;
    }

    public ProductGridPage selectProductType(String productType) throws InterruptedException {
        genericUtilities.waitForElementPresent( By.xpath( String .format( filterLinkByProductType, productType) ) );
        driver.findElement( By.xpath( String .format( filterLinkByProductType, productType) ) ).click();
        genericUtilities.waitForElementPresent( By.xpath( String .format( productTypeFilterSelected,productType ) )  );
        return this;
    }

    public ProductGridPage selectSport(String sport) throws InterruptedException {
        genericUtilities.waitForElementPresent( By.xpath( String .format( filterLinkBySport, sport) ) );
        genericUtilities.scroll( "1250",false );
        driver.findElement( By.xpath( String .format( filterLinkBySport, sport) ) ).click();
        genericUtilities.waitForElementPresent( By.xpath( String .format( sportFilterSelected,sport ) )  );
        return this;
    }

    public ProductDetailPage selectProductBySubtitle(String subTitle) throws InterruptedException {
        genericUtilities.waitForElementPresent( By.xpath( String .format( productLinkBySubtitle, subTitle) ) );
        genericUtilities.clickStaleElement( By.xpath( String .format( productLinkBySubtitle, subTitle) ) );
        //driver.findElement( By.xpath( String .format( productLinkBySubtitle, subTitle) ) ).click();
        return new ProductDetailPage( driver );
    }
}
