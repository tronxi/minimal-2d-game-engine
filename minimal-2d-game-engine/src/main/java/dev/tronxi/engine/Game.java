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

  public Game(InputListener inputListener) {
    dimension = new Dimension(200, 20, 80, 40);
    this.elements = new CopyOnWriteArrayList<>();
    this.screen = new CLIScreen(dimension, elements);

    Element mainElement = new MainElement("^", new Position(0, 0), elements, dimension, inputListener);

    this.elements.add(mainElement);
    this.elements.add(new ObstacleElement("#", new Position(0, 15), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(1, 16), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(2, 17), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(3, 18), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(4, 19), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(4, 17), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(5, 19), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(5, 16), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(6, 19), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(6, 15), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(7, 19), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(7, 14), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(8, 19), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(9, 19), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(10, 18), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(11, 17), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(12, 16), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(13, 15), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(14, 14), elements, dimension));
    this.elements.add(new ObstacleElement("#", new Position(15, 13), elements, dimension));
  }

  public void start() {
    for (Element element : elements) {
      element.start();
    }
    do {
      screen.print();
      for (Element element : elements) {
        if (dimension.isInDimension(element.getPosition())) {
          element.update();
        }
      }
    } while (true);
  }

}
