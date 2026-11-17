import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        int[] arr = new int[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int x = Integer.parseInt(reader.readLine().trim());
            int result = findLastNotGreaterThan(arr, x);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static int findLastNotGreaterThan(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int ansIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= x) {
                ansIndex = mid;
                low = mid + 1; // Ищем правее, вдруг есть еще подходящие
            } else {
                high = mid - 1;
            }
        }

        return (ansIndex == -1) ? -1 : arr[ansIndex];
    }
}