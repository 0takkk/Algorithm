import java.util.ArrayList;

class Solution {
    public static int[] DISCOUNTS = {40, 30, 20, 10};
    public static int n, m;
    public static int[] discountRates;
    public static ArrayList<int[]> result;

    public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        n = users.length;
        m = emoticons.length;
        discountRates = new int[m];
        result = new ArrayList<>();

        setDiscountRates(0, users, emoticons);
        result.sort((o1, o2) -> {
            if(o2[0] == o1[0]) return o2[1] - o1[1];
            return o2[0] - o1[0];
        });

        answer[0] = result.get(0)[0];
        answer[1] = result.get(0)[1];

        return answer;
    }

    public static void setDiscountRates(int idx, int[][] users, int[] emoticons) {
        if(idx == m) {
            calTotalPrice(users, emoticons);
            return;
        }

        for (int discount : DISCOUNTS) {
            discountRates[idx] = discount;
            setDiscountRates(idx + 1, users, emoticons);
        }
    }

    public static void calTotalPrice(int[][] users, int[] emoticons) {
        int[] subResult = new int[2];
        for (int[] user : users) {
            int amount = 0;
            int buyDiscountRate = user[0];
            int buyEmoticonPlusAmount = user[1];

            for(int i = 0; i < m; i++) {
                int emoticonPrice = emoticons[i];
                int discountRate = discountRates[i];

                if(discountRate >= buyDiscountRate) {
                    amount += (emoticonPrice * (100 - discountRate) / 100);
                }
            }

            if(amount >= buyEmoticonPlusAmount) {
                subResult[0]++;
            }
            else {
                subResult[1] += amount;
            }
        }

        result.add(subResult);
    }
}