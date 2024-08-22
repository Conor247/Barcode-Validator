package com.conor.barcodevalidator.unit.domain.service.strategy;

import com.conor.barcodevalidator.domain.service.data.JsonReaderInterface;
import com.conor.barcodevalidator.domain.service.strategy.ValidateCheckDigit;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateCheckDigitTest {

    private static final List<Integer> WEIGHTS = Arrays.asList(8, 6, 4, 2, 3, 5, 9, 7);

    @Mock
    private JsonReaderInterface jsonReader;

    @InjectMocks
    private ValidateCheckDigit validateCheckDigit;

    @ParameterizedTest
    @ValueSource(strings = {
            "473124829",
            "873324826",
            "000000014",
            "999999995", //changes check digit from 11 to 5
            "000000990" //changes check digit from 10 to 0
    })
    void validateS10CheckDigitTest(String serialCheck) throws IOException {
        when(jsonReader.readWeightsFromFile()).thenReturn(WEIGHTS);
        assertTrue(validateCheckDigit.validateS10(serialCheck));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "473224829",
            "473224821"
    })
    void validateS10CheckDigitFailsTest(String serialCheck) throws IOException {
        when(jsonReader.readWeightsFromFile()).thenReturn(WEIGHTS);
        assertFalse(validateCheckDigit.validateS10(serialCheck));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "AB",
            "DEBNMCVY",
            "12345678",
            "22",
            "",
            "        ",
            "47322482A",
            "BCDSDEFGA"
    })
    void validateS10CheckDigitLengthFailsTest(String serialCheck) throws IOException {
        assertFalse(validateCheckDigit.validateS10(serialCheck));
    }
}