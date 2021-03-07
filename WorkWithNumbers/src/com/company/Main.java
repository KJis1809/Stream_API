package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> inList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        List<Integer> inList2 = new ArrayList<>();
        for (Integer element : inList) {
            if (element > 0 && element % 2 == 0) {
                inList2.add(element);
            }
        }

        inList2.sort(Comparator.naturalOrder());
        System.out.println(inList2);
    }
}
