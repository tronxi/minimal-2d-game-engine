package dev.tronxi.engine;

import dev.tronxi.engine.elements.Position;

public class Dimension {
  private int width;
  private int height;
  private int widthVisibleSize;
  private int widthVisibleCenter;
  private int heightVisibleSize;
  private int heightVisibleCenter;

  public Dimension(int widthVisibleSize, int widthVisibleCenter, int heightVisibleSize, int heightVisibleCenter) {
    this.widthVisibleSize = widthVisibleSize;
    this.widthVisibleCenter = widthVisibleCenter;
    this.heightVisibleSize = heightVisibleSize;
    this.heightVisibleCenter = heightVisibleCenter;
  }

  public int width() {
    return width;
  }

  public int height() {
    return height;
  }

  public int startVisibleWidth() {
    return widthVisibleCenter - (widthVisibleSize / 2);
  }

  public int endVisibleWidth() {
    return widthVisibleCenter + (widthVisibleSize / 2);
  }

  public int startVisibleEight() {
    return heightVisibleCenter - (heightVisibleSize / 2);
  }

  public int endVisibleEight() {
    return heightVisibleCenter + (heightVisibleSize / 2);
  }

  public void setWidthVisibleSize(int widthVisibleSize) {
    this.widthVisibleSize = widthVisibleSize;
  }

  public void setWidthVisibleCenter(int widthVisibleCenter) {
    this.widthVisibleCenter = widthVisibleCenter;
  }

  public void setHeightVisibleSize(int heightVisibleSize) {
    this.heightVisibleSize = heightVisibleSize;
  }

  public void setHeightVisibleCenter(int heightVisibleCenter) {
    this.heightVisibleCenter = heightVisibleCenter;
  }

  public boolean isInDimension(Position position){
    return position.getWidth() >= 0 && position.getWidth() < width
        && position.getHeight() >= 0 && position.getHeight() < height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidthVisibleCenter() {
    return widthVisibleCenter;
  }

  public int getHeightVisibleCenter() {
    return heightVisibleCenter;
  }
}
