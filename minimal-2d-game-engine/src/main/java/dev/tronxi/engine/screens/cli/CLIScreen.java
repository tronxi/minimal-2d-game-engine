package dev.tronxi.engine.screens.cli;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.elements.Element;
import dev.tronxi.engine.screens.Screen;
import java.util.List;
import java.util.Optional;

public class CLIScreen extends Screen {

  public CLIScreen(Dimension dimension, List<Element> elements) {
    super(dimension, elements);
  }

  @Override
  public void print() {
    CLIUtils.clear();
    for (int height = 0; height < dimension.height(); height++) {
      for (int width = dimension.startVisibleWidth(); width < dimension.endVisibleWidth();
          width++) {
        Position currentPosition = new Position(width, height);
        if (dimension.isInDimension(currentPosition)) {
          Optional<Element> maybeElement = findElementByPosition(currentPosition);
          maybeElement.ifPresentOrElse(
              element -> System.out.print(element.representation()),
              () -> System.out.print(" "));
        }
      }
      System.out.println();
    }
//    for (Element element : elements) {
//      System.out.println(element.getRepresentation() + ", " + element.getPosition());
//    }
    CLIUtils.sleep(10);
  }

}
