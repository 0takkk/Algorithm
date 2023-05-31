class Solution {
    public static int[] answer, result;
    public static int max;

    public static int[] solution(int n, int[] info) {
        answer = new int[]{};
        result = new int[11];

        max = 0;

        dfs(10, n, info);
        if(answer.length == 0) answer = new int[]{-1};

        return answer;
    }

    public static void dfs(int idx, int cnt, int[] info){
        if(idx == -1 || cnt == 0){
            if(cnt > 0) result[10] += cnt;

            int apeach = 0, lion = 0;

            for(int i = 0; i < 11; i++){
                if(info[i] == 0 && result[i] == 0) continue;

                if(info[i] < result[i]) lion += (10 - i);
                else apeach += (10 - i);
            }

            int diff = lion - apeach;

            if(max < diff){
                max = diff;
                answer = result.clone();
            }

            if(cnt > 0) result[10] -= cnt;
            return;
        }

        int minCountForGain = info[idx]+1;

        if(cnt >= minCountForGain){
            result[idx] = minCountForGain;
            dfs(idx-1, cnt-minCountForGain, info);
            result[idx] = 0;
        }

        dfs(idx-1, cnt, info);
    }
}