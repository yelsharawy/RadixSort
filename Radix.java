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
        return Math.max(1, (int)Math.log10(Math.abs(n)) + 1);
    }
    
    public static void merge(SortableLinkedList original, SortableLinkedList[] buckets) {
        for (SortableLinkedList bucket : buckets) {
            original.extend(bucket);
        }
    }
    
    public static void radixSortSimple(SortableLinkedList data) {
        int maxLength = 1;
        
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new SortableLinkedList();
        }
        
        while (data.size() > 0) {
            int value = data.remove(0);
            maxLength = Math.max(maxLength, length(value));
            int digit = nth(value, 0);
            buckets[digit].add(value);
        }
        merge(data, buckets);
        
        for (int i = 1; i < maxLength; i++) {
            while (data.size() > 0) {
                int value = data.remove(0);
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
            int value = data.remove(0);
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
