package com.conor.barcodevalidator.domain.service.strategy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidatePrefixTest {

    private final ValidatePrefix validatePrefix = new ValidatePrefix();

    @ParameterizedTest
    @ValueSource(strings = {
            "AA",
            "ZZ",
            "AZ",
            "ZA",
            "BB",
            "OK"
    })
    void validateS10PrefixTest(String prefix) {
        assertTrue(validatePrefix.validateS10(prefix));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A1",
            "1A",
            "ZZZ",
            "U",
            "22",
            "  ",
            ""
    })
    void validateS10PrefixFailsTest(String prefix) {
        assertFalse(validatePrefix.validateS10(prefix));
    }
}