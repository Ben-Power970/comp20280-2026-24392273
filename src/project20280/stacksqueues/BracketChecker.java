package project20280.stacksqueues;

import java.util.Scanner;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        ArrayStack<Character> stack = new ArrayStack<>();
        Scanner sc = new Scanner(input);
        sc.useDelimiter("");
        while (sc.hasNext()) {
            char ch = sc.next().charAt(0);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            if (ch == ')') {
                if (stack.isEmpty()) {
                    System.out.println("INVALID: Missing '('");
                    return;
                }
                char pop = stack.pop();
                if (pop != '(') {
                    System.out.println("INVALID: Missing '('");
                    return;
                }
            }
            if (ch == ']') {
                if (stack.isEmpty()) {
                    System.out.println("INVALID: Missing '['");
                    return;
                }
                char pop = stack.pop();
                if (pop != '[') {
                    System.out.println("INVALID: Missing '['");
                    return;
                }
            }
            if (ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("INVALID: Missing '{'");
                    return;
                }
                char pop = stack.pop();
                if (pop != '{') {
                    System.out.println("INVALID: Missing '{'");
                    return;
                }
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("INVALID: Unclosed brackets");
            return;
        }
        System.out.println("VALID");
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
                "{[()]}",
                "{[(])}",
                "{{[[(())]]}}",
                "][]][][[]][]][][[[",
                "(((abc))((d)))))",

        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}