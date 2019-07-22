package com.restassured.basesetup;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions( 
        //plugin = {"pretty"},
      
        plugin = { "pretty",
         "html:target/cucumberHtmlReport",
        "json:target/cucumber-report.json"
        }, // Plugin to generate HTML report and json report
        
      features = { "src/test/resources/featurefiles" }, 
        glue = { "" }
       // plugin =  {"com.cucumber.listener.ExtentCucumberFormatter:target/reprt.html"}

)
public class Runner {

    @BeforeClass
    public static void setUp() throws Throwable {
        System.out.println("BeforeClass");
        BaseSetup.getInstance();
       
    }

    @AfterClass
    public static void cleanUp() throws Throwable {
        BaseSetup.killInstance();

    }

}
