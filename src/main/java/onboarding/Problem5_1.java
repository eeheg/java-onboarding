package onboarding;

import java.util.*;

public class Problem5_1 {
    static List<Integer> getMoneyNum (int money) {
        int[] currencyUnits = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 1 };
        List<Integer> numOfMoney = new ArrayList<>();
        for (int unit : currencyUnits) {
            int count = money / unit;
            numOfMoney.add(count);
            money %= unit;
        }
        return numOfMoney;
    }

    public static List<Integer> solution(int money) {
        List<Integer> answer = getMoneyNum(money);
        return answer;
    }

    public static void main (String[] args) {
        int money = (int)(Math.random() * 999999)+1;  //1 ~ 1,000,000 자연수 무작위 생성 메서드
        System.out.println(money+" 원");
        solution(money);
    }
}