package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Level;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.PropertiesManager;
import java.io.File;
import java.nio.file.Path;
import org.springframework.stereotype.Service;

@Service
public class AddLevelUseCase extends AddResourceUseCase {

  public AddLevelUseCase(ProjectFileRetriever projectFileRetriever,
      PropertiesManager propertiesManager) {
    super(projectFileRetriever, propertiesManager);
  }

  public void add(Project project, Level level) {
    File projectFile = projectFileRetriever.retrieveProjectFile(workspace, project);
    Path levelsPath = projectFile.toPath().resolve("levels");

    createResourcesPath(levelsPath);
    createResourceFile(levelsPath, level.name());
    writeResourceContent(levelsPath, level.name(), level.content());
    propertiesManager.setLevelName(project, levelsPath, level);

  }

}
