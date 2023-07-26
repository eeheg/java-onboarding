package onboarding;

import java.util.*;

public class Problem2 {

    //문자열을 ArrayList로 바꾸는 메서드
    static List<Character> StringToArray (String cryptogram) {
        List<Character> list = new ArrayList<Character>();
        char[] characterArray = cryptogram.toCharArray();
        for(char c : characterArray)
            list.add(c);
        return list;
    }

    //연속되는 문자 제거 1루프
    static void removeSameLetter (List<Character> strs) {
        for(int i=0; i<strs.size()-1; i++) {
            if (strs.get(i) == strs.get(i+1)) {
                strs.remove(i);
                strs.remove(i);
                break;
            }

        }
    }

    public static String solution(String cryptogram) {
        List<Character> strs = new ArrayList<Character>(StringToArray(cryptogram));
        int size = strs.size();
        for(int i=0; i<size; i++) {
            removeSameLetter(strs);
        }

        String answer = "";
        for(Character str : strs) {
            answer += str;
        }
        System.out.println(answer);

        return answer;
    }


    public static void main(String[] args) {
        solution("zyelleyz");
    }
}
