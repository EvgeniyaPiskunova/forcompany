package org.home.forcompany;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestApi {

    @Test
    public void testGet() {
        Response response =
                given()
                        .baseUri("https://api.openbrewerydb.org")
                        .when()
                        .log()
                        .all()
                        .get("/v1/breweries/b54b16e1-ac3b-4bff-a11f-f7ae9ddc27e0");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("id"), "b54b16e1-ac3b-4bff-a11f-f7ae9ddc27e0");
        Assert.assertEquals(response.jsonPath().getString("name"), "MadTree Brewing 2.0");
    }

    @Test
    public void testPostCreate() {
        int userId = 1;
        int id = 101;
        String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String body = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto";
        Map<String, String> payload = Map.of(
                "userId", String.valueOf(userId), "id", String.valueOf(id), "title", title, "body", body);
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri("https://jsonplaceholder.typicode.com")
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getInt("userId"), userId);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("title"), title);
        Assert.assertEquals(response.jsonPath().getString("body"), body);
    }

    @Test
    public void testDelete() {
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .delete("/posts/1")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.body().print(), "{}");
    }

    @Test
    public void testGetUser() {
        Response response =
                given()
                        .baseUri("https://reqres.in/")
                        .when()
                        .log()
                        .all()
                        .get("api/users/2");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
    }

    @Test
    public void testGetUserList() {
        given()
                .baseUri("https://reqres.in/")
                .when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", Matchers.contains(7, 8, 9, 10, 11, 12));
    }

    @Test
    private void testPostUser() {
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .and()
                .baseUri("https://reqres.in/")
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("id");
        String token = jsonPath.getString("token");
        Assert.assertNotNull(id);
        Assert.assertEquals(token, "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void testPutUser() {
        Map<String, String> user = new HashMap<>();
        user.put("email", "janet.weaver@reqres.in");
        user.put("first_name", "Janet");
        user.put("last_name", "Weaver");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .and()
                .baseUri("https://reqres.in/")
                .when()
                .put("api/users/2")
                .then()
                .extract().response();
        String first_name = response.jsonPath().getString("first_name");
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(first_name, "Janet");
    }

    @Test
    public void testDeleteUser() {
        Map<String, String> user = new HashMap<>();
        user.put("email", "janet.weaver@reqres.in");
        user.put("first_name", "Janet");
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri("https://reqres.in/")
                .when()
                .delete("api/users/2")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 204);
    }


}