package dev.tronxi.game.screens.cli;

import dev.tronxi.game.screens.Screen;

public class CLIScreen extends Screen {

  @Override
  public void print() {
    CLIUtils.clear();
      do {
        System.out.println("otras cosas");
        System.out.println("otras cosas");
        System.out.println("otras cosas");
        System.out.println("otras cosas");
        System.out.println("otras cosas");
        System.out.println("otras cosas");
        System.out.println("otras cosas");
        System.out.println("otras cosas");
        for(int i = 0; i < 1; i++)
          System.out.println();

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        CLIUtils.clear();

      }while(true);
  }
}
