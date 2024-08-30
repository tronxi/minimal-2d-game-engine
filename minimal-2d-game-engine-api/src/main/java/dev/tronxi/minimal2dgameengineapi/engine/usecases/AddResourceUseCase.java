package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.PropertiesManager;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

public abstract class AddResourceUseCase {

  protected final ProjectFileRetriever projectFileRetriever;
  protected final PropertiesManager propertiesManager;
  @Value("${engine.workspace}")
  protected String workspace;

  public AddResourceUseCase(ProjectFileRetriever projectFileRetriever,
      PropertiesManager propertiesManager) {
    this.projectFileRetriever = projectFileRetriever;
    this.propertiesManager = propertiesManager;
  }

  protected void createResourcesPath(Path resourcesPath) {
    if (!resourcesPath.toFile().exists()) {
      boolean resourcesPathCreated = resourcesPath.toFile().mkdirs();
      if (!resourcesPathCreated) {
        throw new RuntimeException("Unable to create resource directory");
      }
    }
  }

  protected void createResourceFile(Path resourcesPath, String resourceName) {
    Path resourcePath = resourcesPath.resolve(resourceName);
    if (!resourcePath.toFile().exists()) {
      boolean resourcePathCreated;
      try {
        resourcePathCreated = resourcePath.toFile().createNewFile();
      } catch (IOException e) {
        throw new RuntimeException("Unable to create resource file: " + e.getMessage());
      }
      if (!resourcePathCreated) {
        throw new RuntimeException("Unable to create resource file");
      }
    }
  }

  protected void writeResourceContent(Path resourcesPath, String resourceName, String content) {
    Path resourcePath = resourcesPath.resolve(resourceName);
    try {
      FileUtils.writeStringToFile(resourcePath.toFile(), content, "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException("Unable to write resource content: " + e);
    }
  }

}
