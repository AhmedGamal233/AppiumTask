package Base;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;


/**
 * This Class for set CompatibilityConfig for Web cross testing
 */
public class MobileCompatibilityConfig {
    private static String fileName = "AndroidDesiredCapabilities.properties";
    private static DesiredCapabilities caps = new DesiredCapabilities();

    public static AndroidDriver androidConfig() throws MalformedURLException {
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, getDesiredCapability("platformName"));
        caps.setCapability(MobileCapabilityType.UDID, getDesiredCapability("udid"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, getDesiredCapability("automationName"));
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, getDesiredCapability("platformVersion"));
        caps.setCapability("skipServerInstallation", getDesiredCapability("skipServerInstallation"));
        caps.setCapability("autoGrantPermissions", getDesiredCapability("autoGrantPermissions"));

        // caps.setCapability("app", "");
        caps.setCapability("appPackage", getDesiredCapability("appPackage"));
        caps.setCapability("appActivity", getDesiredCapability("appActivity"));

        buildAppiumServer().start();
        return new AndroidDriver(buildAppiumServer().getUrl(), caps);
    }


    public static DesiredCapabilities installBuild(String platform, String installWay, AppiumDriver mobileObject, String buildPath) {
        if (platform.equals("IOS")) {
            if (installWay.equals("downloadFromURL")) {
                try {
                    mobileObject.executeScript("mobile: installApp", ImmutableMap.of("app", buildPath));
                } catch (Exception e) {
                    caps.setCapability(MobileCapabilityType.APP, buildPath);
                }
            } else if (installWay.equals("installFromResources")) {
                try {
                    mobileObject.executeScript("mobile: installApp", ImmutableMap.of("app", System.setProperty("app", "src\\test\\resources\\Builds\\build.ipa")));
                } catch (Exception e) {
                    caps.setCapability(MobileCapabilityType.APP, System.setProperty("app", "src\\test\\resources\\Builds\\build.ipa"));
                }
            }
        } else if (platform.equals("Android")) {
            if (installWay.equals("downloadFromURL")) {
                caps.setCapability(MobileCapabilityType.APP, buildPath);

            } else if (installWay.equals("installFromResources")) {
                caps.setCapability(MobileCapabilityType.APP, System.setProperty("app", "src\\test\\resources\\Builds\\build.apk"));
            }
            return caps;

        }

        return null;
    }

    public static IOSDriver iosConfig() throws MalformedURLException {


        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, getDesiredCapability("platformName"));
        caps.setCapability(MobileCapabilityType.UDID, getDesiredCapability("udid"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "automationName");
        caps.setCapability("bundleId", getDesiredCapability("bundleId"));
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "PLATFORM_VERSION");

        buildAppiumServer().start();
        return new IOSDriver(buildAppiumServer().getUrl(), caps);
    }

    public static AppiumDriverLocalService buildAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingPort(4725);
        //   AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        return service;
    }

    public static void stopAppiumServer() {
        buildAppiumServer().stop();
    }

    public static String getDesiredCapability(String capsName) {

        String filePath = "Configs/Data/" + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e1) {
            System.out.println("file not found");
            e1.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(reader);
        } catch (IOException e) {
            System.out.println("file not read");
            e.printStackTrace();
        }
        return prop.getProperty(capsName);
    }

}
