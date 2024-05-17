package org.example.base;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CurlLoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        String method = requestSpec.getMethod();
        String uri = requestSpec.getURI();

        StringBuilder curl = new StringBuilder();
        curl.append("curl ");
        curl.append("-X ").append(method).append(" ");

        Headers headers = requestSpec.getHeaders();
        for (Header header : headers) {
            curl.append("-H ").append(header.getName()).append(": ").append(header.getValue()).append("' ");
        }

        if(requestSpec.getBody() != null) {
            curl.append("-d '").append(requestSpec.getBody().toString()).append("' ");
        }

        curl.append(uri);

        System.out.println("The cURL Request is: \n"+curl+"\n");

        return ctx.next(requestSpec, responseSpec);
    }

}
