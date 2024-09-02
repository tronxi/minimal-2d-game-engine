package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import dev.tronxi.minimal2dgameengineapi.engine.usecases.services.ProjectFileRetriever;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class DownloadJarUseCase {

  private final ProjectFileRetriever projectFileRetriever;
  @Value("${engine.workspace}")
  protected String workspace;
  @Value("${engine.version}")
  protected String engineVersion;
  @Value("${engine.jar-directory}")
  protected String jarDirectory;

  public DownloadJarUseCase(ProjectFileRetriever projectFileRetriever) {
    this.projectFileRetriever = projectFileRetriever;
  }

  public Resource downloadJar(Project project) {
    File projectFile = projectFileRetriever.retrieveProjectFile(workspace, project);
    String jarName = projectFile.getName() + "-" + engineVersion + ".jar";
    try {
      Path jarPath = projectFile.toPath().resolve(jarDirectory).resolve(jarName);
      System.out.println(jarPath);
      if(!jarPath.toFile().exists()) {
        throw new RuntimeException("The JAR file does not exist at the expected path");
      }
      return new UrlResource(jarPath.toUri());
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error downloading jar: " + e);
    }
  }

}
