package com.example.jmhsample;

/**
 * Class containing sorting methods
 */
public class Sorter {
    // Bubble Sort Method for Descending Order
    public static void bubbleSort(int[] b) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int c = 0; c < b.length - 1; c++) {
                if (b[c] > b[c + 1]) {
                    int t = b[c];
                    b[c] = b[c + 1];
                    b[c + 1] = t;
                    changed = true;
                }
            }
        }
    }
}
