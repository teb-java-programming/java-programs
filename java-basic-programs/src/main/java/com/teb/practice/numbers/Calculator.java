package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.Character.isDigit;
import static java.lang.Double.parseDouble;
import static java.lang.System.out;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    private static double basicOperation() {

        List<Double> inputList = new ArrayList<>();
        double result;

        out.println("Select the operation");
        out.println("+ for Addition");
        out.println("- for Subtraction");
        out.println("* for Multiplication");
        out.println("/ for Division");
        out.println("% for Modulo");
        out.print("Your choice: ");
        String operationChoice = SCAN.next();

        // Press ⌘ + D (Mac) or Ctrl + Z (Windows) to terminate input loop
        out.println("Enter all numbers (limited to two for Modulo)");
        while (SCAN.hasNext()) {
            inputList.add(SCAN.nextDouble());
        }

        result =
                switch (operationChoice) {
                    case "+" -> inputList.stream().mapToDouble(Double::doubleValue).sum();
                    case "-" ->
                            inputList.stream()
                                    .skip(1)
                                    .reduce(inputList.getFirst(), (x, y) -> x - y);
                    case "*" -> inputList.stream().reduce(1.0, (x, y) -> x * y);
                    case "/" ->
                            inputList.stream()
                                    .skip(1)
                                    .reduce(
                                            inputList.getFirst(),
                                            (x, y) -> {
                                                if (y == 0)
                                                    throw new ArithmeticException(
                                                            "Division by zero not allowed");
                                                return x / y;
                                            });
                    case "%" -> {
                        if (inputList.size() > 2)
                            throw new IllegalArgumentException(
                                    "Invalid input size for " + operationChoice);
                        yield inputList.stream()
                                .skip(1)
                                .reduce(
                                        inputList.getFirst(),
                                        (x, y) -> {
                                            if (y == 0)
                                                throw new ArithmeticException(
                                                        "Division by zero not allowed");
                                            return x % y;
                                        });
                    }
                    default ->
                            throw new IllegalArgumentException(
                                    "Invalid operation " + operationChoice);
                };

        return valueOf(result).setScale(4, HALF_UP).doubleValue();
    }

    private static double expressionOperation() {

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        String input;

        out.println("Enter numbers and operations as a String (eg. x + y - z)");
        SCAN.nextLine();
        input = SCAN.nextLine();

        if (isNull(input) || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == ' ') continue;

            if (ch == '(') {
                operators.push(ch);
            } else if (isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < input.length() && (isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    sb.append(input.charAt(i));
                    i++;
                }
                i--;
                numbers.push(parseDouble(sb.toString()));
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyTopOperator(numbers, operators);
                }
                if (!operators.isEmpty()) {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {
                    applyTopOperator(numbers, operators);
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            applyTopOperator(numbers, operators);
        }

        if (numbers.size() != 1) {
            throw new IllegalStateException("Invalid expression");
        }

        return valueOf(numbers.pop()).setScale(4, HALF_UP).doubleValue();
    }

    private static int precedence(char op) {

        return (op == '+' || op == '-') ? 1 : 2;
    }

    private static void applyTopOperator(Stack<Double> numbers, Stack<Character> operators) {

        double b = numbers.pop();
        double a = numbers.pop();
        char op = operators.pop();

        numbers.push(op == '+' ? a + b : op == '-' ? a - b : op == '*' ? a * b : a / b);
    }

    public static void main(String[] args) {

        out.println("Select your option");
        out.println("1 for simple operations");
        out.println("2 for multiple operations");
        out.print("Your choice: ");
        int choice = SCAN.nextInt();

        switch (choice) {
            case 1 -> out.println("Result: " + basicOperation());
            case 2 -> out.println("Result: " + expressionOperation());
            default -> throw new IllegalArgumentException("Invalid operation " + choice);
        }
    }
}
