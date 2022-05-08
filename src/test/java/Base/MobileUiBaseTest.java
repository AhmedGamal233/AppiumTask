package Base;

import comGoldenScentC3po.CartScreen;
import comGoldenScentC3po.HomeScreen;
import comGoldenScentC3po.LanguagePreferenceScreen;
import comGoldenScentC3po.ProductDetailsScreen;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import helper.Helper;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static Base.MobileCompatibilityConfig.*;

public class MobileUiBaseTest extends AbstractTestNGCucumberTests {
    protected static AppiumDriver mobileObject;
    protected LanguagePreferenceScreen languagePreferenceScreen;
    protected HomeScreen homeScreen;
    protected ProductDetailsScreen productDetailsScreen;
    protected CartScreen cartScreen;


    @BeforeSuite
    @Parameters({"PlatformType", "installWay", "buildPath"})
    public void BeforeSuite(@Optional("Android") String PlatformType, @Optional("installFromResources")String installWay, @Optional("xxx")String buildPath) throws MalformedURLException {
        mobileObject = PlatformType.equals("Android") ? androidConfig() : iosConfig();
        //   installBuild(PlatformType,installWay,mobileObject,buildPath);

    }

    @BeforeClass
    @Parameters({"PlatformType"})
    public void BeforeClass(@Optional("Android")String PlatformType) throws MalformedURLException {

        //  mobileObject= PlatformType.equals("Android")? androidConfig():iosConfig();
        languagePreferenceScreen = new LanguagePreferenceScreen(mobileObject);
        homeScreen = new HomeScreen(mobileObject);
        productDetailsScreen = new ProductDetailsScreen(mobileObject);
        cartScreen = new CartScreen(mobileObject);
    }

    @AfterClass
    public void AfterClass() {
        mobileObject.quit();
    }

    @AfterSuite
    public void AfterSuite() {
        //    mobileObject.removeApp(getDesiredCapability("appPackage"));
        stopAppiumServer();
    }

    /**
     * This method is responsible for :
     * take screenshot when test case fail and add it in the Screenshots Directory
     *
     * @param result to get status and testcase name from it
     */
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenshot(mobileObject, result.getName());

        }

    }


}
