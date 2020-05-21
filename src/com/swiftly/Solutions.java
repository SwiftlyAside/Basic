package com.swiftly;

import java.util.*;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class Solutions {


    int result = 0;
    int len, t;

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

    public boolean solutionOfPhone(String[] phone_book) {
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book.length; j++) {
                if (i != j && phone_book[j].startsWith(phone_book[i])) return false;
            }
        }
        return true;
    }

    public int solutionOfSpy(String[][] clothes) {
        /*int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            // 종류에 해당하는 값이 key로 적절함.
            String key = clothe[1];
            if (!map.containsKey(key))
                map.put(key, 1);

            // 중복인 경우 동일 종류의 value를 늘린다
            else
                map.put(key, map.get(key) + 1);
        }

        // 종류별 가짓수를 모두 곱함
        for (int i : map.values())
            answer *= i + 1;
        return answer - 1;*/

        // 같은 결과 괴악한 표현방법
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream()
                .reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
    }

    // 프테 : 다이얼
    public int solutionOfDial(String p, String s) {
        int answer = 0;
        for (int i = 0; i < p.length(); i++) {
            int x = Integer.parseInt(String.valueOf(p.charAt(i)));
            int y = Integer.parseInt(String.valueOf(s.charAt(i)));
            int through = Math.min(Math.floorMod(x - y, 10), Math.floorMod(y - x, 10));
            answer += through;
        }
        return answer;
    }

    // 프테: 로봇
    public int solutionOfRobot(int[][] office, int r, int c, String[] move) {
        // 처음에는 북쪽을 바라본다.
        final int NORTH = -5;
        final int SOUTH = -4;
        final int EAST = -3;
        final int WEST = -2;

        int answer = 0;
        int direction = NORTH;
        int idxX = c;
        int idxY = r;

        answer = clean(office, answer, idxX, idxY);

        for (String s : move) {
            switch (s) {
                case "go":
                    if (office[idxY][idxX] != -1) {
                        answer = clean(office, answer, idxX, idxY);
                        int nextX = 0;
                        int nextY = 0;
                        if (direction == NORTH)
                            nextY = -1;
                        else if (direction == EAST)
                            nextX = 1;
                        else if (direction == SOUTH)
                            nextY = 1;
                        else
                            nextX = -1;
                        if (idxX + nextX == -1 || idxX + nextX == office[idxY].length) break;
                        if (idxY + nextY == -1 || idxY + nextY == office.length) break;
                        if (office[idxY + nextY][idxX + nextX] == -1) break;
                        idxX += nextX;
                        idxY += nextY;
                        answer = clean(office, answer, idxX, idxY);
                    }
                    break;
                case "right":
                    if (direction == NORTH)
                        direction = EAST;
                    else if (direction == EAST)
                        direction = SOUTH;
                    else if (direction == SOUTH)
                        direction = WEST;
                    else
                        direction = NORTH;
                    break;
                case "left":
                    if (direction == NORTH)
                        direction = WEST;
                    else if (direction == WEST)
                        direction = SOUTH;
                    else if (direction == SOUTH)
                        direction = EAST;
                    else
                        direction = NORTH;
                    break;
            }
        }

        return answer;
    }

    private int clean(int[][] office, int answer, int idxX, int idxY) {
        if (office[idxY][idxX] != 0) {
            answer += office[idxY][idxX];
            office[idxY][idxX] = 0;
        }
        return answer;
    }

    public int solutionOfSwap(int[] numbers, int K) {
        int answer = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (Math.abs(numbers[i] - numbers[j]) > K) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                    answer++;
                }
            }
        }
        if (answer != 0) return answer;
        else return -1;
    }

    public String[] solutionOfChat(String[] record) {
        Map<String, String> id = new HashMap<>();
        List<String[]> enter = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (String rec : record) {
            String[] temp = rec.split(" ");
            enter.add(new String[]{temp[0], temp[1]});
            if (temp.length == 3) id.put(temp[1], temp[2]);
        }
        for (String[] s : enter) {
            if ("Enter".equals(s[0]))
                list.add(id.get(s[1]) + "님이 들어왔습니다.");
            else if ("Leave".equals(s[0]))
                list.add(id.get(s[1]) + "님이 나갔습니다.");
        }
        return list.toArray(String[]::new);
    }

    public int solution(int[] scoville, int K) {
        int answer = 2;
        Queue<Integer> integers = new LinkedList<>();
        for (int s : scoville)
            integers.add(s);
        // 원소가 하나가 될 때까지
        // 찾고 섞기
        while (!(integers.size() == 1)) {
            int min1 = 99, min2;
            for (Integer i : integers) {
                min2 = min1;
                min1 = Math.min(min1, i);
            }
            integers.remove(min1);
        }

        return -1;
    }

    public int solutionOfTarget(int[] numbers, int target) {
        len = numbers.length;
        t = target;
        search(numbers, 0, 0);
        return result;
    }

    public void search(int[] numbers, int sum, int n) {
        if (n == len) {
            if (t == sum) result++;
            return;
        }
        search(numbers, sum + numbers[n], n + 1);
        search(numbers, sum - numbers[n], n + 1);
    }

    // 뉴스 클러스터링
    public int solutionOfClustering(String str1, String str2) {
        List<String> subset1 = subset(str1.toUpperCase());
        List<String> subset2 = subset(str2.toUpperCase());
        List<Integer> checkList = new ArrayList<>();

        int union, intersection = 0;

        for (String s1 : subset1) {
            for (int y = 0; y < subset2.size(); y++) {
                String s2 = subset2.get(y);
                if (s1.equals(s2) && !checkList.contains(y)) {
                    intersection++;
                    checkList.add(y);
                    break;
                }
            }
        }

        union = subset1.size() + subset2.size() - intersection;
        return union == 0 && intersection == 0 ? 65536 : (int) (((double) intersection / union) * 65536);

    }

    public List<String> subset(String string) {
        List<String> list = new ArrayList<>();
        char[] chars = string.toCharArray();
        String str;
        int i = 0, j = 1;

        while (j <= chars.length - 1) {
            str = String.valueOf(chars[i]) + chars[j];
            boolean isMatch = Pattern.matches("^[A-Z]*$", str);
            if (isMatch)
                list.add(str);
            i++;
            j++;
        }
        return list;
    }

    public int solutionOfMini(int[] nums) {
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (primality(nums[i] + nums[j] + nums[k]))
                        answer++;
                }
            }
        }

        return answer;
    }

    public boolean primality(int n) {
        int i = 2;
        while (i * i <= n) {
            if (n % i == 0) return false;
            i++;
        }
        return true;
    }

    public int solutionOfSaver(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0;
        int i = 0, j = people.length - 1;
        while (i <= j) {
            if (i == j) {
                count++;
                break;
            }
            if (people[i] + people[j] <= limit) i++;
            count++;
            j--;
        }
        return count;

        /*
        다른 방법: i와 보트 이동횟수의 관계를 분석하다 나온 듯한 방법
        for (; i < j; --j) {
            if (people[i] + people[j] <= limit)
                ++i;
        }
        return people.length - i;
        */
    }

    public String solutionOfBigNum(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int index = 0;
        char max;
        if (number.charAt(index) == '0') return "0";
        for (int i = 0; i < number.length() - k; i++) {
            max = '0';
            for (int j = index; j <= i + k; j++) {
                if (max < number.charAt(j)) {
                    max = number.charAt(j);
                    index = j + 1;
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }

    public int[] solutionOfTower(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int sender, receiver;
        int senderLength = heights.length;
        int[] answer = new int[senderLength];
        for (int height : heights) stack.push(height);

        for (int i = senderLength - 1; i >= 0; i--) {
            sender = stack.pop();
            int num = 0;
            while (!stack.empty()) {
                receiver = stack.pop();
                num++;
                if (receiver > sender) {
                    answer[i] = i - num + 1;
                    break;
                }
            }
            for (int j = i - num; j < i; j++)
                stack.push(heights[j]);
        }
        return answer;
    }

    public int solutionOfTournament(int n, int a, int b) {
        int answer = 0;
        while (a != b) {
            a = a % 2 == 1 ? a / 2 + 1 : a / 2;
            if (a <= 0) a = 1;
            b = b % 2 == 1 ? b / 2 + 1 : b / 2;
            if (b <= 0) b = 1;
            answer++;
        }

        return answer;
    }
}
