import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        String data = Files.readString(Path.of("src/test/java/Day3Data.txt"));

        String mulRegex = "mul\\([0-9]+,[0-9]+\\)";
        Pattern mulPattern = Pattern.compile(mulRegex);
        Matcher mulMatcher = mulPattern.matcher(data);

        int result = 0;

        while (mulMatcher.find()) {
            String[] numbers = mulMatcher.group().split("(mul\\()|\\)|,");
            result += Integer.parseInt(numbers[1]) * Integer.parseInt(numbers[2]);
        }

        System.out.println(result);

    }
}
