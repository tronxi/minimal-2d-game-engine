package dev.tronxi.game.listeners;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
  private final InputListener inputListener;

  public GlobalKeyListener(InputListener inputListener) {
    this.inputListener = inputListener;
  }

  public void nativeKeyPressed(NativeKeyEvent e) {
    inputListener.handler(NativeKeyEvent.getKeyText(e.getKeyCode()));
  }
}
