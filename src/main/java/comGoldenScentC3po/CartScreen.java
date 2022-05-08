package comGoldenScentC3po;

import actions.MobileUiActions;
import com.github.hemanthsridhar.support.SearchBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import screenFactory.BaseScreen;
import screenFactory.ScreenObjectsConfig;

public class CartScreen extends BaseScreen {



    @SearchBy(locatorsFile = ScreenObjectsConfig.Cart_Screen, nameOfTheLocator = "purchasedCard")
    public WebElement purchasedCard;

    @SearchBy(locatorsFile = ScreenObjectsConfig.Cart_Screen, nameOfTheLocator = "productName")
    public WebElement productName;

    public CartScreen(AppiumDriver<?> appium) {
        super(appium);
    }

    public void checkThatProductIsDisplayedAtCart(){
        MobileUiActions.setExplicitWait(mobileObject,purchasedCard,10);
        MobileUiActions.checkIfElementISDisplayed(purchasedCard);
    }


}
