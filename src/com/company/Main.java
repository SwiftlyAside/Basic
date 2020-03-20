package com.company;

import java.util.HashMap;

/*
* Basic Self-exercIse Cource
* yes
*
* */
class Main {
    private static String[] par = {"mislav", "stanko", "mislav", "ana"};
    private static String[] com = {"stanko", "ana", "mislav"};
    private static int[] questions = {1, 3, 2, 4, 2};

    public static void main(String[] args) {
        System.out.println(solutionOfMarathon(par, com));
        System.out.println(solutionOfExam(questions));
    }

    /*
     HashMap을 이용
    */
    public static String solutionOfMarathon(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String player : participant)
            map.put(player, map.getOrDefault(player, 0) + 1);
        for (String complete : completion)
            map.put(complete, map.get(complete) - 1);
        for (String key : map.keySet()){
            if (map.get(key) != 0)
                return key;
        }
        return null;
    }

    /*
    * 답안지를 주면 점수가 가장 높은 사람이 튀어나옵니다
    * 각 정답자는 맵으로 표시
    * 정답이 '아닐'때마다 -1
    * 값이 0인 사람이 최고점
    *
    * */
    public static int[] solutionOfExam(int[] answers) {
        int[] man1 = {1, 2, 3, 4, 5};
        int[] man2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] man3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] count = new int[3];

        int i = 0;
        for (int answer : answers) {
            if (answer == man1[i % 5])
                man1[0]++;
            if (answer == man2[i % 8])
                man2[1]++;
            if (answer == man3[i % 10])
                man3[2]++;
            i++;
        }
        return null;
    }
}

