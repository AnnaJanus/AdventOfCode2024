import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3b {
    public static void main(String[] args) throws IOException {
        String data = Files.readString(Path.of("src/test/java/Day3Data.txt"));

        data = data.replace("\n", "");

        String mulRegex = "mul\\([0-9]+,[0-9]+\\)";
        Pattern mulPattern = Pattern.compile(mulRegex);

        // get from the beginning to don't()
        // from don't() to do()
        // from do() to don't()
        // and from do() to the end
        String splitter = "^.*?(?=don't\\(\\))" +
                "|don't\\(\\).*?(?=do\\(\\))" +
                "|do\\(\\).*?(?=don't\\(\\))" +
                "|do\\(\\).*";
        Pattern splitPattern = Pattern.compile(splitter);
        Matcher splitMatcher = splitPattern.matcher(data);

        int result = 0;

        while (splitMatcher.find()) {
            if (!splitMatcher.group().startsWith("don't()")) {
                Matcher mulMatcher = mulPattern.matcher(splitMatcher.group());
                while (mulMatcher.find()) {
                    String[] numbers = mulMatcher.group().split("(mul\\()|\\)|,");
                    result += Integer.parseInt(numbers[1]) * Integer.parseInt(numbers[2]);
                }
            }
        }
        System.out.println(result);
    }
}
