package dev.tronxi.engine.listeners;

import dev.tronxi.engine.elements.Element;
import java.util.ArrayList;
import java.util.List;

public class InputListener {

  private final List<Element> registeredElements;

  public InputListener() {
    registeredElements = new ArrayList<>();
  }

  public void registerElement(Element element) {
    this.registeredElements.add(element);
  }

  public void handler(Event event) {
    for (Element element : registeredElements) {
      element.handleInput(event);
    }
  }

}
