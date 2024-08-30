package dev.tronxi.minimal2dgameengineapi.engine.model;

import java.nio.file.Path;

public record Level(String name, String content) {

  public Path retrievePath(Path levelsPath) {
    return levelsPath.resolve(this.name);
  }

}
