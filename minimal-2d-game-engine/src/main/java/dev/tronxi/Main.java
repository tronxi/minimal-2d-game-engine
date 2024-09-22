package dev.tronxi;

import dev.tronxi.engine.Game;
import dev.tronxi.engine.screens.ScreenMode;

public class Main {
    public static void main(String[] args) {
        ScreenMode mode = (args.length >= 1 && args[0].equalsIgnoreCase("cli")) ? ScreenMode.CLI : ScreenMode.GUI;
        new Game(mode).start();
    }
}