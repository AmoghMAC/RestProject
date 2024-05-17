package org.example.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected RequestSpecBuilder requestSpecBuilder;

    private Response response;
    private MethodType method;

    public BaseApi() {
        this.requestSpecBuilder = new RequestSpecBuilder();
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return requestSpecBuilder;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public Response execute() {
        Response response = null;
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpec = requestSpecBuilder
                .addFilter(new CurlLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
        switch (method) {
            case GET:
                response = RestAssured.given().spec(requestSpec).get();
                break;
            case POST:
                response = RestAssured.given().spec(requestSpec).post();
                break;
            case DELETE:
                response = RestAssured.given().spec(requestSpec).delete();
                break;
            default:
                throw new RuntimeException("Invalid method type");
        }
        this.response = response;
        return response;
    }
    
}
