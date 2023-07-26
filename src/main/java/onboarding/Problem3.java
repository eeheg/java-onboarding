package onboarding;

import java.util.*;

// 1이상 10,000이하 자연수 생성
class Number {
    int number = (int) (Math.random() * 9999) + 1;
}

public class Problem3 {

    //number의 자릿수 추출
    static List<Integer> getDigits (int number){
        List<Integer> Digits = new ArrayList<>();
        Digits.add((int)(number/1000)); //천의자리수
        Digits.add((int)(number/100)%10); //백의자리수
        Digits.add((int)(number/10)%10); //십의자리수
        Digits.add(number%10);  //일의자리수
        return Digits;

    }

    //Digits 내 3,6,9 개수
    static int clap (List<Integer> Digits) {
        int claps =0;
        for (int i=0;i<4;i++) {
            if(Digits.get(i)==3 || Digits.get(i)==6 || Digits.get(i)==9) {
                claps++;
            }
        }
        return claps;
    }

    //1~number clap 반복문
    static int allClap (int number) {
        int allClaps = 0;
        for (int i=1; i<=number; i++) {
            allClaps += clap(getDigits(i));
        }
        return allClaps;
    }

    //solution
    public static int solution(int number) {
        int answer = allClap(number);
        return answer;
    }

    public static void main(String[] args) {
        Number creatNumber = new Number();
        int number = creatNumber.number;
        System.out.println("number: "+ number);
        System.out.println("clap: " + solution(number));
    }
}
