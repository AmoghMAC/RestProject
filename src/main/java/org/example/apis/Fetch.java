package org.example.apis;

import org.example.base.BaseApi;
import org.example.base.MethodType;
import org.example.utils.Constants;
import org.example.utils.LocalConfig;

public class Fetch extends BaseApi {

    public Fetch() {
        getRequestSpecBuilder().setBaseUri(LocalConfig.BASE_URI);
        getRequestSpecBuilder().setBasePath(Constants.basePath.OBJECTS);
    }

    public Fetch buildRequest(String id) {
        setMethod(MethodType.GET);
        getRequestSpecBuilder().addQueryParam("id", id);

        return this;
    }
}
