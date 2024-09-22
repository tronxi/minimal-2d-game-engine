package dev.tronxi.engine.screens.gui;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.elements.Element;
import dev.tronxi.engine.screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class GUIScreen extends Screen {
    private final Canvas canvas;

    public GUIScreen(Dimension dimension, List<Element> elements) {
        super(dimension, elements);
        int elementSize = 15;
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                for (int height = dimension.startVisibleEight(); height < dimension.endVisibleEight(); height++) {
                    for (int width = dimension.startVisibleWidth(); width < dimension.endVisibleWidth(); width++) {
                        Position currentPosition = new Position(width, height);
                        if (dimension.isInDimension(currentPosition)) {
                            Optional<Element> maybeElement = findElementByPosition(currentPosition);
                            maybeElement.ifPresent(element -> drawElement(g, element, elementSize));
                        }
                    }
                }

            }
        };
        canvas.setSize(frame.getWidth(), frame.getHeight());
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void drawElement(Graphics g, Element element, int elementSize) {
        element.getSprite().ifPresentOrElse(sprite -> g.drawImage(sprite, element.position().getWidth() * elementSize, element.position().getHeight() * elementSize, elementSize, elementSize, null), () -> g.drawString(element.representation(), element.position().getWidth() * elementSize, (element.position().getHeight() + 1) * elementSize));
    }

    @Override
    public void print() {
        canvas.repaint();
    }
}
