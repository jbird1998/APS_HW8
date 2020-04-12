import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class nucleic_acids {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        PriorityQueue<Acid> heap = new PriorityQueue<>(M, new acidComp());

        String acidRep;
        char[] chars;
        long inv;
        for (int i = 0; i < M; i++) {
            acidRep = reader.readLine();
            chars = acidRep.toCharArray();
            inv = inversions(chars, N);
            heap.offer(new Acid(acidRep, i, inv));
        }

        StringBuilder builder = new StringBuilder(M*(N + 5));
        while (!heap.isEmpty()) {
            Acid a = heap.poll();
            builder.append(a.representation);
            if (!heap.isEmpty()) {
                builder.append('\n');
            }
        }
        System.out.println(builder.toString());
    }

    public static long inversions(char[] arr, int N) {
        char[] tempArr = new char[N];
        return mergeSortAndCount(arr, tempArr, 0, N - 1);
    }

    public static long mergeSortAndCount(char[] arr, char[] tempArr, int left, int right) {
        int mid;
        long count = 0L;
        if (right > left) {
            mid = (left + right) / 2;
            count = mergeSortAndCount(arr, tempArr, left, mid);
            count += mergeSortAndCount(arr, tempArr, mid + 1, right);
            count += mergeAndCount(arr, tempArr, left, mid, right);
        }
        return count;
    }

    public static long mergeAndCount(char[] arr, char[] tempArr, int left, int mid, int right) {
        long count = 0L;
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tempArr[k] = arr[i];
                k++;
                i++;
            } else {
                tempArr[k] = arr[j];
                k++;
                j++;
                count += (mid + 1) - i;
            }
        }
        while (i <= mid) {
            tempArr[k] = arr[i];
            k++;
            i++;
        }
        while (j <= right) {
            tempArr[k] = arr[j];
            k++;
            j++;
        }
        for (int l = left; l <= right; l++) {
            arr[l] = tempArr[l];
        }
        return count;
    }

    public static class Acid {

        public String representation;
        public int order;
        public long inversionCount;

        public Acid(String r, int o, long i) {
            this.representation = r;
            this.order = o;
            this.inversionCount = i;
        }
    }

    public static class acidComp implements Comparator<Acid> {

        @Override
        public int compare(Acid o1, Acid o2) {
            if (o1.inversionCount < o2.inversionCount) {
                return -1;
            } else if (o1.inversionCount == o2.inversionCount) {
                return Integer.compare(o1.order, o2.order);
            }
            return 1;
        }
    }

}
