package dev.tronxi.engine.elements;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.listeners.InputListener;
import java.util.List;

public abstract class Element {

  protected final Position position;
  protected final Dimension dimension;
  protected final List<Element> elements;
  private final String representation;

  public Element(String representation, Position position, List<Element> elements,
      Dimension dimension) {
    this.representation = representation;
    this.position = position;
    this.dimension = dimension;
    this.elements = elements;
  }

  public Element(String representation, Position position, List<Element> elements,
      Dimension dimension, InputListener inputListener) {
    this.representation = representation;
    this.position = position;
    this.dimension = dimension;
    this.elements = elements;
    this.registerForHandleInput(inputListener);
  }

  public String getRepresentation() {
    return representation;
  }

  public Position getPosition() {
    return position;
  }

  public abstract void start();

  public abstract void update();

  public void handleInput(String key) {
  }

  private void registerForHandleInput(InputListener inputListener) {
    inputListener.registerElement(this);
  }

  protected boolean hasRepresentationUp(String representation) {
    Position downPosition = new Position(position.getWidth(), position.getHeight() - 1);
    return elements.stream()
        .anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(
            representation));
  }

  protected boolean hasRepresentationDown(String representation) {
    Position downPosition = new Position(position.getWidth(), position.getHeight() + 1);
    return elements.stream()
        .anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(
            representation));
  }

  protected boolean hasRepresentationRight(String representation) {
    Position downPosition = new Position(position.getWidth() + 1, position.getHeight());
    return elements.stream()
        .anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(
            representation));
  }

  protected boolean hasRepresentationLeft(String representation) {
    Position downPosition = new Position(position.getWidth() - 1, position.getHeight());
    return elements.stream()
        .anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(
            representation));
  }

  protected void remove() {
    elements.removeIf(
        element -> element.position.equals(this.position) && element.representation.equals(
            this.representation));
  }

}
