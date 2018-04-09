package com.github.lkq;

/**
 * given an unsorted array, get the sorted sub array begin from the nth element with the required size
 */
public class SortedSubArray {

    private ElementFinder elementFinder = new ElementFinder();

    public int[] sort(int[] data, int nth, int size) {
        SortList sortedData = new SortList(size);

        int largest = elementFinder.largest(data, nth);
        for (int value : data) {
            if (value <= largest) {
                sortedData.insert(value);
            }
        }

        return sortedData.get();
    }

}
