package dev.tronxi.engine.elements.custom;

import dev.tronxi.engine.Game;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.elements.Element;

public class ShotElement extends Element {

    public ShotElement(String representation, Position position, Game game) {
        super(representation, position, game);
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
        if (millisecondsSinceLatestUpdate() >= 200) {
            resetLastUpdate();
            if (!hasRepresentationRight("#")) {
                position().right();
            } else {
                remove();
            }
        }
    }

    @Override
    public void handleInput(String key) {

    }

}
