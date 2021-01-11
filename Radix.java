public class Radix {

    private static final int[] pow10 = { // lookup table for O(1) pow
        1, 10, 100, 1000,
        10000, 100000, 1000000, 10000000,
        100000000, 1000000000
    };

    public static int nth(int n, int col) {
        return Math.abs(n / pow10[col] % 10);
    }

    public static int length(int n) {
        /* This "Math" approach is ***WAY*** too slow. I timed it.
         * While mine can do all possible integer values in 1.5 s,
         * this one took over 70 seconds before I killed it.
         *
         * EDIT: I noticed this was probably due to branch prediction,
         * so I tested it again with random values. Sure enough,
         * the speeds were much more comparable, but my approach
         * was still clearly faster.
        return n == 0 ? 1 : (int)(Math.log10(Math.abs(n)) + 1);
        */
        n = Math.abs(n);
        for (int i = 1; i < pow10.length; i++) {
            if (n < pow10[i]) {
                return i;
            }
        }
        return pow10.length;
    }

    public static void merge(MyLinkedList original, MyLinkedList[] buckets) {
        for (MyLinkedList bucket : buckets) {
            original.extend(bucket);
        }
    }

}
