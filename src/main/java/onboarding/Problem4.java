package onboarding;

import java.util.*;

/*
 * 아스키코드표
 *  A = 65 ~ Z = 90
 *  a = 97 ~ z = 122
 */

public class Problem4 {

    //메시지의 각 문자를 arrayList로 추출하는 메서드
    static List<Character> getWord (String word) {
        List<Character> words = new ArrayList<Character>();
        char[] messageWords = word.toCharArray();

        for (char c : messageWords) {
            words.add(c);
        }
        return words;
    }

    //모든 문자를 각각 아스키코드로 변환하는 메서드
    static List<Integer> getAscii(List<Character> words) {
        List<Integer> ascii = new ArrayList<>();
        for (char c : words) {
            int asciicode = (byte)c;
            ascii.add(asciicode);
        }
        return ascii;
    }

    //청개구리 사전의 아스키코드로 변환하는 메서드
    static List<Integer> getCode(List<Integer> ascii) {
        List<Integer> codes = new ArrayList<>();
        for (int i : ascii) {
            if (i==32) { //공백일 때
                codes.add(i);
            } else if (i<=90) { //대문자일 때
                codes.add(155 - i);
            } else if (i>=97) { //소문자일 때
                codes.add(219 - i);
            }
        }
        return codes;
    }

    //청개구리 사전의 아스키코드를 문자로 변환하는 메서드
    static String getFrogMessage(List<Integer> codes) {
        //char타입 arrayList에 (char)변환한 codes 삽입
        List<Character> frogWords = new ArrayList<>();
        for (int code : codes) {
            char frogWord = (char)code;
            frogWords.add(frogWord);
        }

        //각 char를 String으로 변환하고 하나의 문자열을 생성
        String FrogMessage = "";
        for (char s : frogWords) {
            FrogMessage += String.valueOf(s);
        }
        return FrogMessage;
    }

    public static String solution(String word) {
        String answer = getFrogMessage(getCode(getAscii(getWord(word))));
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        solution("I love you");
    }
}
