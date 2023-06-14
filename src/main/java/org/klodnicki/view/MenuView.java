package org.klodnicki.view;

import java.util.ArrayList;
import java.util.List;

public class MenuView {
    private final List<String> lines = new ArrayList<>();

    private void addLine(String text) {
        lines.add(text);
    }

    private void addLines (List<String> textLines) {
        lines.addAll(textLines);
    }

    private void clearLines() {
        lines.clear();
    }

    private void print() {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public void update(String text) {
        addLine(text);
        print();
        clearLines();
    }

    public void update(List<String> messages) {
        addLines(messages);
        print();
        clearLines();
    }
}
