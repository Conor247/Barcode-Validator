package com.conor.barcodevalidator.domain.service.data;

import java.io.IOException;
import java.util.List;

public interface JsonReaderInterface {
    List<Integer> readWeightsFromFile() throws IOException;
}
