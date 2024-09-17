package dev.tronxi.engine.screens.gui;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.elements.Element;
import dev.tronxi.engine.screens.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class GUIScreen extends Screen {
    private final Canvas canvas;
    private final Image image;

    public GUIScreen(Dimension dimension, List<Element> elements) {
        super(dimension, elements);
        int elementSize = 15;
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1400, 600);
        frame.setVisible(true);
        try (InputStream imageStream = getClass().getResourceAsStream("/mario.png")) {
            if (imageStream == null) {
                throw new RuntimeException("Image not found");
            }
            image = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                for (int height = 0; height < dimension.height(); height++) {
                    for (int width = dimension.startVisibleWidth(); width < dimension.endVisibleWidth(); width++) {
                        Position currentPosition = new Position(width, height);
                        if (dimension.isInDimension(currentPosition)) {
                            Optional<Element> maybeElement = findElementByPosition(currentPosition);
                            maybeElement.ifPresent(element ->
                                    //g.drawRect(element.position().getWidth() * elementSize, element.position().getHeight() * elementSize, elementSize, elementSize)
                                    //g.drawImage(image, element.position().getWidth() * elementSize, element.position().getHeight() * elementSize, elementSize, elementSize, null)
                                    g.drawString(element.representation(), element.position().getWidth() * elementSize, element.position().getHeight() * elementSize));
                        }
                    }
                }

            }
        };
        canvas.setSize(1400, 600);
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void print() {
        canvas.repaint();
    }
}
