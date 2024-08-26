package dev.tronxi.engine.elements;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Game;
import dev.tronxi.engine.Position;
import java.util.List;

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
