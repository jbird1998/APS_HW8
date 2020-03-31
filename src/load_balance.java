import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class load_balance {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int M = Integer.parseInt(tokens.nextToken());
        int N = Integer.parseInt(tokens.nextToken());

        int[] nodes = new int[M];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(N, new maxComp());
        tokens = new StringTokenizer(reader.readLine());
        int sum = 0;
        int element;
        while (tokens.hasMoreTokens()) {
            element = Integer.parseInt(tokens.nextToken());
            sum += element;
            maxHeap.offer(element);
        }
        double avg = (double)sum / M;
        int count = 0;
        while (!maxHeap.isEmpty()) {
            element = maxHeap.poll();
            if (count < M) {
                nodes[count] += element;
            } else {
                nodes[M - 1 - (count % M)] += element;
            }
            count++;
        }
        double imb = 0.;
        for (int i = 0; i < M; i++) {
            imb += Math.abs(nodes[i] - avg);
        }
        System.out.printf("IMBALANCE = %.5f\n", imb);
    }

    public static class maxComp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return -1;
            } else if (o1 == o2) {
                return 0;
            }
            return 1;
        }
    }
}
