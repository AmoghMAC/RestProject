package org.example.apis;

import org.example.base.BaseApi;
import org.example.base.MethodType;
import org.example.utils.Constants;
import org.example.utils.LocalConfig;

public class Delete extends BaseApi{

    public Delete() {
            setMethod(MethodType.DELETE);
            getRequestSpecBuilder().setBaseUri(LocalConfig.BASE_URI);
            getRequestSpecBuilder().setBasePath(Constants.basePath.OBJECTS);
        }

        public Delete buildRequest(String id) {
            getRequestSpecBuilder().setBasePath(Constants.basePath.OBJECTS + "/" + id);

            return this;
        }
    }

