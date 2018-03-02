package net.oschina.htmlsucker;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/1 13:06
 * @version: 1.0
 * @Description:
 */
public class BaiduOcr {

    //设置APPID/AK/SK
    public static final String APP_ID = "10779479";
    public static final String API_KEY = "7fOs2zK1UfcXpkH3ID14LlMt";
    public static final String SECRET_KEY = "1IUrUHxakhEt14xSGq7OEMQeVPr9DTgU";

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "C:\\Users\\pc\\Desktop\\3.png";
//        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
//        System.out.println(res.toString(0));

        byte[] decode = Base64.getDecoder().decode("6L275YWD56eR5oqA");
        System.out.println(new String(decode, "utf-8"));
        byte[] encode = Base64.getEncoder().encode("中华人民共和国".getBytes());
        System.out.println(new String(encode));


    }
}
