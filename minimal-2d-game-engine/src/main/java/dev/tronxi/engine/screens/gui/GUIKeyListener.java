package dev.tronxi.engine.screens.gui;

import dev.tronxi.engine.listeners.Event;
import dev.tronxi.engine.listeners.EventType;
import dev.tronxi.engine.listeners.InputListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIKeyListener implements KeyListener {
    private final InputListener inputListener;

    public GUIKeyListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Event event = new Event(EventType.PRESSED, String.valueOf(e.getKeyChar()).toUpperCase());
        inputListener.handler(event);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Event event = new Event(EventType.RELEASED, String.valueOf(e.getKeyChar()).toUpperCase());
        inputListener.handler(event);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
