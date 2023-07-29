package onboarding;

public class Problem2_1 {

    //연속문자 제거 1 x for반복문
    static String removeConsecutiveLetter(String crypstogram) {
        StringBuilder sb = new StringBuilder(crypstogram);
        int size = sb.length();
        for (int j=0; j<size; j++) {
            for (int i=0; i<sb.length()-1; i++) {
                if(sb.charAt(i) == sb.charAt(i+1)) {
                    sb.delete(i,i+2);
                    break;
                }
            }
        }
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
