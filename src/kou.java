import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class kou {

    public static void main(String[] args) throws IOException {
        //TODO: Optimize. Perhaps a LinkedList implementation?
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        System.out.println(swaps(arr, N));
    }

    public static long swaps(int[] arr, int N) {
        int[] tempArr = new int[N];
        return mergeSortAndCount(arr, tempArr, 0, N - 1);
    }

    public static long mergeSortAndCount(int[] arr, int[] tempArr, int left, int right) {
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

    public static long mergeAndCount(int[] arr, int[] tempArr, int left, int mid, int right) {
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

}
