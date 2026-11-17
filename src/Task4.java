import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Request {
    int priority;
    String id;

    public Request(int priority, String id) {
        this.priority = priority;
        this.id = id;
    }

    @Override
    public String toString() {
        return "(" + priority + ", " + id + ")";
    }
}

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());

        List<Request> queue = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int priority = Integer.parseInt(tokenizer.nextToken());
            String id = tokenizer.nextToken();

            Request newReq = new Request(priority, id);

            // Ищем позицию для вставки с сохранением порядка для равных приоритетов (stable insertion)
            int insertIdx = queue.size();
            for (int j = 0; j < queue.size(); j++) {
                if (queue.get(j).priority > priority) {
                    insertIdx = j;
                    break;
                }
            }
            queue.add(insertIdx, newReq);

            // Вывод текущей очереди после добавления заявки
            for (int j = 0; j < queue.size(); j++) {
                sb.append(queue.get(j));
                if (j < queue.size() - 1) sb.append(", ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}