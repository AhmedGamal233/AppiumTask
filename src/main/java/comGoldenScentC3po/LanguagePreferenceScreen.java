package comGoldenScentC3po;

import actions.MobileUiActions;
import com.github.hemanthsridhar.support.SearchBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import screenFactory.BaseScreen;
import screenFactory.ScreenObjectsConfig;

public class LanguagePreferenceScreen extends BaseScreen {
    public LanguagePreferenceScreen(AppiumDriver<?> appium) {
        super(appium);
    }


    @SearchBy(locatorsFile = ScreenObjectsConfig.LanguagePreference_Screen, nameOfTheLocator = "apply_btn")
    public WebElement apply_btn;
    public void selectLanguage(){
        MobileUiActions.setExplicitWait(mobileObject,apply_btn,10);
        MobileUiActions.Click(apply_btn);
    }
}
