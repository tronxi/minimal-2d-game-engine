package dev.tronxi.game.elements;

import dev.tronxi.game.Position;

public abstract class Element {

  protected final Position position;
  private final String representation;

  public Element(String representation, Position position) {
    this.representation = representation;
    this.position = position;
  }

  public String getRepresentation() {
    return representation;
  }

  public abstract void start();

  public abstract void update();

}
