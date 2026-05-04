package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory {
    private final String MUL_EXPRESSION_REGEX = "mul\\([0-9]+,[0-9]+\\)";
    private final String MUL_SPLIT_REGEX = "(mul\\()|\\)|,";
    // get from the beginning to don't()
    // from don't() to do()
    // from do() to don't()
    // and from do() to the end
    private final String ACTIVE_CODE_REGEX = "^.*?(?=don't\\(\\))" +
            "|don't\\(\\).*?(?=do\\(\\))" +
            "|do\\(\\).*?(?=don't\\(\\))" +
            "|do\\(\\).*";

    private final Pattern MUL_EXPRESSION_PATTERN = Pattern.compile(MUL_EXPRESSION_REGEX);
    private final Pattern MUL_SPLIT_PATTERN = Pattern.compile(ACTIVE_CODE_REGEX);
    private String data;


    public Memory(String datasource) {
        try {
            data = Files.readString(Path.of(datasource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = data.replace("\n", "");
    }

    public int sumAll() {
        int result = 0;
        Matcher expressionMatcher = MUL_EXPRESSION_PATTERN.matcher(data);
        while (expressionMatcher.find()) {
            String[] numbers = expressionMatcher.group().split(MUL_SPLIT_REGEX);
            result += Integer.parseInt(numbers[1]) * Integer.parseInt(numbers[2]);
        }
        return result;
    }

    public int sumEnabled() {
        int result = 0;
        Matcher splitMatcher = MUL_SPLIT_PATTERN.matcher(data);
        while (splitMatcher.find()) {
            if (!splitMatcher.group().startsWith("don't()")) {
                Matcher mulMatcher = MUL_EXPRESSION_PATTERN.matcher(splitMatcher.group());
                while (mulMatcher.find()) {
                    String[] numbers = mulMatcher.group().split(MUL_SPLIT_REGEX);
                    result += Integer.parseInt(numbers[1]) * Integer.parseInt(numbers[2]);
                }
            }
        }
        return result;
    }
}
