package com.github.lkq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * given an unsorted array, get the sorted sub array begin from the nth element with the required size
 * the algorithm contains 3 parts:
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

    private static Logger logger = LoggerFactory.getLogger(SortedSubArray.class);
    private ElementFinder elementFinder = new ElementFinder();

    public int[] sort(int[] data, int nth, int size) {
        long start = System.currentTimeMillis();
        SortedQueue sortedData = new SortedQueue(size);

        // get the nth largest element
        int largest = elementFinder.largest(data, nth);
        for (int value : data) {
            if (value <= largest) {
                sortedData.insert(value);
            }
        }

        logger.info("sorted {} elements out of {} in {} ms", size, data.length, System.currentTimeMillis() - start);
        return sortedData.get();
    }

}
