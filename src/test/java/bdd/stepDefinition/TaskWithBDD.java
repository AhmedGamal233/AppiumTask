package bdd.stepDefinition;

import Base.MobileUiBaseTest;
import comGoldenScentC3po.CartScreen;
import comGoldenScentC3po.HomeScreen;
import comGoldenScentC3po.LanguagePreferenceScreen;
import comGoldenScentC3po.ProductDetailsScreen;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TaskWithBDD extends MobileUiBaseTest {

    @Given("^As user after navigate to home screen and dismiss tooltip$")
    public void as_user_after_navigate_to_home_screen_and_dismiss_tooltip(){
        languagePreferenceScreen = new LanguagePreferenceScreen(mobileObject);
        languagePreferenceScreen.selectLanguage();
        homeScreen = new HomeScreen(mobileObject);
        homeScreen.dismissTooltip();
    }

    @When("^scroll to product and tap on it$")
    public void scroll_to_product_and_tap_on_it(){
        homeScreen.scrollToProductAndTap("أضف");
        throw new PendingException();
    }

    @When("^product details screen displayed and tap on buy now from pop up$")
    public void product_details_screen_displayed_and_tap_on_buy_now_from_pop_up(){
        productDetailsScreen = new ProductDetailsScreen(mobileObject);
        productDetailsScreen.addProductToCart();
        productDetailsScreen.TapOnBuyNow();
        throw new PendingException();
    }

    @Then("^Cart screen should be displayed with selected product$")
    public void cart_screen_should_be_displayed_with_selected_product(){
        cartScreen = new CartScreen(mobileObject);
        cartScreen.checkThatProductIsDisplayedAtCart();
    }

}
