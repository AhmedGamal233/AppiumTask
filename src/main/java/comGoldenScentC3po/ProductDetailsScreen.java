package comGoldenScentC3po;

import actions.MobileUiActions;
import com.github.hemanthsridhar.support.SearchBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import screenFactory.BaseScreen;
import screenFactory.ScreenObjectsConfig;

public class ProductDetailsScreen extends BaseScreen {
    public ProductDetailsScreen(AppiumDriver<?> appium) {
        super(appium);
    }


    @SearchBy(locatorsFile = ScreenObjectsConfig.ProductDetails_Screen, nameOfTheLocator = "addToCart_btn")
    public WebElement addToCart_btn;


    @SearchBy(locatorsFile = ScreenObjectsConfig.ProductDetails_Screen, nameOfTheLocator = "buyNow_btn")
   // @FindBy(xpath = "//*[contains(@resource-id,'button1')]")
    public WebElement buyNow_btn;

    public void addProductToCart() {
        MobileUiActions.setExplicitWait(mobileObject, addToCart_btn, 10);
        MobileUiActions.Click(addToCart_btn);
    }

    public void TapOnBuyNow() {
        MobileUiActions.setExplicitWait(mobileObject, buyNow_btn, 10);
        MobileUiActions.Click(buyNow_btn);
    }
}
