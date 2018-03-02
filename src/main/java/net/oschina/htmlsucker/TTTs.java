package net.oschina.htmlsucker;

import org.junit.Test;
import sun.rmi.runtime.Log;

import java.time.temporal.Temporal;

/**
 * @author : liulq
 * @date: 创建时间: 2018/3/1 9:19
 * @version: 1.0
 * @Description:
 */
public class TTTs {

    static int[] s = {23, 1, 42, 19, 12, 1, 0, 3, 8, 3};

    public static void bubble() {
        for (int i = 1; i < s.length; i++) {
            int temp = s[i];
            int j = 0;
            for (j = i; j > 0 && s[j - 1] > temp; j--) {
                s[j] = s[j - 1];
            }
            s[j] = temp;
        }
    }

    public static void main(String[] args) {
        bubble();
        for (int i : s) {
            System.out.print(i + "  ");
        }
    }
}
