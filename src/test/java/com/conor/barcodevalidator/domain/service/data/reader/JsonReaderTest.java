package com.conor.barcodevalidator.domain.service.data.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    private static final List<Integer> WEIGHTS = Arrays.asList(8, 6, 4, 2, 3, 5, 9, 7);

    JsonReader jsonReader = new JsonReader();
    @Test
    void readWeightsFromFile() throws IOException {
        assertEquals(WEIGHTS, jsonReader.readWeightsFromFile());
    }
}