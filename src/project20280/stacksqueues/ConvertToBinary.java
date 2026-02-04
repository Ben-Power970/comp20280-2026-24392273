package project20280.stacksqueues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertToBinary {
    static String convertToBinary(long dec_num) {
        ArrayStack<Long> stack = new ArrayStack<>();
        while (dec_num != 0) {
            stack.push(dec_num % 2);
            dec_num /= 2;
        }
        StringBuilder binary = new StringBuilder();
        while (!stack.isEmpty()) {
            binary.append(stack.pop());
        }
        return binary.toString();
    }

    @Test
    void testConvertToBinary() {
        assertEquals("10111", convertToBinary(23));
        assertEquals("111001000000101011000010011101010110110001100010000000000000",
                convertToBinary(1027010000000000000L));
    }
}

