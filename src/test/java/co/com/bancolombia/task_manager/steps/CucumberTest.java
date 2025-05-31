package co.com.bancolombia.task_manager.steps;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("co/com/bancolombia/task_manager")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.com.bancolombia.task_manager.steps")
public class CucumberTest {
}