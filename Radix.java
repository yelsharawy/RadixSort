public class Radix {
    
    private static final int[] pow10 = {  // lookup table for O(1) pow
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
    
    public static void merge(SortableLinkedList original, SortableLinkedList[] buckets) {
        for (SortableLinkedList bucket : buckets) {
            original.extend(bucket);
        }
    }
    
    public static void radixSortSimple(SortableLinkedList data) {
        int maxLength = 0;
        for (int i = 0; i < data.size(); i++) {
            int value = data.remove(0);  // for O(n) traversal
            maxLength = Math.max(maxLength, length(value));
            data.add(value);
        }
        
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new SortableLinkedList();
        }
        
        for (int i = 0; i < maxLength; i++) {
            while (data.size() > 0) {
                int value = data.remove(0);  // for O(n) traversal & clearing
                int digit = nth(value, i);
                buckets[digit].add(value);
            }
            merge(data, buckets);
        }
        
    }
    
    public static void radixSort(SortableLinkedList data) {
        radixSortSimple(data);
        SortableLinkedList negatives = new SortableLinkedList();
        SortableLinkedList positives = new SortableLinkedList();
        
        while (data.size() > 0) {
            int value = data.remove(0);  // for O(n) traversal & clearing
            if (value < 0) {
                negatives.add(0, value);  // reversed
            } else {
                positives.add(value);
            }
        }
        data.extend(negatives);
        data.extend(positives);
    }
    
}
