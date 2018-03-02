package net.oschina.htmlsucker;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import javax.swing.text.html.Option;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/26 16:18
 * @version: 1.0
 * @Description:
 */
public class Entity {

    private int id;
    private String name;

    public static void main(String[] args) {
        Entity name1 = new Entity().setId(1).setName("name1");
        Entity name2 = new Entity().setId(2).setName("name2");
        Entity name3 = new Entity().setId(3).setName("name3");
        List<Entity> entities = Arrays.asList(name1, name2, name3);
        Entity entity = entities.stream().max(Comparator.comparingInt(e -> e.getId())).get();
        System.out.println(entity);
        entities.stream().forEach(Entity::print);
        entities.stream().forEach(e -> e = null);
        System.out.println(entities);

        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));

        List<Term> termList = StandardTokenizer.segment("商品和服务");
        System.out.println(termList);

//        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
        String content = "在缺乏同步的情况下，模型还允许不一致的可见性。比如，得到一个对象的一个字段的最新值，同时得到这个对象的其他字段的过期的值。同样，可能读到一个引用变量的最新值，但读取到这个引用变量引用的对象的字段的过期值";
        List<String> keywordList = HanLP.extractKeyword(content, 3);
        System.out.println(keywordList);

        List<String> list = Collections.emptyList();

        InterfaceA.print("123");

        final String text = "就是要测试加解密！！abjdkhdkuasu!!@@@@";
        String encoded = Base64.getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println("加密后=" + encoded);

        final String decoded = new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8);
        System.out.println("解密后=" + decoded);

        // map操作
        // 不影响原来集合
        List<String> strings = Arrays.asList("jack", "rose", "peter");
        strings.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println(strings);
        List<String> collect = strings.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);
    }

    public int getId() {
        return id;
    }

    public Entity setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return Optional.ofNullable(name).orElse(null);
//        return name;
    }

    public Entity setName(String name) {
        this.name = name;
        return this;
    }

    void print() {
        System.out.println("print: " + this.name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
