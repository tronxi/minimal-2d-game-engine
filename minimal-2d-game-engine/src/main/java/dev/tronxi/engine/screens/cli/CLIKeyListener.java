package dev.tronxi.engine.screens.cli;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import dev.tronxi.engine.listeners.Event;
import dev.tronxi.engine.listeners.EventType;
import dev.tronxi.engine.listeners.InputListener;

public class CLIKeyListener implements NativeKeyListener {
    private final InputListener inputListener;

    public CLIKeyListener(InputListener inputListener) {
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
