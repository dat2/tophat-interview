package com.dujay.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final ObjectMapper objectMapper;

    @Inject
    public JsonTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String render(Object model) throws Exception {
        return this.objectMapper.writeValueAsString(model);
    }
}
