package com.conor.barcodevalidator.domain.service.data;

import com.conor.barcodevalidator.domain.model.Weights;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class JsonReader {

    public List<Integer> readWeightsFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/weights.json"), Weights.class).getS10();
    }
}
