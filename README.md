# Com.GoldenScentC3po

comGoldenScentC3poTests by creating an automation framework using Java, Appium, TestNG, Cucumber. In short: this framework will be a Modular ScreenFactory-POM-DDT hybrid framework .



This framework will include the following:

<details>
<summary>
<b><i><u>Click me to display this section</u></i></b>
</summary>
<p>

- POM (Page Object Model) - To separate function driven methods into several classes as well as using Custom PageFactory to get locators from external file to find elements .
- Object Repository - To store WebElement locators in json files,Test Data in (properties,CSV,Excel) files and Selenium Grid Configs jeson files.
- DDT(Data Driven Testing) - the test scripts read test data from data sources (properties,CSV,Excel) by DataProvider annotation support by TestNG with ability to select the dataSourceFileName and its format from a configuration file (testngSuite.xml)
- Cross Platform support (IOS,Android) - Selection of execution Platform should be managed from a configuration file (testngSuite.xml)
- Using TestNG framework for running and Manipulating Tests
- Excution Report - Using Allure 

</p>
</details>

This framework will be missing the following (for now):

<details>
<summary>
<b><i><u>Click me to display this section</u></i></b>
</summary>
<p>

- **Note that while I provide only these options, there may be many more. There is no right or wrong way of implementing these types of frameworks, it all just depends on your situation (the website being tested, your script creators, complexity of the test cases, etc)**

- KDT (Keyword Driven Testing) 



## Setting up a Framework from Scratch

<details>
<summary>
<b><i><u>Click me to display this section</u></i></b>
</summary>
<p>

1. Create a Maven project.
2. Create proper project structure.
3. Add required dependencies into pom.xml

   a) testng version(7.0.0)
   
   b) selenium-java version (3.141.59)
   
   c) io.appium version (7.3.0)
   
   D) allure-testng  version (2.13.0)
   
   e) custom-page-factory version (1.0.0)& poi-ooxml version (3.17) for custom PAGEFACTORY solution
   
   f) log4j  version 1.2.17 for logs
   
   g) cucumber-testng version 1.2.5 
   
4. Make UiAction class to hold to make the most common actions on webElements in the frame work
5. Make Helper Class to hold generic method needed in handle DDT reading data from external file & taking screenshots from failed test cases
6. ScreenObjectsConfig interface provide locators files path that script needed for SearchBy annotation in Custom PageFactory solution 
7. Setup cross browser testing by creating a testngSuite.xml runner that utilizes parameters.
8. Implement the POM (Page Object Model) framework.
9. Setup custom test reporting (Allure reporting)
   this is thy way to Make Test Report by allure:
   1)Download the latest version as zip archive from Maven Central.(from Github) https://github.com/allure-framework/allure2/releases

   2)Unpack the archive to allure-commandline directory.(extract folder)

   3)Navigate to bin directory.

   4)Use allure.bat for Windows.

   5)Add allure to system PATH. (in System Variables) like java variables we did.
   
   6)then run your testcase  ---->folder named allure-results will apear after test finish (in eclipse refresh your project to make this folder appear).
   
   7)after that go to your project directory run cmd and then type -> allure.bat serve allure-results
   





</p>
</details>


## Notes

<details>
<summary>
<b><i><u>Click me to display this section</u></i></b>
</summary>
<p>

- Refer to the published Javadoc for detailed information on the available functions/classes/packages from [this link](https://github.com/AhmedGamal233/Com.Google.VOIS.Challenge/docs) 

</p>
</details>

***
