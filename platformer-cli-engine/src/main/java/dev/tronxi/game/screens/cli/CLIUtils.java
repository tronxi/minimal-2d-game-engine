package dev.tronxi.game.screens.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIUtils {

  public static void clear() {
    try {
      Process process = Runtime.getRuntime().exec(new String[]{"clear"});
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      System.err.println("Error while clearing console");
      System.err.println(e.getMessage());
    }

  }


}
