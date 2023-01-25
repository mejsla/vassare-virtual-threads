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

public class CreateManyThreads {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private static int countInThread() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(10));
        return counter.incrementAndGet();
    }

    public static void main(String[] args) {
        final var start = System.currentTimeMillis();

        //try (final var executor = Executors.newCachedThreadPool()) {
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 100_000).forEach(i -> executor.submit(CreateManyThreads::countInThread));
        }

        System.out.println("Executed " + counter + " threads in " +
                           (System.currentTimeMillis() - start) / 1000.0 + " seconds");
    }
}
