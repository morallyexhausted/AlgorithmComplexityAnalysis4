import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class SortedArray {
    private List<Integer> data;

    public SortedArray(List<Integer> initial) {
        this.data = new ArrayList<>(initial);
        Collections.sort(this.data);
    }

    public void add(int x) {
        int index = Collections.binarySearch(data, x);
        if (index < 0) {
            index = -index - 1;
        }
        data.add(index, x);
    }

    public boolean contains(int x) {
        return Collections.binarySearch(data, x) >= 0;
    }

    public boolean remove(int x) {
        int index = Collections.binarySearch(data, x);
        if (index >= 0) {
            // Удаляем первое попавшееся вхождение (или конкретное найденное)
            data.remove(index);
            return true;
        }
        return false;
    }

    public int countRange(int l, int r) {
        int leftIdx = lowerBound(l);
        int rightIdx = upperBound(r);
        return rightIdx - leftIdx;
    }

    private int lowerBound(int val) {
        int low = 0, high = data.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (data.get(mid) >= val) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int upperBound(int val) {
        int low = 0, high = data.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (data.get(mid) > val) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i));
            if (i < data.size() - 1) sb.append(" ");
        }
        return sb.toString();
    }
}

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        List<Integer> initial = new ArrayList<>();
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            initial.add(Integer.parseInt(tokenizer.nextToken()));
        }

        SortedArray sortedArray = new SortedArray(initial);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            String line = reader.readLine().trim();
            Stringparts parts = parseCommand(line);

            switch (parts.command) {
                case "ADD":
                    sortedArray.add(parts.arg1);
                    break;
                case "CONTAINS":
                    sb.append(sortedArray.contains(parts.arg1) ? "YES" : "NO").append("\n");
                    break;
                case "REMOVE":
                    sortedArray.remove(parts.arg1);
                    break;
                case "COUNT_RANGE":
                    sb.append(sortedArray.countRange(parts.arg1, parts.arg2)).append("\n");
                    break;
                case "PRINT":
                    sb.append(sortedArray.print()).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }

    private static class Stringparts {
        String command;
        int arg1, arg2;
        Stringparts(String cmd, int a1, int a2) {
            this.command = cmd; this.arg1 = a1; this.arg2 = a2;
        }
    }

    private static Stringparts parseCommand(String line) {
        String[] parts = line.split("\\s+");
        String cmd = parts[0];
        int a1 = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        int a2 = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
        return new Stringparts(cmd, a1, a2);
    }
}