package dev.tronxi.engine.elements;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Game;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.listeners.InputListener;
import java.util.List;

public class MainElement extends Element {
  private boolean mustDown = true;

  public MainElement(String representation, Position position, Game game) {
    super(representation, position, game);
  }

  @Override
  public void start() {
    registerForHandleInput(inputListener());
  }

  @Override
  public void update() {
    if(mustDown) {
      if (!hasRepresentationDown("#")) {
        position().down();
      }
    }
    mustDown = !mustDown;
  }

  @Override
  public void handleInput(String key) {
    switch (key) {
      case "D" -> {
        if (!hasRepresentationRight("#")) {
          position().right();
        }
      }
      case "A" -> {
        if (!hasRepresentationLeft("#")) {
          position().left();
        }
      }
      case "W" -> {
        if (hasRepresentationDown("#")) {
          if (!hasRepresentationUp("#")) {
            position().up();
          }
          if (!hasRepresentationUp("#")) {
            position().up();
          }
          if (!hasRepresentationUp("#")) {
            position().up();
          }
        }
      }
      case "Q" -> {
        elements().add(new ShotElement(">", new Position(position().getWidth(), position().getHeight()), game()));
      }
    }
    dimension().setWidthVisibleCenter(position().getWidth());
  }
}
