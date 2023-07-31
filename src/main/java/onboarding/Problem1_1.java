package onboarding;

import java.util.*;

public class Problem1_1 {
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

    //페이지 번호의 각 자릿수 추출 매서드 (자리수만큼만 추출)
    static List<Integer> getDigitOfPage(int pageNum) {
        List<Integer> digitOfPage = new ArrayList<>();
        while (pageNum > 0) {
            digitOfPage.add(pageNum % 10);
            pageNum /= 10;
        }
        Collections.reverse(digitOfPage); // 순서 뒤집기
        return digitOfPage;
    }

    //각 자릿수 합,곱 중 max값 반환 메서드
    static int getDigitSumMult(List<Integer> digitOfPage) {
        int sum = 0;
        int mult = 1;
        for (int digit : digitOfPage) {
            sum += digit;
            mult *= mult;
        }
        return Math.max(sum,mult);
    }

    //양쪽 페이지 최대값 반환 메서드
    static int getMaxScore(List<Integer> user) {
        return Math.max(
                (getDigitSumMult(getDigitOfPage(user.get(0)))),
                getDigitSumMult(getDigitOfPage(user.get(1)))
        );
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
