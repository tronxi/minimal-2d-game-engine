package dev.tronxi.minimal2dgameengineapi.engine.model;

import java.util.List;

public record ProjectResources(Project project, List<Level> levels,
                               List<ElementClass> elementClasses) {

}
