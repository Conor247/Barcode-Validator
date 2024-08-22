package com.conor.barcodevalidator.domain.service.strategy;

import com.conor.barcodevalidator.domain.service.data.reader.JsonReaderInterface;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ValidateCheckDigit implements ValidationStrategyInterface {

    private static final int MODULUS = 11;
    private final JsonReaderInterface jsonReader;

    public ValidateCheckDigit(JsonReaderInterface jsonReader) {
        this.jsonReader = jsonReader;
    }

    @Override
    public boolean validateS10(String serialCheck) throws IOException {

        try {
            if (serialCheck == null || serialCheck.length() != 9) {
                throw new IllegalArgumentException("The serialCheck string must be exactly 9 characters long.");
            }

            String serialNum = serialCheck.substring(0, 8);
            String checkDigit = serialCheck.substring(8);

            return checkDigit.equals(
                    String.valueOf(
                            calculateCheckDigit(serialNum)
                    )
            );
        } catch (IllegalArgumentException e) {
            return false;
        }
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
