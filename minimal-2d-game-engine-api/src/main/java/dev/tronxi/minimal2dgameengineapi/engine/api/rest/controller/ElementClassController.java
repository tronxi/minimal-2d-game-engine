package dev.tronxi.minimal2dgameengineapi.engine.api.rest.controller;

import dev.tronxi.minimal2dgameengineapi.engine.model.ElementClass;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.AddElementClassUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("project")
public class ElementClassController {

  private final AddElementClassUseCase addElementClassUseCase;

  public ElementClassController(AddElementClassUseCase addElementClassUseCase) {
    this.addElementClassUseCase = addElementClassUseCase;
  }

  @PostMapping("/{projectName}/elementClass")
  public ResponseEntity<Void> addLevel(@PathVariable String projectName,
      @RequestBody ElementClass elementClass) {
    addElementClassUseCase.add(new Project(projectName), elementClass);
    return ResponseEntity.ok().build();
  }
}
