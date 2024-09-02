package dev.tronxi.engine.screens;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.elements.Element;
import java.util.List;
import java.util.Optional;

public abstract class Screen {
  protected List<Element> elements;
  protected Dimension dimension;

  public Screen(Dimension dimension, List<Element> elements) {
    this.dimension = dimension;
    this.elements = elements;
  }

  public abstract void print();

  public Optional<Element> findElementByPosition(Position position) {
    return elements.stream()
        .filter(element -> element.position().equals(position)).findFirst();
  }

}
