class Solution {
    public static String solution(String[] survey, int[] choices) {
        String answer = "";

        int[] score = new int[26];

        for(int i = 0; i < survey.length; i++) {
            String sur = survey[i];
            int choice = choices[i];

            if(choice < 4) {
                score[sur.charAt(0)-'A'] += (4-choice);
            }
            else if(choice > 4) {
                score[sur.charAt(1) - 'A'] += (choice-4);
            }
        }

        answer += produceResult('R', 'T', score);
        answer += produceResult('C', 'F', score);
        answer += produceResult('J', 'M', score);
        answer += produceResult('A', 'N', score);

        return answer;
    }

    public static String produceResult(char a, char b, int[] score) {
        int scoreA = score[a - 'A'];
        int scoreB = score[b - 'A'];

        char result;
        if(scoreA == scoreB) {
            result = a - b < 0 ? a : b;
            return result+"";
        }

        result = scoreA > scoreB ? a : b;
        return result+"";
    }
}