package fliptest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    Response response;

    @Given("client get the access token")
    public void clientGetTheAccessToken() {
        RestAssured.baseURI= "https://gorest.co.in";
    }

    @When("client send a GET endpoint {string}")
    public void clientSendAGETEndpoint(String endpoint) {
//        RestAssured.basePath=endpoint;
        response = given()
                .headers(
                "Authorization",
                "Bearer 5f660bc45521d8a5396907d4e47b7568054a48aef9ea70a59fcf155cdd3ce0fb")
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Then("client verify the todo items are {int}")
    public void clientVerifyTheItemsAre(Integer count) {
        assertEquals(count, response.jsonPath().getList("id").size(), "data tidak sama dengan " + count);
    }

    @Then("client verify the user status is {string}")
    public void clientVerifyTheUserStatusIs(String condition) {
        List<String> users = response.jsonPath().getList("status");
        for(int i = 0; i< users.size(); i++) {
            assertEquals(condition, users.get(i));
        }
    }
}
