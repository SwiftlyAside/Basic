package com.swiftly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
 * Basic Self-exercIse Course
 * Oh yeah. Sounds fun?
 *
 * */
class Main {
    private static final String[] par = {"mislav", "stanko", "mislav", "ana"};
    private static final String[] com = {"stanko", "ana", "mislav"};
    private static final int[] questions = {1, 2, 3, 4, 5} /*{1, 3, 2, 4, 2}*/;
    private static final int[] array = {1, 5, 2, 6, 3, 7, 4};
    private static final int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

    public static void main(String[] args) {
//        System.out.println(solutionOfMarathon(par, com));
//        System.out.println(Arrays.toString(solutionOfExam(questions)));
//        System.out.println(Arrays.toString(solutionOfKthValue(array, commands)));
        System.out.println(solutionOfUniform(5, new int[]{2, 4}, new int[]{1, 3, 5}));
        System.out.println(solutionOfUniform(5, new int[]{2, 4}, new int[]{3}));
        System.out.println(solutionOfUniform(6, new int[]{1, 2, 4, 6}, new int[]{2, 3, 5}));
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
        for (String key : map.keySet()) {
            if (map.get(key) != 0)
                return key;
        }
        return null;
    }


    /*
     * HashMap OR Array
     * */
    public static int[] solutionOfExam(int[] answers) {
        int[] man1 = {1, 2, 3, 4, 5},
                man2 = {2, 1, 2, 3, 2, 4, 2, 5},
                man3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] count = new int[3];

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

        // Add mans to ArrayList
        i = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int val : count) {
            if (val == max)
                temp.add(i);
            i++;
        }

        // Copy values to Primitive Array
        i = 0;
        int[] res = new int[temp.size()];
        for (int r : temp)
            res[i++] = r + 1;
        return res;
    }

    public static int[] solutionOfKthValue(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int j = 0;
        // command = array를 x ~ y 까지 자르고 정렬한 다음, z번째 원소 출력
        // 모든 command 수행이 완료될 때 까지 반복
        for (int[] command : commands) {
            // Cut From x to y
            // 자른다 -> 특정 범위원소만 추가한다
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = command[0] - 1; i < command[1]; i++)
                temp.add(array[i]);

            // Sort
            Collections.sort(temp);
            System.out.println(temp.size());

            // z를 정답 배열에 추가
            answer[j] = temp.get(command[2] - 1);
            j++;
        }

        // 배열 반환
        return answer;
    }

    public static int solutionOfUniform(int n, int[] lost, int[] reserve) {
        int[] people = new int[n];
        int answer = n;

        for (int l : lost)
            people[l - 1]--;
        for (int r : reserve)
            people[r - 1]++;

        for (int i = 0; i < people.length; i++) {
            if (people[i] == -1) {
                if (i - 1 >= 0 && people[i - 1] == 1) {
                    people[i]++;
                    people[i - 1]--;
                } else if (i + 1 < people.length && people[i + 1] == 1) {
                    people[i]++;
                    people[i + 1]--;
                } else
                    answer--;
            }
        }
        return answer;
    }
}

