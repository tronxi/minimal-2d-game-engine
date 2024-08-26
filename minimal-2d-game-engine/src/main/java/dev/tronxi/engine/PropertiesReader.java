package dev.tronxi.engine;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

  public Properties read(String propertiesFiles) {
    Properties properties = new Properties();
    try  {
      java.net.URL url = ClassLoader.getSystemResource(propertiesFiles);
      properties.load(url.openStream());
    } catch (IOException e) {
      System.err.println("Error reading properties file");
      System.err.println(e.getMessage());
    }
    return properties;
  }


}
