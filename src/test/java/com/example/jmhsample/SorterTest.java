package com.example.jmhsample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;


@RunWith(Theories.class)
public class SorterTest {


    public int[] generateArray(int seed){
        int[] array = new int[8000];
        Random r = new Random(5000);
        for (int i = 0; i < array.length; i++) {
            array[i]=r.nextInt();
        }

        return array;
    }

    @Theory
    public void bubbleSortCorrect(int seed) {
        int[] array = generateArray(seed);
        int[] etalon = generateArray(seed);
        Arrays.sort(etalon);

        Sorter.bubbleSort(array);

        Assert.assertArrayEquals(etalon,array);
    }

    @DataPoints
    public static int[] candidates = {1, 2, 3, 4, 5, 13};

}