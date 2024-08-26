package dev.tronxi.engine.elements;

import dev.tronxi.engine.Game;
import dev.tronxi.engine.Position;

public class ShotElement extends Element {

  public ShotElement(String representation, Position position, Game game) {
    super(representation, position, game);
  }

  @Override
  public void start() {

  }

  @Override
  public void update() {
    if(!hasRepresentationRight("#")) {
      position().right();
    } else {
      remove();
    }

  }
}
