package onboarding;

import java.util.*;

public class Problem4 {

    //문자열을 아스키코드 배열로 변환하는 메서드
    static List<Integer> convertWordsToCodes (String words) {
        List<Integer> codes = new ArrayList<>();
        char[] messageWords = words.toCharArray();
        for (char word : messageWords) {
            codes.add((int)word);
        }
        return codes;
    }

    //아스키코드 배열을 문자열로 변환하는 메서드
    static String convertCodesToWords (List<Integer> codes) {
        StringBuilder words = new StringBuilder();
        for(int code : codes) {
            words.append((char)code);
        }
        return words.toString();
    }

    /*
     * 아스키코드표
     *  A = 65 ~ Z = 90
     *  a = 97 ~ z = 122
     */

    //message의 아스키코드를 청개구리사전의 아스키코드로 변환하는 메서드
    static List<Integer> convertToFrogCode(List<Integer> codes) {
        List<Integer> frogCodes = new ArrayList<>();
        for (int code : codes) {
            if (code == 32) { //공백일 때
                frogCodes.add(code);
            } else if (code <= 90) { //대문자일 때
                frogCodes.add(155 - code);
            } else if (code >= 97) { //소문자일 때
                frogCodes.add(219 - code);
            }
        }
        return frogCodes;
    }

    public static String solution(String word) {
        String answer = convertCodesToWords(convertToFrogCode(convertWordsToCodes(word)));
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        solution("I love you");
    }
}