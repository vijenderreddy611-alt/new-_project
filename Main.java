import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            long k = sc.nextLong();

            long[] sum = new long[n];
            boolean[] active = new boolean[n];

            long lo = Long.MAX_VALUE;
            long limit = Long.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                long a = sc.nextLong();
                long b = sc.nextLong();
                long c = sc.nextLong();

                sum[i] = a + b + c;
                active[i] = !(a == b && b == c);

                lo = Math.min(lo, sum[i]);

                if (!active[i])
                    limit = Math.min(limit, sum[i]);
            }

            long hi = lo + k;

            // Equal triples can never be increased
            hi = Math.min(hi, limit);

            while (lo <= hi) {
                long mid = lo + (hi - lo) / 2;

                long need = 0;

                for (int i = 0; i < n; i++) {
                    if (active[i] && sum[i] < mid) {
                        need += mid - sum[i];

                        if (need > k)
                            break;
                    }
                }

                if (need <= k)
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }

            System.out.println(hi);
        }
    }
}