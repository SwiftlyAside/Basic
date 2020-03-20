package com.company;

import java.util.HashMap;

class Main {
    private static String[] par = {"mislav", "stanko", "mislav", "ana"};
    private static String[] com = {"stanko", "ana", "mislav"};


    public static void main(String[] args) {
        System.out.println(solution(par, com));
    }

    /*
     HashMap을 이용
    */
    public static String solution(String[] participant, String[] completion) {
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
}

