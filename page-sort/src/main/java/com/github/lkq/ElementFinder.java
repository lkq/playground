package com.github.lkq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ElementFinder {
    private static Logger logger = LoggerFactory.getLogger(ElementFinder.class);
    static class CountResult {
        public int larger;
        public int smaller;
        public int equals;

        @Override
        public String toString() {
            return "[" +
                    "larger=" + larger +
                    ", smaller=" + smaller +
                    ", equals=" + equals +
                    ']';
        }
    }

    public int largest(int[] data, int nth) {

        logger.info("getting {}th from {}", nth, formatArray(data, data.length));
        int[] sortData = Arrays.copyOf(data, data.length);
        int length = data.length;
        int pivot;
        CountResult counts;
        while (true) {
            pivot = findPivot(sortData, length);
            counts = countElements(pivot, sortData, length);
            logger.info("pivot: {}, {}, length: {}, nth: {}, array: {}", pivot, counts, length, nth, formatArray(sortData, length));
            if (nth < counts.larger) {
                length = partition(pivot, sortData, length, true);
            } else if (nth < (counts.larger + counts.equals)){
                logger.info("found {}", pivot);
                return pivot;
            } else {
                nth -= (counts.larger + counts.equals);
                length = partition(pivot, sortData, length, false);
            }

        }
    }

    private String formatArray(int[] array, int length) {
        StringBuilder msg = new StringBuilder();
        length = Math.max(length, array.length);
        for (int i = 0; i < length; i++) {
            msg.append(array[i]).append(" ");
        }
        return msg.toString();
    }

    private CountResult countElements(int pivot, int[] sortData, int length) {
        CountResult counts = new CountResult();
        for (int i = 0; i < length; i++) {
            if (sortData[i] > pivot) {
                counts.larger++;
            } else if (sortData[i] == pivot) {
                counts.equals++;
            } else {
                counts.smaller++;
            }
        }
        return counts;
    }

    private int partition(int pivot, int[] sortData, int length, boolean keepLarger) {
        int tmp;
        int pivotPos = 0;
        for (int i = 0; i < length; i++) {
            if ((keepLarger && sortData[i] > pivot) ||
                    (!keepLarger && sortData[i] < pivot)) {
                tmp = sortData[i];
                sortData[i] = sortData[pivotPos];
                sortData[pivotPos++] = tmp;
            }
        }
        return pivotPos;
    }

    private int findPivot(int[] sortList, int length) {
        return sortList[0];
    }

    public int findLargest(int[] data, int nth) {
        return 0;
    }

    public int findSmallest(int[] data, int nth) {
        return data[nth];
    }
}
