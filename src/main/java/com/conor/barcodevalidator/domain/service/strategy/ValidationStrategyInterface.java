package com.conor.barcodevalidator.domain.service.strategy;

import java.io.IOException;

public interface ValidationStrategyInterface {

    boolean validateS10(String barcode) throws IOException;
}
