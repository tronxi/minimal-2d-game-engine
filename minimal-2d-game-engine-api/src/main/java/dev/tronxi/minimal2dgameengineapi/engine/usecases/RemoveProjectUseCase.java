package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RemoveProjectUseCase {

  private final ProjectFileRetriever projectFileRetriever;
  @Value("${engine.workspace}")
  protected String workspace;

  public RemoveProjectUseCase(ProjectFileRetriever projectFileRetriever) {
    this.projectFileRetriever = projectFileRetriever;
  }

  public void remove(Project project) {
    File projectFile = projectFileRetriever.retrieveProjectFile(workspace, project);
    if (projectFile.exists()) {
      try {
        FileUtils.deleteDirectory(projectFile);
      } catch (IOException e) {
        throw new RuntimeException("Failed to delete project file: " + e.getMessage());
      }
    }
  }

}
