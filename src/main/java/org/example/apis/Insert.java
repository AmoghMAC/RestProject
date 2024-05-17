package org.example.apis;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.base.BaseApi;
import org.example.base.MethodType;
import org.example.utils.Constants;
import org.example.utils.LocalConfig;

public class Insert extends BaseApi {

    public Insert() {
        setMethod(MethodType.POST);
        getRequestSpecBuilder().setBaseUri(LocalConfig.BASE_URI)
                .setBasePath(Constants.basePath.OBJECTS)
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public Insert buildRequest(String name, int year, Double price, String cpuModel, String hardDisk) {

        JsonObject requestBody = new JsonObject();

        requestBody.addProperty("name", name);

        JsonArray dataArray = new JsonArray();
        JsonObject insertData = new JsonObject();

        insertData.addProperty("year", year);
        insertData.addProperty("price", price);
        insertData.addProperty("CPU Model", cpuModel);
        insertData.addProperty("Hard disk size", hardDisk);

        dataArray.add(insertData);
        requestBody.add("data", dataArray);

        getRequestSpecBuilder().setBody(requestBody.toString());

        return this;
    }

}
