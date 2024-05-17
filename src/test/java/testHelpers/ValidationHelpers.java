package testHelpers;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.SoftAssertions;

public class ValidationHelpers {

    public static String id;

    public static void validateInsertAPIResponse(JsonPath response) {
        SoftAssertions softAssert = new SoftAssertions();
        id = (response.getString("id"));
        softAssert.assertThat(id).isNotEmpty();
        softAssert.assertThat(response.getString("name")).isEqualTo("Apple MacBook Pro 22");
        softAssert.assertThat(response.getString("createdAt")).isNotEmpty();
        softAssert.assertThat(response.getInt("data.year")).isEqualTo(2022);
        softAssert.assertThat(response.getDouble("data.price")).isEqualTo(1849.99);
        softAssert.assertThat(response.getString("data.CPU model")).isEqualTo("Apple M3");
        softAssert.assertThat(response.getString("data.Hard disk size")).isEqualTo("1 TB");
        System.out.println("ID: "+id+" inserted successfully");
        softAssert.assertAll();
    }
}
