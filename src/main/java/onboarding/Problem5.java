package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem5 {
    // 출금하는 금액: 1 ~ 1,000,000 자연수 무작위 생성 메서드
    static int withdrawMoney () {
        int money = (int)(Math.random() * 999999)+1;
        return money;
    }

    // money의 각 자리수 추출 메서드
    static List<Integer> getMoneyDigits (int money) {

        List<Integer> moneyDigits = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0)); //금액의 자릿수

        moneyDigits.set(0, (int) (money / 1000000));
        moneyDigits.set(1, (int) (money / 100000) % 10);
        moneyDigits.set(2, (int) (money / 10000) % 10);
        moneyDigits.set(3, (int) (money / 1000) % 10);
        moneyDigits.set(4, (int) (money / 100) % 10);
        moneyDigits.set(5, (int) (money / 10) % 10);
        moneyDigits.set(6, (int) (money % 10));

        System.out.println(money + " 원");
        System.out.println(moneyDigits);
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

        System.out.println(numOfMoney);
        return numOfMoney;
    }

    public static List<Integer> solution(int money) {

        List<Integer> answer = getMoneyNum(money);
        return answer;

    }

    public static void main (String[] args) {
        // solution(withdrawMoney());
        solution(50237);
    }
}
