package dev.tronxi.minimal2dgameengineapi.engine.usecases.services;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import java.io.File;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

@Component
public class ProjectFileRetriever {

  public File retrieveProjectFile(String workspace, Project project) {
    File projectFile = Paths.get(workspace).resolve(project.name()).toFile();
    if (!projectFile.exists()) {
      throw new RuntimeException("Project not exist");
    }
    return projectFile;
  }

}
