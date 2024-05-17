package scripts;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;
import org.example.apis.Delete;
import org.example.apis.Fetch;
import org.example.apis.Insert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testHelpers.ValidationHelpers;

import static org.example.base.BaseTest.expectedFetchResponse;
import static org.testng.Assert.assertTrue;

public class Scripts {

    private static String insertedId;

    @DataProvider(name = "InsertAPITest")
    public static Object[][] InsertAPITest() {
        return new Object[][]{
                {"Apple MacBook Pro 22", 2022, 1849.99, "Apple M3", "1 TB"},
        };
    }

    @Test(description = "Verify insert API", dataProvider = "InsertAPITest")
    public void verifyInsertTest(String name, int year, double price, String cpuModel, String hardDisk) {
        Insert insert = new Insert().buildRequest(name, year, price, cpuModel, hardDisk);
        JsonPath response = insert.execute().jsonPath();

        // Capture the id from the inserted response
        insertedId = response.getString("id");
        System.out.println("Inserted ID: " + insertedId);

        ValidationHelpers.validateInsertAPIResponse(response);
    }

    @Test(description = "Verify fetch API", dependsOnMethods = "InsertAPITest")
    public void verifyFetchTest(String id) {
        Fetch fetch = new Fetch();
        Response response = fetch.buildRequest(insertedId).execute();
        JsonObject fetchResponse = JsonParser.parseString(response.asString()).getAsJsonObject();
        System.out.println("Fetch API response: " + response);
        JsonAssertions.assertThatJson(fetchResponse).whenIgnoringPaths("$.id").isEqualTo(expectedFetchResponse);
    }

    @Test(description = "Verify delete API", dependsOnMethods = "verifyFetchTest")
    public void verifyDeleteTest() {
        Delete delete = new Delete().buildRequest(insertedId);
        Response response = delete.execute();
        int statusCode = response.getStatusCode();

        System.out.println("Delete API response status code: " + statusCode);
        assertTrue(statusCode == 200 || statusCode == 204, "Expected status code 200 or 204, but got " + statusCode);

        // Verify that the item has been deleted
        Fetch fetch = new Fetch().buildRequest(insertedId);
        Response fetchResponseAfterDelete = fetch.execute();
        String fetchResponseBodyAfterDelete = fetchResponseAfterDelete.asString();

        System.out.println("Fetch API response after delete: " + fetchResponseBodyAfterDelete);
        assertTrue(fetchResponseBodyAfterDelete.equals("[]"), "Expected empty array, but got " + fetchResponseBodyAfterDelete);
    }
}
