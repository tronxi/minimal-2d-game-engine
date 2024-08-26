package dev.tronxi.engine;

import dev.tronxi.engine.elements.Element;
import dev.tronxi.engine.elements.MainElement;
import dev.tronxi.engine.elements.ObstacleElement;
import dev.tronxi.engine.listeners.InputListener;
import dev.tronxi.engine.screens.Screen;
import dev.tronxi.engine.screens.cli.CLIScreen;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {

  private final Dimension dimension;
  private final List<Element> elements;
  private final Screen screen;
  private final InputListener inputListener;

  public Game(InputListener inputListener) {
    this.inputListener = inputListener;
    dimension = new Dimension(200, 20, 80, 40);
    this.elements = new CopyOnWriteArrayList<>();
    this.screen = new CLIScreen(dimension, elements);

    Element mainElement = new MainElement("\u001B[36m" + "^" + "\u001B[0m", new Position(0, 0), this);

    this.elements.add(mainElement);
    this.elements.add(new ObstacleElement("#", new Position(0, 15), this));
    this.elements.add(new ObstacleElement("#", new Position(1, 16), this));
    this.elements.add(new ObstacleElement("#", new Position(2, 17), this));
    this.elements.add(new ObstacleElement("#", new Position(3, 18), this));
    this.elements.add(new ObstacleElement("#", new Position(4, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(4, 17), this));
    this.elements.add(new ObstacleElement("#", new Position(5, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(5, 16), this));
    this.elements.add(new ObstacleElement("#", new Position(6, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(6, 15), this));
    this.elements.add(new ObstacleElement("#", new Position(7, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(7, 14), this));
    this.elements.add(new ObstacleElement("#", new Position(8, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(9, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(10, 18), this));
    this.elements.add(new ObstacleElement("#", new Position(11, 17), this));
    this.elements.add(new ObstacleElement("#", new Position(12, 16), this));
    this.elements.add(new ObstacleElement("#", new Position(13, 15), this));
    this.elements.add(new ObstacleElement("#", new Position(14, 14), this));
    this.elements.add(new ObstacleElement("#", new Position(15, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(16, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(17, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(18, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(19, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(20, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(21, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(22, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(23, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(24, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(25, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(27, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(28, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(29, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(30, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(31, 19), this));
    this.elements.add(new ObstacleElement("#", new Position(32, 19), this));
  }

  public void start() {
    for (Element element : elements) {
      element.start();
    }
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
}
