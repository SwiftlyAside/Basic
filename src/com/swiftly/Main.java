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
    private static String[] par = {"mislav", "stanko", "mislav", "ana"};
    private static String[] com = {"stanko", "ana", "mislav"};
    private static int[] questions = {1, 2, 3, 4, 5} /*{1, 3, 2, 4, 2}*/,
            array = {1, 5, 2, 6, 3, 7, 4};
    private static int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

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
        for (String key : map.keySet()){
            if (map.get(key) != 0)
                return key;
        }
        return null;
    }


    /*
    * HashMap OR Array
    * */
    public static int[] solutionOfExam(int[] answers) {
        int[]   man1 = {1, 2, 3, 4, 5},
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
        // 전체 학생의 수: 2 <= n <= 30
        // lost는 체육복을 잃어버린 학생의 번호
        // reserve는 여벌의 체육복이 있는 학생의 번호
        // lost는 -
        // reserve는 lost된 값을 +
        // lost와 reserve 모두 해당하는 학생은 체육복을 빌려줄 수 없다.
        int answer = n;
        // HashMap 생성
        HashMap<Integer, Integer> students = new HashMap<>();
        // 여벌보유자는 1, 아닌 사람은 0
        for (int i = 1; i <= n; i++)
            students.put(i, 0);

        for (int student : students.keySet()) {
            for (int value : reserve) {
                if (student == value){
                    students.put(student, students.get(student) + 1);
                }
            }
        }

        // lost => 여벌을 가진 사람은 여벌(value)을 0으로.
        for (int student : students.keySet()) {
            for (int value : lost) {
                if (student == value){
                    if (students.get(student) == 0) answer--;
                    students.put(student, 0);
                    System.out.println("lost: " + students.get(student));
                }
            }
        }

        // 여벌을 n까지 채움
        // reserve 중복검사와 처리
        for (int student : students.keySet()) {
            for (int value : reserve) {
                if (answer == n) break;
                if (student == value && students.get(student) == 1) {
                    System.out.println("reserve:" +students.get(student));
                    answer++;
                }
            }
        }
        // 결과 반환
        return answer;
    }
}

