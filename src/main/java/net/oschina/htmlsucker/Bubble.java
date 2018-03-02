package net.oschina.htmlsucker;

import java.util.Arrays;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/28 10:50
 * @version: 1.0
 * @Description:
 */
public class Bubble {

    static int[] s = {18, 3, 41, 5, 11, 13, 33, 6, 7, 1, 1, 0};

    // 冒泡排序
    public static void bubble() {
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length - i - 1; j++) {
                if (s[j] > s[j + 1]) {
                    int temp = s[j + 1];
                    s[j + 1] = s[j];
                    s[j] = temp;
                }
            }
        }
    }

    private static void print() {
        for (int i : s) {
            System.out.print(i + " ");
        }
    }

    // 快速排序
    public static void quickSort() {
        qsort(s, 0, s.length - 1);
    }

    private static void qsort(int[] s, int low, int high) {
        if (low < high) {
            int partition = partition(s, low, high);
            qsort(s, low, partition - 1);
            qsort(s, partition + 1, high);
        }
    }

    private static int partition(int[] s, int low, int high) {
        int s0 = s[low];
        while (low < high) {
            // 从后向前扫描
            while (low < high && s0 <= s[high]) high--;
            s[low] = s[high];
            // 从前向后扫描
            while (low < high && s[low] <= s0) low++;
            s[high] = s[low];
        }
        s[high] = s0;
        return low;
    }

    // 简单插入排序
    public static void insertSort() {
        for (int i = 1; i < s.length; i++) {
            int a = s[i];
            int j;
            for (j = i; j > 0 && s[j - 1] > a; j--) {
                s[j] = s[j - 1];
            }
            s[j] = a;
        }
    }

    // 选择排序
    public static void selectSort() {
        for (int i = 0; i < s.length - 1; i++) {
            int m = i;
            for (int j = i + 1; j < s.length; j++) {
                if (s[j] < s[m]) {
                    m = j;
                }
            }
            int temp = s[i];
            s[i] = s[m];
            s[m] = temp;
        }
    }

    public static void main(String[] args) {
//        bubble();
//        quickSort();
//        insertSort();
        selectSort();
        print();
    }

}
