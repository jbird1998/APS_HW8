import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class party_fee {

    static int[] rank;
    static int[] sets;
    static int[] owed;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        sets = new int[N];
        rank = new int[N];
        owed = new int[N];
        for (int i = 0; i < N; i++) {
            sets[i] = i;
            owed[i] = Integer.parseInt(reader.readLine());
        }
        int a, b;
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(tokens.nextToken());
            b = Integer.parseInt(tokens.nextToken());
            union(a, b);
        }
        for (int i = 0; i < N; i++) {
            if (owed[i] != 0) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
        System.out.println("POSSIBLE");
    }

    public static int find(int i) {
        // Returns the index
        if (sets[i] == i) {
            return i;
        }
        sets[i] = find(sets[i]);
        return sets[i];
    }

    public static void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);
        if (aP == bP) {
            return;
        }
        if (rank[aP] < rank[bP]) {
            sets[aP] = bP;
            owed[bP] += owed[aP];
            owed[aP] = 0;
        } else if (rank[bP] < rank[aP]) {
            sets[bP] = aP;
            owed[aP] += owed[bP];
            owed[bP] = 0;
        } else {
            sets[bP] = aP;
            owed[aP] += owed[bP];
            owed[bP] = 0;
            rank[aP] += 1;
        }
    }
}
