package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Updates {
    private final Map<Integer, List<Integer>> rulesMap = new HashMap<>();
    private final List<List<Integer>> updates = new ArrayList<>();

    public Updates(Path datasource) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(datasource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            if (line.contains("|")) {
                rulesMap.computeIfAbsent(
                        Integer.parseInt(line.split("\\|")[0]),
                        k -> new ArrayList<>()).add(Integer.parseInt(line.split("\\|")[1]));
            } else if (line.contains(",")) {
                List<Integer> update = new ArrayList<>();
                for (String number : line.split(",")) {
                    update.add(Integer.parseInt(number));
                }
                updates.add(update);
            }
        }
        System.out.println("");
    }

    private int checkUpdate(List<Integer> update) {
        for (int i = 1; i < update.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (rulesMap.get(update.get(i)) != null) {
                    if (rulesMap.get(update.get(i)).contains(update.get(j))) {
                        return 0;
                    }
                }
            }
        }
        return update.get(update.size() / 2);
    }

    public int sumAllUpdates() {
        int sum = 0;
        for (List<Integer> update : updates) {
            sum += checkUpdate(update);
        }
        return sum;
    }
}
