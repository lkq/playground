package com.github.lkq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class PageSortTest {

    public static final int SIZE = 1000;
    private final Random random = new Random();
    private PageSort pageSort;
    public static final int PAGE_SIZE = 100;

    @BeforeEach
    void setUp() {
        random.setSeed(System.nanoTime());
        pageSort = new PageSort();
    }

    @Test
    void canSortAllPageRange() {
        int pages = (SIZE + PAGE_SIZE - 1) / PAGE_SIZE;
        for (int i = 0; i < pages; i++) {
            runTest(createRandomArray(SIZE), i * PAGE_SIZE, (i + 1) * PAGE_SIZE);
        }
    }

    @Test
    void canSortLastWholePage() {
        runTest(createRandomArray(SIZE), SIZE - PAGE_SIZE, SIZE);
    }

    @Test
    void canSortLastHalfPage() {
        runTest(createRandomArray(SIZE), SIZE - PAGE_SIZE / 2, SIZE + PAGE_SIZE / 2);
    }

    private void runTest(int[] data, int startIndex, int endIndex) {

        int[] sortedData = pageSort.sort(data, startIndex, endIndex);

        Arrays.sort(data);
        int[] expected = Arrays.copyOfRange(data, startIndex, endIndex);
        Assertions.assertArrayEquals(sortedData, expected);
    }

    private int[] createRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 100);
        }
        return array;
    }
}