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
    private int sumCorrect = 0;
    private int sumIncorrect = 0;

    public Updates(Path datasource) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(datasource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        readUpdatesAndRules(lines);
    }

    public int getSumCorrect() {
        return sumCorrect;
    }

    public int getSumIncorrect() {
        return sumIncorrect;
    }

    private void readUpdatesAndRules(List<String> lines) {
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
    }

    private boolean checkUpdate(List<Integer> update) {
        for (int i = 1; i < update.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (rulesMap.get(update.get(i)) != null
                        && rulesMap.get(update.get(i)).contains(update.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void sumAllUpdates() {

        for (List<Integer> update : updates) {
            if (checkUpdate(update)) {
                sumCorrect += update.get(update.size() / 2);
            } else {
                List<Integer> ordered = orderUpdate(update);
                sumIncorrect += ordered.get(update.size() / 2);
            }
        }
    }

    private List<Integer> orderUpdate(List<Integer> update) {
        List<Integer> orderedUpdate = new ArrayList<>();
        orderedUpdate.add(update.get(0));
        for (int i = 1; i < update.size(); i++) {
            boolean isAdded = false;
            for (int j = 0; j < i; j++) {
                if (rulesMap.get(update.get(i)) != null
                        && rulesMap.get(update.get(i)).contains(orderedUpdate.get(j))) {
                    orderedUpdate.add(j, update.get(i));
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) orderedUpdate.add(update.get(i));
        }
        return orderedUpdate;
    }
}
