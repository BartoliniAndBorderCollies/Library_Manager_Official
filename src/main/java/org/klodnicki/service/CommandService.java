package org.klodnicki.service;

import java.util.ArrayList;
import java.util.List;

public class CommandService {

    public List<String> possibleCommands = new ArrayList<>();

    public void addCommand() {
        possibleCommands.add("help");
        possibleCommands.add("create account");

    }

    public void showCommands() {
        for (String rows: possibleCommands) {
            System.out.println(rows);
        }
    }
}
