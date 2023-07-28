package onboarding;

import java.util.*;

public class Problem1 {
    //페이지 번호 무작위 생성 메서드
    static void createPageNum(List<Integer> user) {
        int pageNum = (int) (Math.random() * 396) + 3;

        if (pageNum % 2 == 1) {
            user.set(0, pageNum);
            user.set(1, user.get(0) + 1);
        } else {
            user.set(1, pageNum);
            user.set(0, user.get(1) - 1);
        }
    }

    //페이지 번호의 각 자릿수 추출 매서드
    static List<Integer> getDigitOfPage(int pageNum) {
        List<Integer> digits = new ArrayList<>(Arrays.asList(1, 2, 3));
        digits.set(2, pageNum % 10);  //일의 자리수
        digits.set(1, ((int)(pageNum / 10) % 10));  //십의 자리수
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
        if (digits.get(0) == 0) {  //100미만일 때
            for (int i = 1; i < 3; i++) {
                if (digits.get(1) == 0) {  //한자리 수 일 때
                    mult = digits.get(2);
                }
                mult *= digits.get(i);  //두자리 수 일 때
            }
        } else if (digits.get(0) > 0) {  //세자리 수 일 때
            for (int i = 0; i < 3; i++) {
                mult *= digits.get(i);
            }
        }
        return mult;
    }

    //최대값 메서드
    static int getMaxScore(List<Integer> user) {
        List<Integer> digitsOfLeftPage = getDigitOfPage(user.get(0));
        List<Integer> digitsOfRightPage = getDigitOfPage(user.get(1));
        List<Integer> Num = new ArrayList<>();
        Num.add(getDigitSum(digitsOfLeftPage));  //왼쪽 페이지 자리수 합
        Num.add(getDigitMult(digitsOfLeftPage)); //왼쪽 페이지 자리수 곱
        Num.add(getDigitSum(digitsOfRightPage)); //오른쪽 페이지 자리수 합
        Num.add(getDigitMult(digitsOfRightPage)); //오른쪽 페이지 자리수 곱
        return Collections.max(Num);  //최댓값 반환
    }

    //try-catch로 예외처리 및 승패 반환
    static int getBinaryResult (List<Integer> pobi, List<Integer> crong) {
        try {
            if (!isValidPageNumber(pobi)) {
                throw new IllegalArgumentException("유효하지 않은 페이지번호가 입력되었습니다.");
            }
            if (!isValidPageNumber(crong)) {
                throw new IllegalArgumentException("유효하지 않은 페이지번호가 입력되었습니다.");
            }

            int pobiMax = getMaxScore(pobi);
            int crongMax = getMaxScore(crong);

            if (pobiMax > crongMax) {
                return 1;
            } else if (pobiMax < crongMax) {
                return 2;
            } else if (pobiMax == crongMax) {
                return 0;
            } else {
                return -1;
            }
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }

    //예외처리 : 유효한 페이지번호인지 확인하는 메서드
    static boolean isValidPageNumber(List<Integer> user) {
        List<Integer> invalidPageNumber = new ArrayList<>(Arrays.asList(1,2,399,400));
        for (Integer pageNumber : user) {
            // 페이지 번호는 음수가 될 수 없다.
            if (pageNumber < 0) {
                return false;
            }
            //페이지 번호는 [1,2,399,400]가 될 수 없다.
            if (invalidPageNumber.contains(pageNumber)) {
                return false;
            }
        }
        // 오른쪽페이지 번호는 왼쪽페이지 번호에 1을 더한 값 이다.
        if (user.get(0)+1 != user.get(1)) {
            return false;
        }
        return true;
    }

    //solution: getMaxScore값 비교하여 승패 return
    static int solution(List<Integer> pobi, List<Integer> crong) {
        System.out.println("pobi: " + pobi);
        System.out.println("crong: " + crong);
        int answer = getBinaryResult (pobi, crong);
        System.out.println("result: " + answer);
        return answer;
    }

    public static void main(String[] args) {
        //각 user의 길이2 배열 생성
        List<Integer> pobi = new ArrayList<Integer>(Arrays.asList(1, 2));
        List<Integer> crong = new ArrayList<Integer>(Arrays.asList(1, 2));
        //user가 책을 펼침
        createPageNum(pobi);
        createPageNum(crong);
        solution(pobi, crong);
    }
}
