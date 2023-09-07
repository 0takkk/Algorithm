class Solution {
     public static int solution(int n, int[] cores) {
        int answer = 0;

        long min = 0;
        long max = 500_000_000;

        long totalTime = 0;
        long doneWorkCount = 0;

        while(min <= max) {
            long mid = (min + max) / 2;

            long count = countWork(mid, cores);

            if(count >= n) {
                max = mid-1;
                totalTime = mid;
                doneWorkCount = count;
            }
            else {
                min = mid+1;
            }
        }

        long lastWorkCount = doneWorkCount - n;

        for(int i = cores.length-1; i >= 0; i--) {
            if(totalTime % cores[i] == 0) {
                if(lastWorkCount == 0) {
                    answer = i+1;
                    break;
                }
                lastWorkCount--;
            }
        }

        return answer;
    }

    public static long countWork(long time, int[] cores) {
        long count = cores.length;
        if(time == 0) return count;

        for (int core : cores) {
            count += (time / core);
        }

        return count;
    }
}