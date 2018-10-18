package com.qa.parameterised_bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/qa/parameterised_bdd/Parametisation.feature", glue = {"com.qa.parameterised_bdd"})
public class Runner {

}
