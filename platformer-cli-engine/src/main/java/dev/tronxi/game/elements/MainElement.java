package dev.tronxi.game.elements;

import dev.tronxi.game.Position;

public class MainElement extends Element {

  public MainElement(String representation, Position position) {
    super("^", new Position(0, 0));
  }

  @Override
  public void start() {

  }

  @Override
  public void update() {
    position.advance();
  }
}
