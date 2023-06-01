package org.klodnicki;

import org.klodnicki.controller.MenuController;

public class Main {
    public static void main(String[] args) {

        do {
            MenuController menuController = new MenuController();
            menuController.run();
        }while(true);
    }
}
