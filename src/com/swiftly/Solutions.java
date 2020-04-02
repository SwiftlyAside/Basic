package com.swiftly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solutions {
    public String solutionOfMarathon(String[] participant, String[] completion) {
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

    public int[] solutionOfExam(int[] answers) {
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

    public int[] solutionOfKthValue(int[] array, int[][] commands) {
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

    public int solutionOfUniform(int n, int[] lost, int[] reserve) {
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

    public int solutionOfCrane(int[][] board, int[] moves) {
        /*

        crane은 임의 접근법으로 배열을 건드림
        0은 비어있음을 의미함
        숫자는 인형
        결과배열은 정렬하지않음
        0이 아닌 인접원소를 발견하면 서로 0이 됨
        더이상 찾을 수 없을때까지 반복
        똥카오는 너가 바라는 것만큼 친절하지 않습니다. 문제의 구린내를 느껴보고 힘껏 빡치십시오.

         */
        // 1 ~ 1000
        int[] popped = new int[1000];
        int idx = 0;

        //fuck 시발 라이언 시발


        // moves 각 원소마다 반복
        for (int i = 0; i < moves.length; i++) {
            // board[moves[i] - 1] 의 0이 아닌 마지막 값을 popped의 앞부터 채움
            // 응 틀림 그림하고 전혀다르구만
            for (int j = 0; j < board[moves[i] - 1].length; j++) {
                if (board[moves[i] - 1][j] != 0) {
                    popped[idx] = board[moves[i] - 1][j];
                    board[moves[i] - 1][j] = 0;
                    idx++;
                }
            }
            // popped idx와 같을 때 까지의 각 원소 검사
            if (idx != 0 && popped[idx] == popped[idx - 1]) {
                popped[idx] = 0;
                popped[idx - 1] = 0;
                idx -= 2;
                if (idx < 0) idx = 0;
            }
        }
        return idx;
    }
}
