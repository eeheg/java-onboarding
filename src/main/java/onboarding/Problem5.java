package onboarding;

import java.util.*;

public class Problem5 {

    // money의 각 자리수 추출 메서드
    static List<Integer> getMoneyDigits (int money) {
        List<Integer> moneyDigits = new ArrayList<>(); //금액의 자릿수
        moneyDigits.add((int)(money/Math.pow(10,6)));
        for(int i=5; i>=0; i--) {
            moneyDigits.add((int)(money/Math.pow(10,i))%10);
        }
        // moneyDigits.add((int)(money / 1000000));
        // moneyDigits.add((money / 100000) % 10);
        // moneyDigits.add((money / 10000) % 10);
        // moneyDigits.add((money / 1000) % 10);
        // moneyDigits.add((money / 100) % 10);
        // moneyDigits.add((money / 10) % 10);
        // moneyDigits.add((money % 10));

        return moneyDigits;
    }

    // 오만 원권, 만 원권, 오천 원권, 천 원권, 오백원 동전, 백원 동전, 오십원 동전, 십원 동전, 일원 동전 개수
    static List<Integer> getMoneyNum (int money) {
        List<Integer> moneyDigits = getMoneyDigits(money); //money의 각 자리수 배열
        List<Integer> numOfMoney = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0)); //각 화폐개수 배열 생성

        int tenThousand = moneyDigits.get(0)*100 + moneyDigits.get(1)*10 + moneyDigits.get(2); //만원단위수
        numOfMoney.set(0,(int)(tenThousand/5)); //5만 원
        numOfMoney.set(1, tenThousand%5); //1만 원
        numOfMoney.set(2, (int)(moneyDigits.get(3)/5)); //5천 원
        numOfMoney.set(3, moneyDigits.get(3)%5); //1천 원
        numOfMoney.set(4, (int)(moneyDigits.get(4)/5)); //5백 원
        numOfMoney.set(5, moneyDigits.get(4)%5); //1백 원
        numOfMoney.set(6, (int)(moneyDigits.get(5)/5)); //5십 원
        numOfMoney.set(7, moneyDigits.get(5)%5); //십 원
        numOfMoney.set(8, moneyDigits.get(6)); //일 원

        return numOfMoney;
    }

    static boolean isValidNumber (int money) {
        if (money < 1 || money > 1000000) {
            return false;
        }
        return true;
    }

    public static List<Integer> solution(int money) {
        try {
            if (!isValidNumber(money)) {
                throw new IllegalArgumentException("유효하지 않은 값이 입력되었습니다.");
            }
            List<Integer> answer = getMoneyNum(money);
            System.out.println(money + " 원");
            System.out.println(answer);
            return answer;
        } catch(IllegalArgumentException e) {
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }

    public static void main (String[] args) {
        // 1 ~ 1,000,000 자연수 무작위 생성
        int money = (int)(Math.random() * 999999)+1;
        solution(money);
    }
}