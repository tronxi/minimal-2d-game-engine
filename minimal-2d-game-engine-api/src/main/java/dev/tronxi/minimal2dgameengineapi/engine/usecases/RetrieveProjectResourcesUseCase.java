package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.ElementClass;
import dev.tronxi.minimal2dgameengineapi.engine.model.Level;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.model.ProjectResources;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.PropertiesManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RetrieveProjectResourcesUseCase {

  private final ProjectFileRetriever projectFileRetriever;
  private final PropertiesManager propertiesManager;
  @Value("${engine.workspace}")
  protected String workspace;
  @Value("${engine.levels-directory}")
  private String levelsDirectory;
  @Value("${engine.custom-elements-package}")
  private String customElementsPackage;
  @Value("${engine.custom-elements-package-name}")
  private String customElementsPackageName;

  public RetrieveProjectResourcesUseCase(ProjectFileRetriever projectFileRetriever,
      PropertiesManager propertiesManager) {
    this.projectFileRetriever = projectFileRetriever;
    this.propertiesManager = propertiesManager;
  }

  public ProjectResources retrieve(Project project) {
    File projectFile = projectFileRetriever.retrieveProjectFile(workspace, project);
    List<Level> levels = retrieveLevels(projectFile);
    List<ElementClass> elementClasses = retrieveElementClasses(project, projectFile);
    return new ProjectResources(project, levels, elementClasses);
  }

  private List<Level> retrieveLevels(File projectFile) {
    File levelsFile = projectFile.toPath().resolve(levelsDirectory).toFile();
    if (!levelsFile.exists()) {
      throw new RuntimeException("Levels directory does not exist");
    }
    List<Level> levels = new ArrayList<>();
    File[] files = levelsFile.listFiles();

    if (files != null) {
      for (File file : files) {
        if (!file.isDirectory()) {
          levels.add(new Level(file.getName(), readFile(file)));
        }
      }
    }
    return levels;
  }

  private List<ElementClass> retrieveElementClasses(Project project, File projectFile) {
    File customElementsPackageFile = projectFile.toPath().resolve(customElementsPackage).toFile();
    if (!customElementsPackageFile.exists()) {
      throw new RuntimeException("CustomElementsPackage directory does not exist");
    }
    List<ElementClass> elementClasses = new ArrayList<>();
    File[] files = customElementsPackageFile.listFiles();

    if (files != null) {
      for (File file : files) {
        if (!file.isDirectory()) {
          elementClasses.add(
              new ElementClass(retrieveRepresentation(project, file.getName()), file.getName(),
                  readFile(file)));
        }
      }
    }
    return elementClasses;
  }

  private String retrieveRepresentation(Project project, String className) {
    Map<String, String> elementsDefinition = propertiesManager.retrieveElementsDefinition(project);
    for (Map.Entry<String, String> entry : elementsDefinition.entrySet()) {
      if (entry.getValue().equals(customElementsPackageName + className)) {
        return entry.getKey();
      }
    }
    return "";
  }

  private String readFile(File file) {
    try {
      return FileUtils.readFileToString(file, "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException("Error reading file: " + e);
    }
  }

}
