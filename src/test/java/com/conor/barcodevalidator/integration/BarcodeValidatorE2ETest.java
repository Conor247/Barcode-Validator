package com.conor.barcodevalidator.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BarcodeValidatorE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @ValueSource(strings = {
            "AB473124829GB",
            "ZZ873324826GB",
            "BB000000014GB",
            "GG999999995GB", //changes check digit from 11 to 5
            "OK000000990GB" //changes check digit from 10 to 0
    })
    public void testValidateBarcode_Success(String barcode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("barcode", barcode);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                "/validate", HttpMethod.POST, entity, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A3473124829GB",
            "BBB00000014GB",
            "ZZ3873324826GB",
            "GG999999995IE",
            "",
            "AA12345GB",
            "A@473124829GB",
            "AB4731#4829GB"
    })
    public void testValidateBarcode_Failure(String barcode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("barcode", barcode);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                "/validate", HttpMethod.POST, entity, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody());
    }
}
