package com.conor.barcodevalidator.domain.service.strategy;

import com.conor.barcodevalidator.domain.service.data.JsonReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ValidateCheckDigit implements ValidationStrategyInterface {

    private static final int MODULUS = 11;
    private final JsonReader jsonReader;

    public ValidateCheckDigit(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @Override
    public boolean validateS10(String serialCheck) throws IOException {
        if (isValidSerialCheck(serialCheck)) {
            String serialNum = serialCheck.substring(0, 8);
            String checkDigit = serialCheck.substring(8);

            return checkDigit.equals(String.valueOf(calculateCheckDigit(serialNum)));
        }
        return false;
    }

    private boolean isValidSerialCheck(String serialCheck) {
        return serialCheck != null && serialCheck.matches("^\\d{9}$");
    }

    private List<Integer> convertToList(String numberString) {
        return numberString.chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());
    }

    private int calculateSum(String serial) throws IOException {

        List<Integer> serialList = convertToList(serial);
        List<Integer> weights = jsonReader.readWeightsFromFile();

        return IntStream.range(0, serialList.size())
                .map(i -> weights.get(i) * serialList.get(i))
                .sum();
    }

    private int calculateCheckDigit(String serialNum) throws IOException {

        int checkDigit = MODULUS - (calculateSum(serialNum) % MODULUS);

        return switch (checkDigit) {
            case 10 -> 0;
            case 11 -> 5;
            default -> checkDigit;
        };
    }
}
