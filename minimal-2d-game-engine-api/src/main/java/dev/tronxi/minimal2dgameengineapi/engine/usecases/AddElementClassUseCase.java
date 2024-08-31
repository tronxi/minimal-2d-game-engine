package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.ElementClass;
import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.PropertiesManager;
import java.io.File;
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
    writeResourceContent(elementClassesPath, elementClass.className(), elementClass.content());
    propertiesManager.addElementClass(project, elementClass);
  }

}
