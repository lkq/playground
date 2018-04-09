package com.github.lkq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class SortedQueueTest {
    public static final int CAPACITY = 100;
    SortedQueue sortedQueue;
    Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
        random.setSeed(System.nanoTime());
    }

    @Test
    void canInsertAndSort() {
        int[] expected = new int[CAPACITY];
        sortedQueue = new SortedQueue(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            int value = random.nextInt(CAPACITY * 100);
            expected[i] = value;
            sortedQueue.insert(value);
        }

        Arrays.sort(expected);

        Assertions.assertArrayEquals(expected, sortedQueue.get());

    }
}