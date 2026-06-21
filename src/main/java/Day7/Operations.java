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

    public long sumOperations(){
        long sum = 0;
        for(String line: lines){
            sum += checkLine(Long.parseLong(line.split(":")[0]),
                    Arrays.stream(line.split(":")[1].trim().split("\\s+"))
                    .map(Long::parseLong)
                    .toList());
        }
        return sum;
    }


    private long checkLine(long expectedResult, List<Long> numbers) {
        for (int mask = 0; mask < Math.pow(2, numbers.size() - 1); mask++) {
            long result = count(numbers, mask);
            if (result == expectedResult) {
                return result;
            }
        }
        return 0;
    }


    private long count(List<Long> numbers, int mask) {
        char[] signs = readMask(mask, numbers.size()).toCharArray();
        long result = numbers.get(0);
        int numberIndex = 1;
        for (int sign : signs) {
            if (sign == '0') {
                result += numbers.get(numberIndex);
            } else {
                result *= numbers.get(numberIndex);
            }
            numberIndex++;
        }
        return result;
    }

    private String readMask(int mask, int arraySize){
        StringBuilder maskString = new StringBuilder(Integer.toBinaryString(mask));
        for(int i = maskString.length(); i < arraySize - 1; i++){
            maskString.insert(0, "0");
        }
        return maskString.toString();
    }
}
