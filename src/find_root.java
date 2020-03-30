import java.util.Scanner;
import java.lang.Math;

public class find_root {

    public static int a, b, c, d, e, f;
    public static double EPSILON = Math.pow(10, -12);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        a = scan.nextInt();
        b = scan.nextInt();
        c = scan.nextInt();
        d = scan.nextInt();
        e = scan.nextInt();
        f = scan.nextInt();

        double low = 0, high = 1;
        double mid = (low + high) / 2;

        double eval = evaluate(mid);
        double eLow, eHigh;
        eLow = evaluate(low);
        eHigh = evaluate(high);
        if (eLow == 0.) {
            System.out.println("0.0000");
            return;
        }
        if (eHigh == 0.) {
            System.out.println("1.0000");
            return;
        }
        while (Math.abs(eval) > EPSILON) {
            if (eLow * eHigh > 0.) {
                System.out.println("No solution");
                return;
            }
            if (eLow * eval > 0.) {
                low = mid;
                eLow = eval;
            } else {
                high = mid;
                eHigh = eval;
            }
            mid = (low + high) / 2;
            eval = evaluate(mid);
        }
        System.out.printf("%.4f\n", mid);
    }

    public static double evaluate(double x) {
        return (a*Math.exp(-1 * x) + b*Math.sin(x) + c*Math.cos(x) + d*Math.tan(x) + e*Math.pow(x, 2) + f);
    }
}
