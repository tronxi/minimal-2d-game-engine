package dev.tronxi.engine.elements;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.listeners.InputListener;
import java.util.List;

public class MainElement extends Element {

  public MainElement(String representation, Position position, List<Element> elements,
      Dimension dimension) {
    super(representation, position, elements, dimension);
  }

  public MainElement(String representation, Position position, List<Element> elements,
      Dimension dimension, InputListener inputListener) {
    super(representation, position, elements, dimension, inputListener);
  }

  @Override
  public void start() {
  }

  @Override
  public void update() {
    if (!hasRepresentationDown("#")) {
      position.down();
    }
  }

  @Override
  public void handleInput(String key) {
    switch (key) {
      case "D" -> {
        if (!hasRepresentationRight("#")) {
          position.right();
        }
      }
      case "A" -> {
        if (!hasRepresentationLeft("#")) {
          position.left();
        }
      }
      case "W" -> {
        if (hasRepresentationDown("#")) {
          if (!hasRepresentationUp("#")) {
            position.up();
          }
          if (!hasRepresentationUp("#")) {
            position.up();
          }
          if (!hasRepresentationUp("#")) {
            position.up();
          }
        }
      }
      case "Q" -> {
        elements.add(new ShotElement(">", new Position(position.getWidth(), position.getHeight()), elements, dimension));
      }
    }
    dimension.setWidthVisibleCenter(position.getWidth());
  }
}
