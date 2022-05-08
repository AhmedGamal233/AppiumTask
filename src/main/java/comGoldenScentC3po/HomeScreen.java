package comGoldenScentC3po;

import actions.MobileUiActions;
import com.github.hemanthsridhar.support.SearchBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import screenFactory.BaseScreen;
import screenFactory.ScreenObjectsConfig;

public class HomeScreen extends BaseScreen {
    public HomeScreen(AppiumDriver<?> appium) {
        super(appium);
    }


    @SearchBy(locatorsFile = ScreenObjectsConfig.Home_Screen, nameOfTheLocator = "tooltip")
    public WebElement toolTip;


    @SearchBy(locatorsFile = ScreenObjectsConfig.Home_Screen, nameOfTheLocator = "product")
    //public ArrayList<WebElement> product = new ArrayList<WebElement>();
    public WebElement product;


    @SearchBy(locatorsFile = ScreenObjectsConfig.Home_Screen, nameOfTheLocator = "cart_btn")
    public WebElement cart_btn;


    @SearchBy(locatorsFile = ScreenObjectsConfig.Home_Screen, nameOfTheLocator = "searchbar")
    public WebElement searchbar;


    public void dismissTooltip() {
        MobileUiActions.setExplicitWait(mobileObject, toolTip, 15);
        MobileUiActions.Click(toolTip);
    }

    public void scrollToProductAndTap(String txt) {

        MobileUiActions.setExplicitWait(mobileObject, searchbar, 10);
        MobileUiActions.scrollIntoView(mobileObject,txt);
        MobileUiActions.setExplicitWait(mobileObject, product, 10);
        MobileUiActions.Click(product);
    }


    public void openCartFromButtonBar() {
        MobileUiActions.setExplicitWait(mobileObject, cart_btn, 10);
        MobileUiActions.Click(cart_btn);
    }

}
