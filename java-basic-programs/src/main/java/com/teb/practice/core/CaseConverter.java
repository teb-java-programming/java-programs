package com.teb.practice.core;

import java.util.List;

public class CaseConverter {

    protected String camelCase(String input) {

        List<String> inputList = List.of(input.toLowerCase().split("[^a-zA-Z0-9]+"));
        StringBuilder output = new StringBuilder(inputList.getFirst().toLowerCase());

        for (String word : inputList.subList(1, inputList.size())) {
            output.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
        }

        return output.toString();
    }

    protected String kebabCase(String input) {

        return input.toLowerCase().replaceAll("[^a-zA-Z0-9]+", "-");
    }

    protected String pascalCase(String input) {

        List<String> inputList = List.of(input.toLowerCase().split("[^a-zA-Z0-9]+"));
        StringBuilder output = new StringBuilder();

        for (String word : inputList) {
            output.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
        }

        return output.toString();
    }

    protected String snakeCase(String input) {

        return input.toLowerCase().replaceAll("[^a-zA-Z0-9]+", "_");
    }

    protected String upperSnakeCase(String input) {

        return input.toUpperCase().replaceAll("[^a-zA-Z0-9]+", "_");
    }
}
