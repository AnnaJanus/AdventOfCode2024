package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DiskMap {
    public long checksum;

    public DiskMap(String datasource) {
        String input = "";
        try {
            input = Files.readString(Path.of(datasource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> blocks = formatInputIntoBlocks(input);
        List<Integer> blocksWithoutGaps = removeGaps(blocks);
        checksum = countChecksum(blocksWithoutGaps);
    }

    private List<Integer> formatInputIntoBlocks(String diskMap) {
        int fileId = 0;
        List<Integer> blocks = new ArrayList<>();
        for (int i = 0; i < diskMap.length(); i++) {
            int length = diskMap.charAt(i) - '0';
            int value = (i % 2 == 0) ? fileId++ : -1;
            for (int j = 0; j < length; j++) {
                blocks.add(value);
            }
        }
        return blocks;
    }

    private List<Integer> removeGaps(List<Integer> blocks) {
        List<Integer> movedBlocks = new ArrayList<>(blocks);
        while (movedBlocks.indexOf(-1) < getLastIndexOfPositiveNumber(movedBlocks)) {
            int freeSpaceIndex = movedBlocks.indexOf(-1);
            for (int j = movedBlocks.size() - 1; j >= 0; j--) {
                if (movedBlocks.get(j) != -1) {
                    movedBlocks.set(freeSpaceIndex, movedBlocks.get(j));
                    movedBlocks.set(j, -1);
                    break;
                }
            }
        }
        return movedBlocks;
    }

    private int getLastIndexOfPositiveNumber(List<Integer> list) {
        int lastIndex = -1;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != -1) {
                lastIndex = i;
                break;
            }
        }
        return lastIndex;
    }

    private long countChecksum(List<Integer> list) {
        int index = 0;
        long sum = 0;
        while (list.get(index) != -1) {
            sum += (long) index * list.get(index);
            index++;
        }
        return sum;
    }
}
