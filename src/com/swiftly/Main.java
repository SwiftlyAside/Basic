package com.swiftly;

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

    // 전화번호
    private static final String[] phoneBook = {"119", "97674223", "1195524421"};
    private static final String[] phoneBook2 = {"123", "456", "789"};
    private static final String[] phoneBook3 = {"12", "123", "1235", "567", "88"};

    // 위장
    private static final String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
    private static final String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};


    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        Solutions solutions = new Solutions();
        int solution = solutions.solutionOfSpy(clothes);
        System.out.println(solution);
//        System.out.println(solutionOfMarathon(par, com));
//        System.out.println(Arrays.toString(solutionOfExam(questions)));
//        System.out.println(Arrays.toString(solutionOfKthValue(array, commands)));
//        System.out.println(solutionOfUniform(5, new int[]{2, 4}, new int[]{1, 3, 5}));
//        System.out.println(solutionOfUniform(5, new int[]{2, 4}, new int[]{3}));
//        System.out.println(solutionOfUniform(6, new int[]{1, 2, 4, 6}, new int[]{2, 3, 5}));
    }
}

