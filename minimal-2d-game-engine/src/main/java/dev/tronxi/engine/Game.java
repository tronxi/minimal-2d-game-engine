package dev.tronxi.engine;

import dev.tronxi.engine.elements.Element;
import dev.tronxi.engine.elements.ElementsGenerator;
import dev.tronxi.engine.listeners.InputListener;
import dev.tronxi.engine.screens.Screen;
import dev.tronxi.engine.screens.gui.GUIScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {

    private final Dimension dimension;
    private final Screen screen;
    private final InputListener inputListener;
    private final List<Element> elements;
    private final Properties properties;
    private final Map<String, Image> sprites;

    public Game(Properties properties, InputListener inputListener) {
        this.sprites = new HashMap<>();
        this.properties = properties;
        this.inputListener = inputListener;
        dimension = new Dimension(80, 0, 40, 0);
        this.elements = new CopyOnWriteArrayList<>();
        //this.screen = new CLIScreen(dimension, elements);
        this.screen = new GUIScreen(dimension, elements);
    }

    public void initElements() {
        elements.clear();
        elements.addAll(new ElementsGenerator().generate(properties, this));
        for (Element element : elements) {
            element.start();
        }
    }

    public void initElementsForLevel(String level) {
        elements.clear();
        elements.addAll(new ElementsGenerator().generateForLevel(properties, this, level));
        for (Element element : elements) {
            element.start();
        }
    }

    public void start() {
        initElements();
        do {
            screen.print();
            for (Element element : elements) {
                if (dimension.isInDimension(element.position())) {
                    element.update();
                }
            }
        } while (true);
    }

    public Dimension dimension() {
        return dimension;
    }

    public List<Element> elements() {
        return elements;
    }

    public InputListener inputListener() {
        return inputListener;
    }

    public Optional<Image> getSprite(String sprite) {
        return Optional.ofNullable(sprites.get(sprite));
    }

    public void addSprite(String spriteName) {
        if (sprites.containsKey(spriteName)) return;
        Image image;
        try (InputStream imageStream = getClass().getResourceAsStream("/sprites/" + spriteName)) {
            if (imageStream == null) {
                throw new RuntimeException("Image not found");
            }
            image = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sprites.put(spriteName, image);
    }

    public void removeSprite(String sprite) {
        sprites.remove(sprite);
    }
}
