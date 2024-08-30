package dev.tronxi.minimal2dgameengineapi.engine.usecases.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tronxi.minimal2dgameengineapi.engine.model.ElementClass;
import dev.tronxi.minimal2dgameengineapi.engine.model.Level;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  public void addElementClass(Project project, ElementClass elementClass) {
    createPropertiesFileIfNotExist(project);
    Properties properties = getProperties(project);
    String elementClassFullName = "dev.tronxi.engine.elements.custom." + elementClass.className();
    Map<String, String> elementsDefinition = parseElementsDefinition(properties.getProperty("elementsDefinition"));
    elementsDefinition.put(elementClass.representation(), elementClassFullName);
    properties.setProperty("elementsDefinition", elementsDefinitionToJson(elementsDefinition));
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

  private Map<String, String> parseElementsDefinition(String elementsDefinition) {
    if (elementsDefinition == null || elementsDefinition.isEmpty()) {
      return new HashMap<>();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      List<Map<String, String>> elements = objectMapper.readValue(elementsDefinition,
          new TypeReference<>() {
          });

      Map<String, String> representationMap = new HashMap<>();
      for (Map<String, String> element : elements) {
        String representation = element.get("representation");
        String className = element.get("className");
        representationMap.put(representation, className);
      }
      return representationMap;
    } catch (Exception e) {
      return new HashMap<>();
    }
  }

  private String elementsDefinitionToJson(Map<String, String> elementsDefinition) {
    ObjectMapper objectMapper = new ObjectMapper();
    List<ElementClass> elements = new ArrayList<>();
    for (Map.Entry<String, String> entry : elementsDefinition.entrySet()) {
      elements.add(new ElementClass(entry.getKey(), entry.getValue(), ""));
    }
    try {
      return objectMapper.writeValueAsString(elements);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error parsing to json: " + e);
    }

  }

}
