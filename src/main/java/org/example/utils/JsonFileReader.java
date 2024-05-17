package org.example.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {

    public JsonObject readJsonFile(String filePath) throws IOException {
    try (FileReader reader = new FileReader(filePath)) {
        return JsonParser.parseReader(reader).getAsJsonObject();
    } catch (IOException e) {
        throw new IOException("Error reading JSON file: " + filePath, e);
    }
    }
}
