package com.conor.barcodevalidator.domain.service.data.reader;

import java.io.IOException;
import java.util.List;

public interface JsonReaderInterface {
    List<Integer> readWeightsFromFile() throws IOException;
}
