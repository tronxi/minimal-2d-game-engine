package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuildJarUseCase {

  @Value("${engine.workspace}")
  private String workspace;

  public void build(Project project) {
    File projectFile = Paths.get(workspace).resolve(project.name()).toFile().getAbsoluteFile();
    if (!projectFile.exists()) {
      throw new RuntimeException("Project not exist");
    }

    try {
      Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "mvn clean install"}, null, projectFile);
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Error building jar: " + e);
    }

  }

}
