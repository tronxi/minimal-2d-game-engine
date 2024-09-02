package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.ElementClass;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.PropertiesManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddElementClassUseCase extends AddResourceUseCase {

  @Value("${engine.custom-elements-package}")
  private String customElementsPackage;


  public AddElementClassUseCase(ProjectFileRetriever projectFileRetriever,
      PropertiesManager propertiesManager) {
    super(projectFileRetriever, propertiesManager);
  }

  public void add(Project project, ElementClass elementClass) {
    File projectFile = projectFileRetriever.retrieveProjectFile(workspace, project);
    Path elementClassesPath = projectFile.toPath().resolve(customElementsPackage);

    createResourcesPath(elementClassesPath);
    createResourceFile(elementClassesPath, elementClass.className());
    if (elementClass.content().isEmpty()) {
      writeDefaultClassContent(elementClassesPath, elementClass.className());
    } else {
      writeResourceContent(elementClassesPath, elementClass.className(), elementClass.content());
    }
    propertiesManager.addElementClass(project, elementClass);
  }

  private void writeDefaultClassContent(Path elementClassesPath, String className) {
    String classNameWithOutExtension = className;
    if (className.endsWith(".java")) {
      classNameWithOutExtension = className.substring(0, className.length() - ".java".length());
    }

    InputStream inputStream = getClass().getClassLoader()
        .getResourceAsStream("templates/DefaultElement");
    if (inputStream == null) {
      throw new IllegalStateException("Resource not found");
    }
    String defaultElementContent = readTemplate(inputStream, classNameWithOutExtension);
    writeResourceContent(elementClassesPath, className, defaultElementContent);
  }

  private String readTemplate(InputStream inputStream, String classNameWithOutExtension) {
    StringBuilder defaultElementTemplate = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = reader.readLine()) != null) {
        defaultElementTemplate.append(line);
        defaultElementTemplate.append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to read template file: ", e);
    }

    return defaultElementTemplate.toString().replace("${defaultName}", classNameWithOutExtension);
  }

}
