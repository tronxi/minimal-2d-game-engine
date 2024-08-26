package dev.tronxi;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import dev.tronxi.engine.Game;
import dev.tronxi.engine.PropertiesReader;
import dev.tronxi.engine.listeners.GlobalKeyListener;
import dev.tronxi.engine.listeners.InputListener;
import java.util.Properties;

public class Main {

  public Main() {
    Properties properties = new PropertiesReader().read("engine.properties");
    InputListener inputListener = new InputListener();
    Game game = new Game(properties, inputListener);
    setKeyListeners(inputListener);
    game.start();
  }

  private void setKeyListeners(InputListener inputListener) {
    try {
      GlobalScreen.registerNativeHook();
    }
    catch (NativeHookException ex) {
      System.err.println("There was a problem registering the native hook.");
      System.err.println(ex.getMessage());
      System.exit(1);
    }
    GlobalScreen.addNativeKeyListener(new GlobalKeyListener(inputListener));
  }

  public static void main(String[] args) {
    new Main();
  }
}