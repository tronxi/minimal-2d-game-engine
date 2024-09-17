package dev.tronxi.engine.elements.custom;

import dev.tronxi.engine.Game;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.elements.Element;

public class MainElement extends Element {

    public MainElement(String representation, Position position, Game game) {
        super(representation, position, game);
    }

    @Override
    public void start() {
        registerForHandleInput(inputListener());
    }

    @Override
    public void update() {
        if (millisecondsSinceLatestUpdate() >= 500) {
            resetLastUpdate();
            if (!hasRepresentationDown("#")) {
                position().down();
                if (position().getHeight() == dimension().height()) {
                    game().initElements();
                    dimension().setWidthVisibleCenter(0);
                }
            }
        }
    }

    @Override
    public void handleInput(String key) {
        switch (key) {
            case "D" -> {
                if (!hasRepresentationRight("#")) {
                    position().right();
                }
            }
            case "A" -> {
                if (!hasRepresentationLeft("#")) {
                    position().left();
                }
            }
            case "W" -> {
                if (hasRepresentationDown("#")) {
                    if (!hasRepresentationUp("#")) {
                        position().up();
                    }
                    if (!hasRepresentationUp("#")) {
                        position().up();
                    }
                    if (!hasRepresentationUp("#")) {
                        position().up();
                    }
                }
            }
            case "Q" -> {
                elements().add(new ShotElement(">", new Position(position().getWidth(), position().getHeight()), game()));
            }
        }
        dimension().setWidthVisibleCenter(position().getWidth());
    }

}