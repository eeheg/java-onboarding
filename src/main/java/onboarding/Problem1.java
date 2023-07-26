package onboarding;

import java.util.*;

public class Problem1 {

    //책을 펼쳐서 나오는 페이지 번호 생성 메서드
    static void createPageNum(List<Integer> user) {
        int pageNum = (int) (Math.random() * 396) + 3;

        if (pageNum % 2 == 1) {
            user.set(0, pageNum);
            user.set(1, user.get(0) + 1);
        } else {
            user.set(1, pageNum);
            user.set(0, user.get(1) - 1);
        }
        System.out.print(user);
    }

    //페이지 번호의 각 자릿수 추출 매서드
    static List<Integer> getDigitOfPage(int pageNum) {
        List<Integer> digits = new ArrayList<>(Arrays.asList(1, 2, 3));
        digits.set(2, pageNum % 10);  //일의 자리수
        digits.set(1, ((pageNum - digits.get(2)) / 10) % 10);  //십의 자리수
        digits.set(0, (int) (pageNum / 100));  //백의 자리수
        return digits;

    }

    //각 자릿수 합 메서드
    static int getDigitSum(List<Integer> digits) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += digits.get(i);
        }
        return sum;
    }

    //각 자리수 곱 메서드
    static int getDigitMult(List<Integer> digits) {

        int mult = 1;
        if (digits.get(0) == 0) {
            for (int i = 1; i < 3; i++) {
                mult *= digits.get(i);
            }
        } else if (digits.get(0) > 0) {
            for (int i = 0; i < 3; i++) {
                mult *= digits.get(i);
            }
        }
        return mult;
    }

    //최대값 메서드
    static int getMaxScore(List<Integer> user) {
        List<Integer> Num = new ArrayList<>();
        Num.add(getDigitSum(getDigitOfPage(user.get(0))));
        Num.add(getDigitSum(getDigitOfPage(user.get(1))));
        Num.add(getDigitMult(getDigitOfPage(user.get(0))));
        Num.add(getDigitMult(getDigitOfPage(user.get(1))));
        return Collections.max(Num);
    }

    //solution: getMaxScore값 비교하여 승패 return
    static int solution(List<Integer> pobi, List<Integer> crong) {
        if (pobi.get(0) + 1 != pobi.get(1) || crong.get(0) + 1 != crong.get(1)) {
            return -1;
        } else if (getMaxScore(pobi) > getMaxScore(crong)) {
            return 1;
        } else if (getMaxScore(pobi) < getMaxScore(crong)) {
            return 2;
        } else if (getMaxScore(pobi) == getMaxScore(crong)) {
            return 0;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        //각 user의 길이2 배열 생성
        List<Integer> pobi = new ArrayList<Integer>(Arrays.asList(1, 2));
        List<Integer> crong = new ArrayList<Integer>(Arrays.asList(1, 2));

        //user가 책을 펼침
        createPageNum(pobi);
        createPageNum(crong);

        //solution: 승패 출력
        System.out.print(solution(pobi, crong));
    }
}
