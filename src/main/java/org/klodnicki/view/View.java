package org.klodnicki.view;

import java.util.ArrayList;
import java.util.List;

public class View {
    private final List<String> lines = new ArrayList<>();

    public void addLine(String text) {
        lines.add(text);
    }

    public void clearLines() {
        lines.clear();
    }

    public void print() {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public void update(String text) {
        addLine(text);
        print();
        clearLines();
    }
}
