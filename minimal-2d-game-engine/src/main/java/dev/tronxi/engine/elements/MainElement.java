package dev.tronxi.engine.elements;

import dev.tronxi.engine.Game;
import dev.tronxi.engine.Position;

public class MainElement extends Element {

  private int mustDown = 0;

  public MainElement(String representation, Position position, Game game) {
    super(representation, position, game);
  }

  @Override
  public void start() {
    registerForHandleInput(inputListener());
  }

  @Override
  public void update() {
    if (mustDown % 10 == 0) {
      mustDown = 0;
      if (!hasRepresentationDown("#")) {
        position().down();
        if (position().getHeight() == dimension().height()) {
          game().initElements();
        }
      }
    }
    mustDown++;
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
        elements().add(
            new ShotElement(">", new Position(position().getWidth(), position().getHeight()), game()));
      }
    }
    dimension().setWidthVisibleCenter(position().getWidth());
  }
}
