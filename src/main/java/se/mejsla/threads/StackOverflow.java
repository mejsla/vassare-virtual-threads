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

import java.util.concurrent.Executors;

public class StackOverflow {

    private static void count(final long c) {
        System.out.println("Counting " + c);
        try {
            count(c + 1);
        } catch (StackOverflowError e) {
            if (c == 1) {
                System.err.println(Thread.currentThread() + ": " + e);
                System.exit(1);
            } else {
                throw e;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try (final var executor = Executors.newCachedThreadPool()) {
        //try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> count(1));
        }
        Thread.sleep(3000);
    }
}
