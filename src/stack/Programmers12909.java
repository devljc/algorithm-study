package stack;


/**
 * 프로그래머스 - 올바른 괄호
 * 입력 값: 괄호 문자열, ex) "(()())", 100,000 이하의 자연수
 * 출력 값: 올바른 괄호 문자열이면 true, 아니면 false
 * <p>
 * 풀이:
 * 1. 스택을 사용하여 여는 괄호는 push, 닫는 괄호는 pop
 * 2. 스택이 비어있으면 올바른 괄호 문자열
 * 3. 스택이 비어있지 않으면 올바르지 않은 괄호 문자열
 */
public class Programmers12909 {

    private static boolean solution(String s) {
        int count = 0;
        if (s.charAt(0) == ')') return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (count > 0) {
                count--;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        String input = ")()(";
        boolean solution = solution(input);
        System.out.println(solution);
    }

}
