package net.oschina.htmlsucker;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/6 16:07
 * @version: 1.0
 * @Description:
 */
public class ZZZ {

    public static void main(String[] args) {
        Collections.synchronizedMap(new HashMap<>());
        HashMap<Object, Object> hashMap = new HashMap<>();
        Object put = hashMap.put("1", 1);
        hashMap.get("1");

        System.out.println(String.format("hello , i'm %s , the result is %b", "jack", false));
        // Map  HashMap HashTable LinkedHashMap TreeMap
        // List ArrayList


    }
}
