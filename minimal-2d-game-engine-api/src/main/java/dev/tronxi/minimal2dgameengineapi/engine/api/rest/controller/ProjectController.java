package dev.tronxi.minimal2dgameengineapi.engine.api.rest.controller;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.CreateProjectUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
public class ProjectController {

  private final CreateProjectUseCase createProjectUseCase;

  public ProjectController(CreateProjectUseCase createProjectUseCase) {
    this.createProjectUseCase = createProjectUseCase;
  }

  @PostMapping("/{projectName}")
  public ResponseEntity<Void> createProject(@PathVariable String projectName) {
    Project project = new Project(projectName);
    createProjectUseCase.create(project);
    return ResponseEntity.ok().build();
  }

}
