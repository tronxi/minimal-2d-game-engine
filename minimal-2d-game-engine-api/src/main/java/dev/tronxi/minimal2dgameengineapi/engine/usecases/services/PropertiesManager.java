package dev.tronxi.minimal2dgameengineapi.engine.usecases.services;

import dev.tronxi.minimal2dgameengineapi.engine.model.Level;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesManager {

  private final ProjectFileRetriever projectFileRetriever;
  @Value("${engine.workspace}")
  private String workspace;

  public PropertiesManager(ProjectFileRetriever projectFileRetriever) {
    this.projectFileRetriever = projectFileRetriever;
  }

  public void setLevelName(Project project, Path levelsPath, Level level) {
    createPropertiesFileIfNotExist(project);
    Properties properties = getProperties(project);
    properties.setProperty("fileLevel", level.retrievePath(levelsPath).toString());
    saveProperties(project, properties);
  }

  private void createPropertiesFileIfNotExist(Project project) {
    Path propertiesFilePath = getPropertiesFilePath(project);
    if (!propertiesFilePath.toFile().exists()) {
      boolean propertiesFileCreated;
      try {
        propertiesFileCreated = propertiesFilePath.toFile().createNewFile();
      } catch (IOException e) {
        throw new RuntimeException("Unable to create properties file: " + e.getMessage());
      }
      if (!propertiesFileCreated) {
        throw new RuntimeException("Unable to create properties file");
      }
    }
  }

  private Properties getProperties(Project project) {
    Path propertiesFilePath = getPropertiesFilePath(project);
    try (FileInputStream inputStream = new FileInputStream(propertiesFilePath.toFile())) {
      Properties properties = new Properties();
      properties.load(inputStream);
      return properties;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void saveProperties(Project project, Properties properties) {
    Path propertiesFilePath = getPropertiesFilePath(project);
    try (FileOutputStream outputStream = new FileOutputStream(propertiesFilePath.toString())) {
      properties.store(outputStream, null);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Path getPropertiesFilePath(Project project) {
    return projectFileRetriever.retrieveProjectFile(workspace, project).toPath()
        .resolve("src/main/resources/engine.properties");
  }

}
