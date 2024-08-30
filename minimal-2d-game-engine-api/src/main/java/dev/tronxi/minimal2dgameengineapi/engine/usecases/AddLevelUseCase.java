package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Level;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.PropertiesManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddLevelUseCase {

  private final ProjectFileRetriever projectFileRetriever;
  private final PropertiesManager propertiesManager;
  @Value("${engine.workspace}")
  private String workspace;

  public AddLevelUseCase(ProjectFileRetriever projectFileRetriever,
      PropertiesManager propertiesManager) {
    this.projectFileRetriever = projectFileRetriever;
    this.propertiesManager = propertiesManager;
  }

  public void add(Project project, Level level) {
    File projectFile = projectFileRetriever.retrieveProjectFile(workspace, project);
    Path levelsPath = projectFile.toPath().resolve("levels");

    createLevelsPath(levelsPath);
    createLevelFile(levelsPath, level);
    writeLevelContent(levelsPath, level);
    propertiesManager.setLevelName(project, levelsPath, level);

  }

  private void createLevelsPath(Path levelsPath) {
    if (!levelsPath.toFile().exists()) {
      boolean levelsPathCreated = levelsPath.toFile().mkdirs();
      if (!levelsPathCreated) {
        throw new RuntimeException("Unable to create levels directory");
      }
    }
  }

  private void createLevelFile(Path levelsPath, Level level) {
    Path levelPath = levelsPath.resolve(level.name());
    if (!levelPath.toFile().exists()) {
      boolean levelPathCreated;
      try {
        levelPathCreated = levelPath.toFile().createNewFile();
      } catch (IOException e) {
        throw new RuntimeException("Unable to create level file: " + e.getMessage());
      }
      if (!levelPathCreated) {
        throw new RuntimeException("Unable to create level file");
      }
    }
  }

  private void writeLevelContent(Path levelsPath, Level level) {
    Path levelPath = levelsPath.resolve(level.name());
    try {
      FileUtils.writeStringToFile(levelPath.toFile(), level.content(), "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException("Unable to write level content: " + e);
    }
  }

}
