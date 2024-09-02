package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.ElementClass;
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
    String defaultElementPath = ClassLoader.getSystemResource("templates/DefaultElement").getFile();
    File defaultElementFile = new File(defaultElementPath);
    try {
      String defaultElementTemplate = FileUtils.readFileToString(defaultElementFile, "UTF-8");
      String defaultElementContent = defaultElementTemplate.replace("${defaultName}",
          classNameWithOutExtension);
      writeResourceContent(elementClassesPath, className, defaultElementContent);
    } catch (IOException e) {
      throw new RuntimeException("Unable to read defaultElement template" + e);
    }
  }

}
