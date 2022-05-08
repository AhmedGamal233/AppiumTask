package bdd.Runner;

import Base.MobileUiBaseTest;
import cucumber.api.CucumberOptions;


@CucumberOptions(features="src/test/java/bdd/FeatureFiles" //this where Purchase.feature exist
        ,glue= {"bdd.stepDefinition"} //this is runner class package name
        ,plugin= {"pretty","html:target/cucumber-html-report"}) //to make html report
public class TestRunnerMobileUi extends MobileUiBaseTest
{
}
