package dev.tronxi.minimal2dgameengineapi.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ElementClass(String representation, String className, @JsonIgnore String content) {

}
