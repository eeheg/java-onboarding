package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem6 {

    //이중 List에서 닉네임만 추출하는 메서드
    //List<String> names : [제이엠, 제이슨, 워니, 엠제이, 이제엠]
    static List<String> getName(List<List<String>> forms) {

        List<String> names = new ArrayList<>();
        for (List<String> list : forms) {
            names.add(list.get(1));
        }

        System.out.println(names);
        return names;

    }

    //각 닉네임의 각 글자를 이중 List에 배열하는 메서드
    //List<List<Character>> digitsOfNames : [[제, 이, 엠], [제, 이, 슨], [워, 니], [엠, 제, 이], [이, 제, 엠]]
    static List<List<Character>> getDigitsOfNames (List<List<String>> forms) {
        List<String> names = getName(forms);
        List<List<Character>> digitsOfNames = new ArrayList<>();
        for (int i=0; i<names.size(); i++) {
            char[] nameDigits = names.get(i).toCharArray();  //char[] nameDigits = [제,이,엠]
            List<Character> digits = new ArrayList<>();
            for (char digit : nameDigits) {
                digits.add(digit);    //List<Character> digits = [제,이,엠]
            }
            digitsOfNames.add(digits);  //List<List<Character>> digitsOfNames = [[제,이,엠]]
        }

        System.out.println(digitsOfNames);
        return digitsOfNames;

    }

    //List<Character> digitsOfName : [제,이,엠]
    // 위 배열을 두 글자씩 붙여 새로운 배열 생성
    //List<String> twoWordsOfName : [제이, 이엠]
    static List<String> joinTwoWords (List<Character> digitsOfName) {
        List<String> twoWordsOfName = new ArrayList<>();
        for (int i=0; i<digitsOfName.size()-1; i++) {
            String twoWords = digitsOfName.get(i).toString() + digitsOfName.get(i+1).toString();
            twoWordsOfName.add(twoWords);  // [제이, 이엠]
        }
        return twoWordsOfName;
    }

    //digitsOfNames 배열에 반복문으로 joinTwoWords 메서드를 돌리는 메서드
    //List<List<String>> twoWordsOfNames : [[제이, 이엠], [제이, 이슨], [워니], [엠제, 제이], [이제, 제엠]]
    static List<List<String>> joinAllWords (List<List<Character>> digitsOfNames) {
        List<List<String>> twoWordsOfNames = new ArrayList<>();
        for (List<Character> digitsOfName : digitsOfNames) {
            twoWordsOfNames.add(joinTwoWords(digitsOfName));
        }
        System.out.println(twoWordsOfNames);
        return twoWordsOfNames;
    }



    //같은 요소가 있는지 확인하여 form의 해당 인덱스의 이메일을 중복없이 추출하는 메서드
    //List<String> emails : [jason@email.com, jm@email.com, mj@email.com]
    static List<String> printEmails (List<List<String>> forms) {
        List<String> emails = new ArrayList<>();
        List<List<String>> twoWordsOfNames = joinAllWords(getDigitsOfNames(forms));

        for (int i=0; i<twoWordsOfNames.size(); i++) {
            for (int j=0; j<twoWordsOfNames.get(i).size(); j++) {
                for (int k=0; k<twoWordsOfNames.size(); k++) {

                    if (twoWordsOfNames.get(k).contains(twoWordsOfNames.get(i).get(j))  //같은 요소가 있는지
                            && !emails.contains(forms.get(k).get(0))  //이미 이메일 배열에 들어가 있는 요소는 아닌지
                            && i != k) {  //자기자신은 아닌지
                        emails.add(forms.get(k).get(0));
                    }

                }
            }
        }
        Collections.sort(emails);  //오름차순 정렬
        System.out.println(emails);
        return emails;
    }


    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = printEmails(forms);
        return answer;
    }

    public static void main (String[] args) {
        solution(List.of(
                List.of("jm@email.com", "제이엠"),
                List.of("jason@email.com", "제이슨"),
                List.of("woniee@email.com", "워니"),
                List.of("mj@email.com", "엠제이"),
                List.of("nowm@email.com", "이제엠")
        ));
    }
}
