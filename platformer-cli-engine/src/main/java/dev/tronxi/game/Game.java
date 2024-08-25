package dev.tronxi.game;

import dev.tronxi.game.screens.Screen;
import dev.tronxi.game.screens.cli.CLIScreen;

public class Game {

  private final Screen screen;

  public Game() {
    this.screen = new CLIScreen();
  }

  public void start() {
    screen.print();
  }

}
