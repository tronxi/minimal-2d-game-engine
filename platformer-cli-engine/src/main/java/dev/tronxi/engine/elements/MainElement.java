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
    if (key.equals("D")) {
      if (!hasRepresentationRight("#")) {
        position.right();
      }
    } else if (key.equals("A")) {
      if (!hasRepresentationLeft("#")) {
        position.left();
      }
    } else if (key.equals("W")) {
      position.up();
      position.up();
      position.up();
    }
    dimension.setWidthVisibleCenter(position.getWidth());
  }
}
