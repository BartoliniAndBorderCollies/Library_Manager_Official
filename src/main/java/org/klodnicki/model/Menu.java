package org.klodnicki.model;

import org.klodnicki.command.MenuCommand;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuCommand> commands = new ArrayList<>();

    public void addCommand(MenuCommand command) {
        commands.add(command);
    }

    public List<MenuCommand> getCommands() {
        return commands;
    }

    public void executeCommand(MenuCommand command) {
        command.execute();
    }
}
