package dev.tronxi;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import dev.tronxi.game.Game;
import dev.tronxi.game.listeners.GlobalKeyListener;
import dev.tronxi.game.listeners.InputListener;

public class Main {

  public Main() {
    setKeyListeners();
    initGame();
  }

  private void setKeyListeners() {
    try {
      GlobalScreen.registerNativeHook();
    }
    catch (NativeHookException ex) {
      System.err.println("There was a problem registering the native hook.");
      System.err.println(ex.getMessage());
      System.exit(1);
    }
    GlobalScreen.addNativeKeyListener(new GlobalKeyListener(new InputListener()));
  }

  private void initGame() {
    new Game().start();
  }

  public static void main(String[] args) {
    new Main();
  }
}