package algorithm;

import org.junit.Test;

/**
 * @author : liulq
 * @date: 创建时间: 2018/4/10 13:21
 * @version: 1.0
 * @Description:
 */
public class Knapsack {

    // 01背包二维数据实现
    // 伪代码
    // if w(i) > j , f(i,j) = f(i-1,j);
    // else f(i,j) = max{f(i-1,j), f(i-1,j-w(i)) + v(i)}
    @Test
    public void test() {
        int[] w = {6, 4, 3, 5, 7};
        int[] v = {1, 8, 5, 4, 3};
        int n = w.length;
        int c = 11;
        int[][] m = new int[n + 1][c + 1]; // 01背包最大值地图数据
        int[][] p = new int[n + 1][c + 1]; // i个物品容量为j时，物品j有没有放进去，用01表示
        for (int i = 1; i <= n; i++) { // 遍历物品个数
            for (int j = 1; j <= c; j++) { // 遍历容量
                if (w[i - 1] <= j) { // 如果第i个物品的重量小于当前背包重量，取最大值
                    int a = m[i - 1][j - w[i - 1]] + v[i - 1];
                    int b = m[i - 1][j];
                    m[i][j] = Math.max(a, b);
                    if (a > b) { // 代表，i个物品，容量为j的时候，物品i放进去了
                        p[i][j] = 1;
                    }
                }
            }
        }
        System.out.println(m[n][c]);

        int i = n;
        int j = c;
        while (i > 0) {
            if (p[i][j] == 1) { // 如果等于1，那么上一个状态是 p[i-1][j-w[i-1]]
                System.out.print(i + " ");
                j -= w[i - 1];
            }
            i--;  // 如果不等于1，上一个状态是 p[i-1,j]
        }

    }

}