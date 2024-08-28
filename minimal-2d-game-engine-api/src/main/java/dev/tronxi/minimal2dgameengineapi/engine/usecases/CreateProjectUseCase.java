package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectUseCase {

  @Value("${engine.path}")
  private String engine;

  @Value("${engine.workspace}")
  private String workspace;

  public void create(Project project) {
    Path enginePath = Paths.get(this.engine);
    Path workspacePath = Paths.get(this.workspace);
    Path projectPath = workspacePath.resolve(project.name());

    createProjectDirectory(projectPath);
    copyEngineToProjectDirectory(enginePath, projectPath);
    changeArtifactId(projectPath, project.name());


  }

  private void createProjectDirectory(Path projectPath) {
    boolean projectPathCreated = projectPath.toFile().mkdirs();

    if(!projectPathCreated) {
      throw new RuntimeException("Could not create project: " + projectPath);
    }
  }

  private void copyEngineToProjectDirectory(Path enginePath, Path projectPath) {
    try {
      FileUtils.copyDirectory(enginePath.toFile(), projectPath.toFile());
    } catch (IOException e) {
      throw new RuntimeException("Could not copy engine: " + e.getMessage());
    }
  }

  private void changeArtifactId(Path projectPath, String artifactId) {
    try {
      String pom = FileUtils.readFileToString(projectPath.resolve("pom.xml").toFile(), "UTF-8");
      System.out.println(pom);
      //TODO change artifactId
    } catch (IOException e) {
      throw new RuntimeException("Could not read pom: " + e.getMessage());
    }
  }

}
