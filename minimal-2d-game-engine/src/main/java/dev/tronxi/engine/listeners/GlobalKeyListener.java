package dev.tronxi.engine.listeners;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
    private final InputListener inputListener;

    public GlobalKeyListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        Event event = new Event(EventType.PRESSED, NativeKeyEvent.getKeyText(e.getKeyCode()));
        inputListener.handler(event);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        Event event = new Event(EventType.RELEASED, NativeKeyEvent.getKeyText(e.getKeyCode()));
        inputListener.handler(event);
    }

}
