package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RetrieveProjectsUseCase {

  @Value("${engine.workspace}")
  private String workspace;

  public List<Project> retrieve() {
    List<Project> projects = new ArrayList<>();
    File workspaceFile = new File(this.workspace);
    if(!workspaceFile.exists() || !workspaceFile.isDirectory()) {
      throw new RuntimeException("Workspace does not exist or is not a directory");
    }
    File[] files = workspaceFile.listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          projects.add(new Project(file.getName()));
        }
      }
    }

    return projects;
  }

}
