package dev.tronxi.engine.elements;

import java.util.Objects;

public class Position {

  private int width;
  private int height;

  public Position(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void right() {
    width += 1;
  }

  public void left() {
    width -= 1;
  }

  public void down() {
    height += 1;
  }

  public void up() {
    height -= 1;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public String toString() {
    return "{" +
        "width=" + width +
        ", height=" + height +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return width == position.width && height == position.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }
}
