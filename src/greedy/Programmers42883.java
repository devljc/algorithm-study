package greedy;

import java.util.ArrayDeque;

public class Programmers42883 {

    public static String solution(String number, int k) {
        char[] numArray = number.toCharArray();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char digit : numArray) {
            while (!stack.isEmpty() && k > 0 && stack.peek() < digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String solution = solution("1231234", 3);
        System.out.println(solution);
    }


}
