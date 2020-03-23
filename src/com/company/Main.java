package com.company;

import java.util.Arrays;
import java.util.HashMap;

/*
* Basic Self-exercIse Cource
* yes
*
* */
class Main {
    private static String[] par = {"mislav", "stanko", "mislav", "ana"};
    private static String[] com = {"stanko", "ana", "mislav"};
    private static int[] questions = {1, 2, 3, 4, 5} /*{1, 3, 2, 4, 2}*/;

    public static void main(String[] args) {

        System.out.println(solutionOfMarathon(par, com));
        System.out.println(Arrays.toString(solutionOfExam(questions)));
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
    * HashMap OR Array
    *
    * */
    public static int[] solutionOfExam(int[] answers) {
        int[] man1 = {1, 2, 3, 4, 5};
        int[] man2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] man3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] count = new int[3], res = new int[3];

        int i = 0;
        for (int answer : answers) {
            if (answer == man1[i % 5])
                count[0]++;
            if (answer == man2[i % 8])
                count[1]++;
            if (answer == man3[i % 10])
                count[2]++;
            i++;
        }

        // Evaluate Max Value
        int max = Math.max(count[0], Math.max(count[1], count[2]));
        i = 0;
        for (int val : count) {
            if (val == max)
                res[i] = i + 1;
                i++;
        }
        return res;
    }
}

