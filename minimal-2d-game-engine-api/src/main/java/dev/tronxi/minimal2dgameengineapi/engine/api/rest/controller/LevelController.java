package dev.tronxi.minimal2dgameengineapi.engine.api.rest.controller;

import dev.tronxi.minimal2dgameengineapi.engine.model.Level;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.AddLevelUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project/")
public class LevelController {

  private final AddLevelUseCase addLevelUseCase;

  public LevelController(AddLevelUseCase addLevelUseCase) {
    this.addLevelUseCase = addLevelUseCase;
  }

  @PostMapping("/{projectName}/level")
  public ResponseEntity<Void> addLevel(@PathVariable String projectName, @RequestBody Level level) {
    addLevelUseCase.add(new Project(projectName), level);
    return ResponseEntity.ok().build();
  }

}
