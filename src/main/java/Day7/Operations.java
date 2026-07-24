package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operations {
    private List<String> lines = new ArrayList<>();

    public Operations(String datasource) {
        try {
            lines = Files.readAllLines(Path.of(datasource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long sumOperations(int numberOfOperators) {
        long sum = 0;
        for (String line : lines) {
            String[] split = line.split(":");
            sum += checkLine(Long.parseLong(split[0]),
                    Arrays.stream(split[1].trim().split("\\s+"))
                            .map(Long::parseLong)
                            .toList(),
                    numberOfOperators);
        }
        return sum;
    }


    private long checkLine(long expectedResult, List<Long> numbers, int numberOfOperators) {
        for (int mask = 0; mask < Math.pow(numberOfOperators, numbers.size() - 1); mask++) {
            long result = count(numbers, mask, numberOfOperators);
            if (result == expectedResult) {
                return result;
            }
        }
        return 0;
    }


    private long count(List<Long> numbers, int mask, int numberOfOperators) {
        char[] signs = readMask(mask, numbers.size(), numberOfOperators).toCharArray();
        long result = numbers.get(0);
        int numberIndex = 1;
        for (int sign : signs) {
            if (sign == '0') {
                result += numbers.get(numberIndex);
            } else if (sign == '1') {
                result *= numbers.get(numberIndex);
            } else {
                result = Long.parseLong(String.valueOf(result) + numbers.get(numberIndex));
            }
            numberIndex++;
        }
        return result;
    }

    private String readMask(int mask, int arraySize, int numberOfOperators) {
        StringBuilder maskString = new StringBuilder(Integer.toString(mask, numberOfOperators));
        for (int i = maskString.length(); i < arraySize - 1; i++) {
            maskString.insert(0, "0");
        }
        return maskString.toString();
    }
}
