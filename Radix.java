public class Radix {

    private static final int[] pow10 = { // lookup table for O(1) pow
        1, 10, 100, 1000,
        10000, 100000, 1000000, 10000000,
        100000000, 1000000000
    };

    public static int nth(int n, int col) {
        return Math.abs(n / pow10[col] % 10);
    }

}
