/*
 * Copyright 2023 Johan Dykström
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.mejsla.threads;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SynchronizedThread {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final Object lock0 = new Object();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private final Object lock3 = new Object();
    private final Object lock4 = new Object();
    private final Object lock5 = new Object();
    private final Object lock6 = new Object();
    private final Object lock7 = new Object();
    private final Object lock8 = new Object();
    private final Object lock9 = new Object();
    private final Object lock10 = new Object();
    private final Object lock11 = new Object();
    private final Object lock12 = new Object();
    private final Object lock13 = new Object();
    private final Object lock14 = new Object();
    private final Object lock15 = new Object();

    private void sleep(final int i) {
        System.out.println("Sleeping " + i + "...");
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Sleeping " + i + "... done");
    }

    private int lockAndSleep0() {
        synchronized (lock0) {
            sleep(0);
        }
        return 0;
    }

    private int lockAndSleep1() {
        synchronized (lock1) {
            sleep(1);
        }
        return 1;
    }

    private int lockAndSleep2() {
        synchronized (lock2) {
            sleep(2);
        }
        return 2;
    }

    private int lockAndSleep3() {
        synchronized (lock3) {
            sleep(3);
        }
        return 3;
    }

    private int lockAndSleep4() {
        synchronized (lock4) {
            sleep(4);
        }
        return 4;
    }

    private int lockAndSleep5() {
        synchronized (lock5) {
            sleep(5);
        }
        return 5;
    }

    private int lockAndSleep6() {
        synchronized (lock6) {
            sleep(6);
        }
        return 6;
    }

    private int lockAndSleep7() {
        synchronized (lock7) {
            sleep(7);
        }
        return 7;
    }

    private int lockAndSleep8() {
        synchronized (lock8) {
            sleep(8);
        }
        return 8;
    }

    private int lockAndSleep9() {
        synchronized (lock9) {
            sleep(9);
        }
        return 9;
    }

    private int lockAndSleep10() {
        synchronized (lock10) {
            sleep(10);
        }
        return 10;
    }

    private int lockAndSleep11() {
        synchronized (lock11) {
            sleep(11);
        }
        return 11;
    }

    private int lockAndSleep12() {
        synchronized (lock12) {
            sleep(12);
        }
        return 12;
    }

    private int lockAndSleep13() {
        synchronized (lock13) {
            sleep(13);
        }
        return 13;
    }

    private int lockAndSleep14() {
        synchronized (lock14) {
            sleep(14);
        }
        return 14;
    }

    private int lockAndSleep15() {
        synchronized (lock15) {
            sleep(15);
        }
        return 15;
    }

    private int countInThread() {
        final var number = counter.incrementAndGet();
        try {
            System.out.println("Executing virtual thread " + number);
        } finally {
            counter.decrementAndGet();
        }
        return 0;
    }

    public static void main(String[] args) {
        final var st = new SynchronizedThread();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(st::lockAndSleep0);
            executor.submit(st::lockAndSleep1);
            executor.submit(st::lockAndSleep2);
            executor.submit(st::lockAndSleep3);
            executor.submit(st::lockAndSleep4);
            executor.submit(st::lockAndSleep5);
            executor.submit(st::lockAndSleep6);
            executor.submit(st::lockAndSleep7);
            executor.submit(st::lockAndSleep8);
            executor.submit(st::lockAndSleep9);
            executor.submit(st::lockAndSleep10);
            executor.submit(st::lockAndSleep11);
            executor.submit(st::lockAndSleep12);
            executor.submit(st::lockAndSleep13);
            executor.submit(st::lockAndSleep14);
            //executor.submit(st::lockAndSleep15);

            IntStream.range(0, 10).forEach(i -> executor.submit(st::countInThread));
        }
    }
}
