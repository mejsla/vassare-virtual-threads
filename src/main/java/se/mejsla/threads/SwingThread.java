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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.time.Duration;

public class SwingThread {

    private static final JButton button = new JButton("Click me!");

    private static void startVirtualThread() {
        Thread.startVirtualThread(() -> {
            System.out.println("Before: " + Thread.currentThread());
            try {
                Thread.sleep(Duration.ofSeconds(2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("After:  " + Thread.currentThread());
        });
    }

    public static void main(String[] args) {
        button.addActionListener(e -> startVirtualThread());

        SwingUtilities.invokeLater(() -> {
            final var frame = new JFrame("Swing Virtual Threads");
            frame.getContentPane().add(button);
            frame.setPreferredSize(new Dimension(300, 200));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
