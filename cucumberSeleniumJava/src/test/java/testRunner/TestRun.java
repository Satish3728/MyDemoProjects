package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(
			features = ".//Features//Login.feature",
			glue = "stepDefinitions",
			dryRun = false,			//if true:crosschecking every steps are implemented in feature file or not
			monochrome = true,
			plugin = {"pretty", "html:test-output"},			//pretty- can see the output very clearly
			tags = "@sanity"	
				
		)


public class TestRun {

}
