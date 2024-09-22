package dev.tronxi.engine.screens.gui;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.elements.Element;
import dev.tronxi.engine.listeners.InputListener;
import dev.tronxi.engine.screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class GUIScreen extends Screen {
    private final Canvas canvas;

    public GUIScreen(Dimension dimension, List<Element> elements, InputListener inputListener) {
        super(dimension, elements, inputListener);
        int elementSize = 20;
        JFrame frame = new JFrame();
        frame.addKeyListener(new GUIKeyListener(inputListener));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                int visibleHeight = 0;
                for (int height = dimension.startVisibleEight(); height < dimension.endVisibleEight(); height++) {
                    int visibleWidth = 0;
                    for (int width = dimension.startVisibleWidth(); width < dimension.endVisibleWidth(); width++) {
                        Position currentPosition = new Position(width, height);
                        if (dimension.isInDimension(currentPosition)) {
                            Optional<Element> maybeElement = findElementByPosition(currentPosition);
                            int finalVisibleWidth = visibleWidth;
                            int finalVisibleHeight = visibleHeight;
                            maybeElement.ifPresent(element -> drawElement(g, element, elementSize, finalVisibleWidth, finalVisibleHeight));
                        }
                        visibleWidth += 1;
                    }
                    visibleHeight += 1;
                }

            }
        };
        canvas.setSize(frame.getWidth(), frame.getHeight());
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void drawElement(Graphics g, Element element, int elementSize, int visibleWidth, int visibleHeight) {
        element.getSprite().ifPresentOrElse(sprite -> g.drawImage(sprite, visibleWidth * elementSize, visibleHeight * elementSize, elementSize, elementSize, null), () -> g.drawString(element.representation(), visibleWidth * elementSize, (visibleHeight + 1) * elementSize));
    }

    @Override
    public void print() {
        canvas.repaint();
    }
}
