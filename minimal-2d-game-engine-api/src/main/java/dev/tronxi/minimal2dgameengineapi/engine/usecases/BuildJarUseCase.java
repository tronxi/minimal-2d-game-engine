package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuildJarUseCase {

  Logger logger = LoggerFactory.getLogger(BuildJarUseCase.class);


  @Value("${engine.workspace}")
  private String workspace;

  private final ProjectFileRetriever projectFileRetriever;

  public BuildJarUseCase(ProjectFileRetriever projectFileRetriever) {
    this.projectFileRetriever = projectFileRetriever;
  }

  public void build(Project project) {
    File projectFile = projectFileRetriever.retrieveProjectFile(workspace, project);

    try {
      Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "mvn clean install"}, null, projectFile);
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      StringBuilder errorMessage = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        logger.info(line);
        errorMessage.append(line);
      }
      int status = process.waitFor();
      if(status != 0) {
        throw new RuntimeException("Error building jar: " + errorMessage);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Error building jar: " + e);
    }

  }

}
