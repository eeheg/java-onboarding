package onboarding;

public class Problem2_2 {

    //연속문자 제거 1 x 중복문자가 없을 때까지 do-while 반복
    static String removeConsecutiveLetter(String crypstogram) {
        StringBuilder sb = new StringBuilder(crypstogram);
        boolean foundDuplicate;
        do {
            foundDuplicate = false;
            for (int i=0; i<sb.length()-1; i++) {
                if (sb.charAt(i) == sb.charAt(i+1)) {
                    sb.delete(i, i+2);
                    foundDuplicate = true;
                    break;
                }
            }
        } while (foundDuplicate);
        return sb.toString();
    }

    public static String solution(String cryptogram) {
        String answer =  removeConsecutiveLetter(cryptogram);
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        solution("zyelleyz");
    }
}
