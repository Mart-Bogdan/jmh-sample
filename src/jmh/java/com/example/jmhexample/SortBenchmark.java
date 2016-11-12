package com.example.jmhexample;


import com.example.jmhsample.Sorter;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Fork(value = 1, /*jvmArgs = "-server", */warmups = 0)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Thread)
@Timeout(time = 1, timeUnit = TimeUnit.SECONDS)
public class SortBenchmark {
    // Could be an implicit State instead, but we are going to use it
    // as the dependency in one of the tests below
    @State(Scope.Benchmark)
    public static class Data {

        @Param({"1", "16", "256", "1024", "8000", "10000", "20000", "30000", "50000", "100000"})
        int count;

        int[] arr;

        @Setup()
        public void setup() {
            arr = new int[count];
            Random random = new Random(1234);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt();
            }

        }

        public int[] getArray() {
            if(arr.length==1)
                return arr;

            return arr.clone();
        }
    }

    /**
     * cleaning up memory consumed by array copes
     */
    @TearDown(Level.Iteration)
    public void runGcAfterIteration() {
        System.gc();
    }


    @Benchmark
    public int[] bubbleSort(Data d) {
        if (d.count > 30000)
            throw new RuntimeException("To many data for Bubble Sort");
        int[] array = d.getArray();

        Sorter.bubbleSort(array);

        return array;
    }
}
