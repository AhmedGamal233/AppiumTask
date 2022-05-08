package comGoldenScentC3poTests;

import Base.MobileUiBaseTest;
import io.qameta.allure.Description;

import org.testng.annotations.Test;

public class Task extends MobileUiBaseTest {

    @Description("Checking adding product to cart")
    @Test
    public void addProductToCart(){
        //select Language
        languagePreferenceScreen.selectLanguage();
        //dismissTooltip
        homeScreen.dismissTooltip();
        //scroll to product
        //open product
        homeScreen.scrollToProductAndTap("أضف");
        //add product to cart
        productDetailsScreen.addProductToCart();
        productDetailsScreen.TapOnBuyNow();
        //close product
        //open cart
        //check that product at cart
        cartScreen.checkThatProductIsDisplayedAtCart();
    }
}
