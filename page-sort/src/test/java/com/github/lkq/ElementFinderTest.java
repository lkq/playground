package com.github.lkq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElementFinderTest {

    private final Random random = new Random();
    private final int SIZE = 10;
    private ElementFinder elementFinder;

    @BeforeEach
    void setUp() {
        random.setSeed(System.currentTimeMillis());
        this.elementFinder = new ElementFinder();
    }

    @Test
    void canFindTheLargestElement() {
        int[] data = createRandomArray(SIZE);
        int largest = elementFinder.largest(data, 0);
        for (int value : data) {
            assertTrue(value <= largest);
        }
    }

    @Test
    void canFindLargestNthElement() {
        int[] data = createRandomArray(SIZE);
        int nth = random.nextInt(SIZE);
        int nthValue = elementFinder.largest(data, nth);
        Arrays.sort(data);
        assertThat(nthValue, is(data[data.length - nth - 1]));
    }

    @Test
    void canFindTheSmallestElement() {
        int[] data = createRandomArray(1000);
        int smallest = elementFinder.findSmallest(data, 0);
        for (int value : data) {
            assertTrue(value >= smallest);
        }
    }

    @Test
    void canFindSmallestNthElement() {
        int[] data = createRandomArray(SIZE);
        int nth = random.nextInt(SIZE);
        int nthValue = elementFinder.findSmallest(data, nth);
        Arrays.sort(data);
        assertThat(nthValue, is(data[SIZE - nth - 1]));
    }

    private int[] createRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 100);
        }
        return array;
    }
}