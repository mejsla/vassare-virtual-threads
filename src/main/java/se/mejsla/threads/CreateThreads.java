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

public class CreateThreads {
    public static void main(String[] args) throws InterruptedException {

        // Using static method in class Thread
        Thread.startVirtualThread(() ->
                System.out.println("Using static method: " + Thread.currentThread()));

        // Using a thread builder
        Thread.ofVirtual().name("my thread").start(() ->
                System.out.println("Using thread builder: " + Thread.currentThread()));

        // Using executor
        try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() ->
                    System.out.println("Using executor: " + Thread.currentThread()));
        }

        Thread.sleep(1000);
    }
}
