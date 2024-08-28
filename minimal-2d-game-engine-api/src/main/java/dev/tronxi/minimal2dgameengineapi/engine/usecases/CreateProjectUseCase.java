package dev.tronxi.minimal2dgameengineapi.engine.usecases;

import dev.tronxi.minimal2dgameengineapi.engine.model.Project;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class CreateProjectUseCase {

  @Value("${engine.path}")
  private String engine;

  @Value("${engine.workspace}")
  private String workspace;

  public void create(Project project) {
    Path enginePath = Paths.get(this.engine);
    Path workspacePath = Paths.get(this.workspace);
    Path projectPath = workspacePath.resolve(project.name());

    createProjectDirectory(projectPath);
    copyEngineToProjectDirectory(enginePath, projectPath);
    changeArtifactId(projectPath, project.name());
  }

  private void createProjectDirectory(Path projectPath) {
    boolean projectPathCreated = projectPath.toFile().mkdirs();

    if (!projectPathCreated) {
      throw new RuntimeException("Could not create project: " + projectPath);
    }
  }

  private void copyEngineToProjectDirectory(Path enginePath, Path projectPath) {
    try {
      FileUtils.copyDirectory(enginePath.toFile(), projectPath.toFile());
    } catch (IOException e) {
      throw new RuntimeException("Could not copy engine: " + e.getMessage());
    }
  }

  private void changeArtifactId(Path projectPath, String artifactId) {
    try {
      File pom = projectPath.resolve("pom.xml").toFile();
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(pom);
      doc.getDocumentElement().normalize();
      NodeList artifactIdList = doc.getElementsByTagName("artifactId");
      if (artifactIdList.getLength() > 0) {
        Node artifactIdElement = artifactIdList.item(0).getFirstChild();
        artifactIdElement.setTextContent(artifactId);
        artifactIdElement.setTextContent(artifactId);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(pom);
        transformer.transform(source, result);
      }
    } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
      throw new RuntimeException("Could not read pom: " + e.getMessage());
    }
  }

}
