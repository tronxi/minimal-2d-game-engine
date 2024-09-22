package dev.tronxi.engine.elements;

import dev.tronxi.engine.Dimension;
import dev.tronxi.engine.Game;
import dev.tronxi.engine.Position;
import dev.tronxi.engine.listeners.InputListener;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class Element {

    private final Position position;
    private final String representation;
    private final Game game;
    private LocalDateTime lastUpdate;
    private String spriteName;

    public Element(String representation, Position position, Game game) {
        this.representation = representation;
        this.position = position;
        this.game = game;
        this.lastUpdate = LocalDateTime.now();
        this.spriteName = null;
    }

    public String representation() {
        return representation;
    }

    public Position position() {
        return position;
    }

    protected InputListener inputListener() {
        return game.inputListener();
    }

    protected List<Element> elements() {
        return game.elements();
    }

    protected Dimension dimension() {
        return game.dimension();
    }

    protected Game game() {
        return game;
    }

    public abstract void start();

    public abstract void update();

    public void handleInput(String key) {
    }

    protected void registerForHandleInput(InputListener inputListener) {
        inputListener.registerElement(this);
    }

    protected boolean hasRepresentationUp(String representation) {
        Position downPosition = new Position(position.getWidth(), position.getHeight() - 1);
        return game.elements().stream().anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(representation));
    }

    protected boolean hasRepresentationDown(String representation) {
        Position downPosition = new Position(position.getWidth(), position.getHeight() + 1);
        return game.elements().stream().anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(representation));
    }

    protected boolean hasRepresentationRight(String representation) {
        Position downPosition = new Position(position.getWidth() + 1, position.getHeight());
        return game.elements().stream().anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(representation));
    }

    protected boolean hasRepresentationLeft(String representation) {
        Position downPosition = new Position(position.getWidth() - 1, position.getHeight());
        return game.elements().stream().anyMatch(element -> element.position.equals(downPosition) && element.representation.equals(representation));
    }

    protected void remove() {
        game.elements().removeIf(element -> element.position.equals(this.position) && element.representation.equals(this.representation));
    }

    protected long millisecondsSinceLatestUpdate() {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(lastUpdate, now).toMillis();
    }

    protected void resetLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    protected void setSprite(String spriteName) {
        this.spriteName = spriteName;
        game().setSprite(spriteName);
    }

    public Optional<Image> getSprite() {
        return game().getSprite(spriteName);
    }

}
