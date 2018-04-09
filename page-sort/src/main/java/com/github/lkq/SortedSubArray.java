package com.github.lkq;

/**
 * given an unsorted array, get the sorted sub array begin from the nth element with the required size
 * the algorithm contains 2 parts:
 * 1. find out the nth largest element.
 *      use a partition by pivot algorithm
 *      complexity: O(log(n)) - O(n), depending on the pivot selection algorithm
 * 2. scan through the array
 *      put every element smaller than the nth element into a sorted queue
 *      complexity: O(n)
 * 3. sorted queue
 *      the sorted queue will dispose the smallest element when a new element is inserted
 *      complexity: O(n) with constant factor of log(size)
 *
 *  overall complexity: O(n)
 */
public class SortedSubArray {

    private ElementFinder elementFinder = new ElementFinder();

    public int[] sort(int[] data, int nth, int size) {
        SortedQueue sortedData = new SortedQueue(size);

        int largest = elementFinder.largest(data, nth);
        for (int value : data) {
            if (value <= largest) {
                sortedData.insert(value);
            }
        }

        return sortedData.get();
    }

}
