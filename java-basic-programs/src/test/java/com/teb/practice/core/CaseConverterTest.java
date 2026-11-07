package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CaseConverterTest {

    private final CaseConverter converter = new CaseConverter();

    static Stream<Arguments> caseConversionProvider() {

        return Stream.of(
                Arguments.of(
                        "hello world",
                        "helloWorld",
                        "hello-world",
                        "HelloWorld",
                        "hello_world",
                        "HELLO_WORLD"),
                Arguments.of(
                        "HELLO WORLD",
                        "helloWorld",
                        "hello-world",
                        "HelloWorld",
                        "hello_world",
                        "HELLO_WORLD"),
                Arguments.of(
                        "hello-world",
                        "helloWorld",
                        "hello-world",
                        "HelloWorld",
                        "hello_world",
                        "HELLO_WORLD"),
                Arguments.of(
                        "hello_world",
                        "helloWorld",
                        "hello-world",
                        "HelloWorld",
                        "hello_world",
                        "HELLO_WORLD"),
                Arguments.of(
                        "hello---world",
                        "helloWorld",
                        "hello-world",
                        "HelloWorld",
                        "hello_world",
                        "HELLO_WORLD"),
                Arguments.of(
                        "hello123 WORLD456",
                        "hello123World456",
                        "hello123-world456",
                        "Hello123World456",
                        "hello123_world456",
                        "HELLO123_WORLD456"));
    }

    @ParameterizedTest
    @MethodSource("caseConversionProvider")
    void shouldConvertCases(
            String input,
            String camelCase,
            String kebabCase,
            String pascalCase,
            String snakeCase,
            String upperSnakeCase) {

        assertEquals(camelCase, converter.camelCase(input));
        assertEquals(kebabCase, converter.kebabCase(input));
        assertEquals(pascalCase, converter.pascalCase(input));
        assertEquals(snakeCase, converter.snakeCase(input));
        assertEquals(upperSnakeCase, converter.upperSnakeCase(input));
    }
}
