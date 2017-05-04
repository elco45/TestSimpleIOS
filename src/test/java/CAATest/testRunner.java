package CAATest;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = "pretty",
        features = {"src/test/java/CAATest/features/"}
)
public class testRunner {
    
}
