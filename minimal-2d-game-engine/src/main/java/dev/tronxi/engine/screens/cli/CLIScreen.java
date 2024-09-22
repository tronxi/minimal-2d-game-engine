package dev.tronxi.engine.screens.cli;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.elements.Position;
import dev.tronxi.engine.elements.Element;
import dev.tronxi.engine.listeners.InputListener;
import dev.tronxi.engine.screens.Screen;

import java.util.List;
import java.util.Optional;

public class CLIScreen extends Screen {

    public CLIScreen(Dimension dimension, List<Element> elements, InputListener inputListener) {
        super(dimension, elements, inputListener);
        registerListener(inputListener);
    }

    private void registerListener(InputListener inputListener) {
        CLIKeyListener CLIKeyListener = new CLIKeyListener(inputListener);
        GlobalScreen.addNativeKeyListener(CLIKeyListener);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void print() {
        CLIUtils.clear();
        for (int height = dimension.startVisibleEight(); height < dimension.endVisibleEight(); height++) {
            for (int width = dimension.startVisibleWidth(); width < dimension.endVisibleWidth(); width++) {
                Position currentPosition = new Position(width, height);
                if (dimension.isInDimension(currentPosition)) {
                    Optional<Element> maybeElement = findElementByPosition(currentPosition);
                    maybeElement.ifPresentOrElse(element -> System.out.print(element.representation()), () -> System.out.print(" "));
                }
            }
            System.out.println();
        }
        CLIUtils.sleep(10);
    }

}
