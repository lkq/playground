package com.github.lkq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

class SortedSubArrayTest {
    private static Logger logger = LoggerFactory.getLogger(SortedSubArrayTest.class);

    public static final int SIZE = 100;
    private final Random random = new Random();
    private SortedSubArray sortedSubArray;
    public static final int PAGE_SIZE = 10;

    @BeforeEach
    void setUp() {
        random.setSeed(System.nanoTime());
        sortedSubArray = new SortedSubArray();
    }

    @Test
    void canSortAllPageRange() {
        int pages = (SIZE + PAGE_SIZE - 1) / PAGE_SIZE;
        for (int i = 0; i < pages; i++) {
            logger.info("testing page {}", i);
            runTest(createRandomArray(SIZE), i * PAGE_SIZE, PAGE_SIZE);
        }
    }

    @Test
    void canSortLastWholePage() {
        runTest(createRandomArray(SIZE), SIZE - PAGE_SIZE, PAGE_SIZE);
    }

    @Test
    void canSortLastHalfPage() {
        runTest(createRandomArray(SIZE), SIZE - PAGE_SIZE / 2, PAGE_SIZE);
    }

    @Test
    void canSortHalfPage() {
        runTest(createRandomArray(SIZE), 0, PAGE_SIZE * 2);
    }

    private void runTest(int[] data, int startIndex, int size) {
        int[] sortedData = sortedSubArray.sort(data, startIndex, size);
        reverseSort(sortedData);
        reverseSort(data);
        int[] expected = Arrays.copyOfRange(data, startIndex, startIndex + size);
        Assertions.assertArrayEquals(expected, sortedData);
    }

    private int[] createRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 100);
        }
        return array;
    }

    private void reverseSort(int[] data) {
        Arrays.sort(data);
        int tmp;
        int length = data.length - 1;
        for (int i = 0; i < (data.length + 1) / 2; i++) {
            tmp = data[i];
            data[i] = data[length - i];
            data[length - i] = tmp;
        }
    }
}