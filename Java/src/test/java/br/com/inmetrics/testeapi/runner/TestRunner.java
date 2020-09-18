package br.com.inmetrics.testeapi.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"br.com.inmetrics.testeapi.stepDefinitions"},
        monochrome = true,
        strict = true,
        plugin = {"pretty","junit:target/JUnitReports/report.xml",
        				"json:target/JSONReports/report.json",
        				"html:target/HtmlReports"}
)

public class TestRunner {

}
