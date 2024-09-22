package dev.tronxi.engine.elements;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tronxi.engine.Game;
import dev.tronxi.engine.common.EngineUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

public class ElementsGenerator {

    public List<Element> generate(Properties properties, Game game) {
        String elementsDefinition = properties.getProperty("elementsDefinition");
        String fileLevel = properties.getProperty("fileLevel");
        Map<String, String> elementsMap = parseElementsDefinition(elementsDefinition);
        return readFile(fileLevel, elementsMap, game);
    }

    public List<Element> generateForLevel(Properties properties, Game game, String fileLevel) {
        String elementsDefinition = properties.getProperty("elementsDefinition");
        Map<String, String> elementsMap = parseElementsDefinition(elementsDefinition);
        return readFile(fileLevel, elementsMap, game);
    }

    private List<Element> readFile(String fileLevel, Map<String, String> elementsMap, Game game) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("levels/" + fileLevel);
        List<Element> elements = new CopyOnWriteArrayList<>();
        if (inputStream == null) {
            System.err.println("Level not found");
            EngineUtils.exitEngine();
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int height = 0;
            int width;
            while ((line = reader.readLine()) != null) {
                for (width = 0; width < line.length(); width++) {
                    String representation = Character.toString(line.charAt(width));
                    if (elementsMap.containsKey(representation)) {
                        Element element = createElementInstance(representation, new Position(width, height),
                                elementsMap.get(representation), game);
                        elements.add(element);
                    }
                }
                height++;
                game.dimension().setHeight(height);
                game.dimension().setWidth(width);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            EngineUtils.exitEngine();
        }
        return elements;
    }

    private Element createElementInstance(String representation, Position position, String className,
                                          Game game) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?>[] paramTypes = {String.class, Position.class, Game.class};
            var constructor = clazz.getConstructor(paramTypes);
            Object[] initArgs = {representation, position, game};
            return (Element) constructor.newInstance(initArgs);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            EngineUtils.exitEngine();
            return null;
        }
    }

    private Map<String, String> parseElementsDefinition(String elementsDefinition) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, String>> elements = objectMapper.readValue(elementsDefinition,
                    new TypeReference<>() {
                    });

            Map<String, String> representationMap = new HashMap<>();
            for (Map<String, String> element : elements) {
                String representation = element.get("representation");
                String className = element.get("className");
                representationMap.put(representation, className);
            }
            return representationMap;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            EngineUtils.exitEngine();
            return null;
        }
    }

}
