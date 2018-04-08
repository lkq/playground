package com.github.lkq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class PageSortTest {

    private PageSort pageSort;
    private List<Integer> unsortedList;

    @BeforeEach
    void setUp() {
        pageSort = new PageSort();
        unsortedList = createUnsortedList(1000);
    }

    @Test
    void canSortFirstPage() {
        runTest(0, 99);
    }

    @Test
    void canSortPageInTheMiddle() {
        runTest(500, 599);
    }

    @Test
    void canSortLastPage() {
        runTest(900, 999);
    }

    @Test
    void canSortLastHalfPage() {
        runTest(900, 1099);
    }

    private void runTest(int startIndex, int endIndex) {
        ArrayList<Integer> sortedList = new ArrayList<>(unsortedList);
        sortedList.sort(Comparator.naturalOrder());
        List<Integer> sortedData = pageSort.sort(unsortedList, startIndex, endIndex);

        Integer[] expected = sortedList.subList(startIndex, endIndex).toArray(new Integer[0]);
        Integer[] actual = sortedData.toArray(new Integer[0]);
        Assertions.assertArrayEquals(actual, expected);
    }

    private List<Integer> createUnsortedList(int size) {
        List<Integer> unsortedList = new ArrayList<>(size);
        for (Integer index : unsortedList) {
            unsortedList.add((int) (Math.random() * size * 100));
        }
        return unsortedList;
    }
}