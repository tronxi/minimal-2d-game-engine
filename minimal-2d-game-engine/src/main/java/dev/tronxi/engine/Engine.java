package dev.tronxi.engine;

import dev.tronxi.engine.common.PropertiesReader;
import dev.tronxi.engine.screens.ScreenMode;

import java.util.Properties;

public class Engine {

    public Engine(ScreenMode screenMode) {
        Properties properties = new PropertiesReader().read("engine.properties");
        Game game = new Game(screenMode, properties);
        game.start();
    }
}
