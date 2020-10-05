package cucumber.Options;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/feature",plugin="json:target/jsonReports/cucumber-report.json",
				 glue="stepDefinitions")
public class TestRunner {

}
//tags={"@DeletePlace"}