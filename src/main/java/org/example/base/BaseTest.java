package org.example.base;

import com.google.gson.JsonObject;
import org.example.utils.JsonFileReader;
import org.example.utils.LocalConfig;

import java.io.IOException;

public class BaseTest {

    public static JsonObject expectedFetchResponse;

    public void loadTestData() throws IOException {
        System.out.println("Loading the test data");
        JsonFileReader jsonFileReader = new JsonFileReader();
        expectedFetchResponse = jsonFileReader.readJsonFile(LocalConfig.FETCH_API_RESPONSE);
    }
}
