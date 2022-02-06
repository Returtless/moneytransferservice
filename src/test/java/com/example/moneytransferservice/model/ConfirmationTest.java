package com.example.moneytransferservice.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class ConfirmationTest {

    @Test
    public void testDeserializeJson() throws IOException {
        final String operationId = "123456";
        final String code = "0000";

        String jsonString = String.format("{\"operationId\": \"%s\", \"code\": \"%s\"}", operationId, code);

        ObjectMapper mapper = new ObjectMapper();
        Confirmation confirmation = mapper.readValue(jsonString, Confirmation.class);

        assertThat(confirmation.getOperationId().getOperationId(), equalTo(operationId));
        assertThat(confirmation.getCode(), equalTo(code));
    }

}