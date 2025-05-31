package co.com.bancolombia.task_manager.steps;

import io.cucumber.java.en.Given;
import io.restassured.specification.RequestSpecification;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class TaskStepDefinitions {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> requestBody;

    @Given("the user provides a valid JSON")
    public void the_user_provides_a_valid_json() {
        requestBody = new HashMap<>();
        requestBody.put("name", "Integration Task");
        requestBody.put("description", "Integration description");
        request = given()
                .contentType("application/json")
                .body(requestBody);
    }

    @When("a POST is made to \\/tasks")
    public void a_post_is_made_to_tasks() {
        response = request.when().post("http://localhost:3000/api/v1/tasks");
    }

    @Then("the system responds with status 201")
    public void the_system_responds_with_status_201() {
        response.then().statusCode(201);
    }

    @And("the body contains the ID and the saved data")
    public void the_body_contains_the_id_and_the_saved_data() {
        response.then()
                .body("id", notNullValue())
                .body("name", equalTo(requestBody.get("name")))
                .body("description", equalTo(requestBody.get("description")));
    }
}