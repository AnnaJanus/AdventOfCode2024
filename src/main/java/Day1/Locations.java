package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Locations {
    private final List<Integer> leftColumn = new ArrayList<>();
    private final List<Integer> rightColumn = new ArrayList<>();

    public Locations(Path datasource) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(datasource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            this.leftColumn.add(Integer.valueOf(line.split(" +")[0]));
            this.rightColumn.add(Integer.valueOf(line.split(" +")[1]));
        }
    }

    public int sumDistances() {
        List<Integer> sortedLeft = new ArrayList<>(leftColumn);
        Collections.sort(sortedLeft);
        List<Integer> sortedRight = new ArrayList<>(rightColumn);
        Collections.sort(sortedRight);
        int sum = 0;
        for (int i = 0; i < sortedLeft.size(); i++) {
            sum += Math.abs(sortedLeft.get(i) - sortedRight.get(i));
        }
        return sum;
    }

    public int similarity(){
        int score = 0;
        for(int i : leftColumn){
           score += i * Collections.frequency(rightColumn,i);
        }
        return score;
    }
}
