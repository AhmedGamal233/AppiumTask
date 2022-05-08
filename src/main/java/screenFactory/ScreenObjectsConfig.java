package screenFactory;

/**
 * this interface provide locators files path that script needed
 */
public interface ScreenObjectsConfig {
    String BASE_Screen_OBJECT_PATH = "Configs/ElementLocators";
    String Cart_Screen = BASE_Screen_OBJECT_PATH + "/CartScreenLocators.json";
    String Home_Screen = BASE_Screen_OBJECT_PATH + "/HomeScreenLocators.json";
    String LanguagePreference_Screen = BASE_Screen_OBJECT_PATH + "/LanguagePreferenceScreenLocators.json";
    String ProductDetails_Screen = BASE_Screen_OBJECT_PATH + "/ProductDetailsScreenLocators.json";


}
