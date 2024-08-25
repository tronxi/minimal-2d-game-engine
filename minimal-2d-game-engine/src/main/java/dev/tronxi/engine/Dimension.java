package dev.tronxi.engine;

public class Dimension {
  private final int width;
  private final int height;
  private int widthVisibleSize;
  private int widthVisibleCenter;

  public Dimension(int width, int height, int widthVisibleSize, int widthVisibleCenter) {
    this.width = width;
    this.height = height;
    this.widthVisibleSize = widthVisibleSize;
    this.widthVisibleCenter = widthVisibleCenter;
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

  public void setWidthVisibleSize(int widthVisibleSize) {
    this.widthVisibleSize = widthVisibleSize;
  }

  public void setWidthVisibleCenter(int widthVisibleCenter) {
    this.widthVisibleCenter = widthVisibleCenter;
  }

  public boolean isInDimension(Position position){
    return position.getWidth() >= 0 && position.getWidth() < width
        && position.getHeight() >= 0 && position.getHeight() < height;
  }
}
