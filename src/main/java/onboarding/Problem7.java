package onboarding;

import java.util.*;

class Friend {
    String name;
    int score;
    Friend(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class Problem7 {
    //인덱스가 0이면 1을, 인덱스가 1이면 0을 반환하는 메서드
    static String switchIndex (List<String> list, String str) {
        int index = list.indexOf(str);
        switch (index) {
            case 0 :
                index = 1;
                break;
            case 1 :
                index = 0;
                break;
        }
        return list.get(index);
    }

    //user의 friend 배열 생성
    //friendUserKnow: [donut, shakevan]
    static List<String> getFriendsUserKnow (String user, List<List<String>> friends) {
        List<String> friendsUserKnow = new ArrayList<>();
        for (List<String> frd : friends) {
            if (frd.contains(user)) {
                friendsUserKnow.add(switchIndex(frd,user));
            }
        }
        return friendsUserKnow;
    }

    //사용자와 함께 아는 친구 배열 생성
    //List<String> friendUserKnowWith: [andole, jun, andole, jun]
    static List<String> getFriendUserKnowWith (String user, List<List<String>> friends) {
        List<String> friendsUserKnow = getFriendsUserKnow(user,friends);
        List<String> friendUserKnowWith = new ArrayList<>();
        for (String friendUserKnow : friendsUserKnow) {
            for (List<String> frd : friends) {
                if (frd.contains(friendUserKnow) && !switchIndex(frd,friendUserKnow).equals(user)) {
                    friendUserKnowWith.add(switchIndex(frd,friendUserKnow));
                }
            }
        }
        return friendUserKnowWith;
    }

    // 중복없이 friendNames 배열 생성 : friendUserKnowWith + visitors
    // List<String> friendNames: [andole, bedi, jun]
    static List<String> getFriendNames (List<String> friendUserKnowWith, List<String> visitors, List<String> friendsUserKnow) {

        List<String> friendNames = new ArrayList<>();
        for (String friend : friendUserKnowWith) {
            if (!friendNames.contains(friend)) {
                friendNames.add(friend);
            }
        }
        for (String visitor : visitors) {
            if (!friendNames.contains(visitor) && !friendsUserKnow.contains(visitor)) {
                friendNames.add(visitor);
            }
        }
        Collections.sort(friendNames);  //오름차순 정렬
        return friendNames;
    }

    //Friend 생성자로 Friend(name,score) 배열 생성
    // List<Friend> friendScore: [Friend(andole,0), Friend(bedi,0), Friend(jun,0)]
    static List<Friend> getFriendsScore (List<String> friendNames) {
        List<Friend> friendsScore = new ArrayList<>();
        for(String name : friendNames) {
            friendsScore.add(new Friend(name,0));
        }
        return friendsScore;
    }

    //점수부여
    static List<Friend> scorePoints (List<String> friendUserKnowWith, List<String> visitors, List<Friend>friendsScore) {
        for (Friend friendScore : friendsScore) {
            // name이 friendUserKnowWith에 해당되면 +10점
            for (String friend : friendUserKnowWith) {
                if (friend.equals(friendScore.name)) {
                    friendScore.score += 10;
                }
            }
            // name이 visitors에 해당되면 +1점
            for (String visitor : visitors) {
                if (visitor.equals(friendScore.name)) {
                    friendScore.score += 1;
                }
            }
        }
        return friendsScore;
    }

    // Friend(name,score) -> score로 오름차순 정렬 후 name 배열 생성
    // (andole, 20) (jun, 20) (bedi, 3)
    // friendRecommend: [andole, jun, bedi]
    static List<String> recommendFriends (List<Friend> friendsScore){
        // score 버블정렬
        for(int i=0; i<friendsScore.size()-1; i++) {
            if(friendsScore.get(i).score < friendsScore.get(i+1).score) {
                Collections.swap(friendsScore,i,i+1);
            }
        }
        //  name 배열 생성: friendRecommend
        List<String> friendRecommend = new ArrayList<>();
        for(Friend friendScore : friendsScore) {
            friendRecommend.add(friendScore.name);
            System.out.printf("(%s, %d) ", friendScore.name, friendScore.score);
        }
        System.out.println();
        System.out.println("friendRecommend: " + friendRecommend);
        return friendRecommend;
    }

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> friendUserKnowWith = getFriendUserKnowWith (user, friends);
        List<String> friendsUserKnow = getFriendsUserKnow(user,friends);
        List<Friend> friendsScore = getFriendsScore(getFriendNames (friendUserKnowWith, visitors, friendsUserKnow));
        List<String> answer = recommendFriends(scorePoints(friendUserKnowWith, visitors, friendsScore));
        return answer;
    }

    public static void main (String[] args) {
        String user = "mrko";
        List<List<String>> friends = List.of(
                List.of("donut", "andole"),
                List.of("donut", "jun"),
                List.of("donut", "mrko"),
                List.of("shakevan", "andole"),
                List.of("shakevan", "jun"),
                List.of("shakevan", "mrko")
        );
        List<String> visitors = List.of("bedi", "bedi", "donut", "bedi", "shakevan");
        List<String> result = List.of("andole", "jun", "bedi");

        solution(user, friends, visitors);
    }
}
