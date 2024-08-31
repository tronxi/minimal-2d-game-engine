package dev.tronxi.minimal2dgameengineapi.engine.api.rest.controller;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.model.ProjectResources;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.BuildJarUseCase;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.CreateProjectUseCase;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.RetrieveProjectResourcesUseCase;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.RetrieveProjectsUseCase;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("project")
public class ProjectController {

  private final CreateProjectUseCase createProjectUseCase;
  private final RetrieveProjectsUseCase retrieveProjectsUseCase;
  private final BuildJarUseCase buildJarUseCase;
  private final RetrieveProjectResourcesUseCase retrieveProjectResourcesUseCase;

  public ProjectController(CreateProjectUseCase createProjectUseCase,
      RetrieveProjectsUseCase retrieveProjectsUseCase, BuildJarUseCase buildJarUseCase,
      RetrieveProjectResourcesUseCase retrieveProjectResourcesUseCase) {
    this.createProjectUseCase = createProjectUseCase;
    this.retrieveProjectsUseCase = retrieveProjectsUseCase;
    this.buildJarUseCase = buildJarUseCase;
    this.retrieveProjectResourcesUseCase = retrieveProjectResourcesUseCase;
  }

  @PostMapping("/{projectName}")
  public ResponseEntity<Void> createProject(@PathVariable String projectName) {
    Project project = new Project(projectName);
    createProjectUseCase.create(project);
    return ResponseEntity.ok().build();
  }

  @GetMapping()
  public ResponseEntity<List<Project>> getProjects() {
    return ResponseEntity.ok(retrieveProjectsUseCase.retrieve());
  }

  @PutMapping("/{projectName}/build")
  public ResponseEntity<Void> buildProject(@PathVariable String projectName) {
    Project project = new Project(projectName);
    buildJarUseCase.build(project);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{projectName}")
  public ResponseEntity<ProjectResources> getProjectResources(@PathVariable String projectName) {
    Project project = new Project(projectName);
    return ResponseEntity.ok(retrieveProjectResourcesUseCase.retrieve(project));
  }

}
