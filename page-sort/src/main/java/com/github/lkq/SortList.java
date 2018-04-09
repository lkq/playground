package com.github.lkq;

import java.util.Arrays;

public class SortList {
    private int capacity;
    private int[] data;

    SortList(int capacity) {
        this.data = new int[capacity];
        this.capacity = capacity;
    }

    public void insert(int value) {
        if (capacity > 0) {
            data[--capacity] = value;
        } else if (value > data[0]) {
            data[0] = value;
        }
        // TODO: find a better way to insert with order, e.g sorted BTree
        Arrays.sort(data);
    }

    public int[] get() {
        return data;
    }
}
