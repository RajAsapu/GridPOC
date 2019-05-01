package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions
        (plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
                features = {"src/test/resources/Demo.feature"},
                glue = {"classpath:"},
                tags = {},
                monochrome = true)
public class RunCucumberTests {
    private static Logger log = LoggerFactory.getLogger(RunCucumberTests.class);

    @AfterClass
    public static void tearDown() {
        String projectName = "Grid POC";

        File reportOutputDirectory = new File("out");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json-report.json");

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}