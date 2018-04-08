package com.github.lkq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElementFinderTest {
    private static Logger logger = LoggerFactory.getLogger(ElementFinderTest.class);

    private final Random random = new Random();
    private final int SIZE = 1000000;
    private ElementFinder elementFinder;

    @BeforeEach
    void setUp() {
        random.setSeed(System.nanoTime());
        this.elementFinder = new ElementFinder();
    }

    @Test
    void canFindTheLargestElement() {
        int[] data = createRandomArray(SIZE);
        long startTime = System.currentTimeMillis();
        int largest = elementFinder.largest(data, 0);
        logger.info("found largest element in {} ms", System.currentTimeMillis() - startTime);
        for (int value : data) {
            assertTrue(value <= largest);
        }
    }

    @Test
    void canFindLargestNthElement() {
        int[] data = createRandomArray(SIZE);
        int nth = random.nextInt(SIZE);
        int nthValue = elementFinder.largest(data, nth);

        long startTime = System.currentTimeMillis();
        Arrays.sort(data);
        logger.info("Arrays.sort completed in {} ms", System.currentTimeMillis() - startTime);
        assertThat(nthValue, is(data[data.length - nth - 1]));
    }

    private int[] createRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 100);
        }
        return array;
    }
}