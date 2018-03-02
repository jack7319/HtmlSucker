package net.oschina.htmlsucker;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.ImageIOHelper;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/31 14:50
 * @version: 1.0
 * @Description:
 */
public class Tess4jTest {

    private static final String JPG_HEX = "ff";
    private static final String PNG_HEX = "89";
    private static final String JPG_EXT = "jpg";
    private static final String PNG_EXT = "png";

    // 获取文件真实类型
    public static String getFileExt(String filePath) {
        FileInputStream fis = null;
        String extension = FilenameUtils.getExtension(filePath);
        try {
            fis = new FileInputStream(new File(filePath));
            byte[] bs = new byte[1];
            fis.read(bs);
            String type = Integer.toHexString(bs[0] & 0xFF);
            if (JPG_HEX.equals(type))
                extension = JPG_EXT;
            if (PNG_HEX.equals(type))
                extension = PNG_EXT;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return extension;
    }

    @Test
    public void getCaptureText() {
        String result = "";
        String imgPath = "C:/Users/pc/Desktop/captcha2.jpg";
        BufferedReader bufReader = null;
        try {
            String outPath = imgPath.substring(0, imgPath.lastIndexOf("."));
            Runtime runtime = Runtime.getRuntime();
            String command = "D:\\soft\\tesseract-ocr\\Tesseract-OCR\\tesseract.exe " + imgPath + " " + outPath + " " + " -l eng";
            System.out.println(command);
            Process ps = runtime.exec(command);
            ps.waitFor();
            // 读取文件
            File file = new File(outPath + ".txt");
            if (!file.exists()){
                file.createNewFile();
            }
            bufReader = new BufferedReader(new FileReader(file));
            String temp = "";
            StringBuffer sb = new StringBuffer();
            while ((temp = bufReader.readLine()) != null) {
                sb.append(temp);
            }
            // 文字结果
            result = sb.toString();
            if (null != result && result.trim().length() > 0)
                result = result.replaceAll(" ", "");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return result;
    }

    @Test
    public void clear() throws IOException {
        BufferedImage read = ImageIO.read(new File("C:/Users/pc/Desktop/captcha2.jpg"));
        BufferedImage reline = reline(read);
        File file = new File("C:/Users/pc/Desktop/result");
        if (!file.exists()) {
            file.createNewFile();
        }
        boolean write = ImageIO.write(reline, ".jpg", file);
        System.out.println(write);
    }

    public static BufferedImage reline(BufferedImage curImg) {
        if (curImg != null) {
            int width = curImg.getWidth();
            int height = curImg.getHeight();
            int px = 3;
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = curImg.getRGB(x, y);
                    int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);
                    int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);
                    int b = (int) (((argb >> 0) & 0xFF) * 1.1 + 30);
                    int sum = r + g + b;
                    if (!map.containsKey(sum)) {
                        map.put(sum, 1);
                    } else {
                        int num = map.get(sum);
                        map.remove(sum);
                        map.put(sum, num + 1);
                    }
                }
            }
            List<Integer> list = new ArrayList<Integer>();
            for (Integer in : map.keySet()) {
                // map.keySet()返回的是所有key的值
                Integer n = map.get(in);// 得到每个key多对用value的值
                list.add(n);
            }
            Collections.sort(list);
            // 四种颜色的rgb
            int num1 = 0;
            int num2 = 0;
            int num3 = 0;
            int num4 = 0;
            if (list.size() > 4) {
                num1 = list.get(list.size() - 5);
                num2 = list.get(list.size() - 4);
                num3 = list.get(list.size() - 3);
                num4 = list.get(list.size() - 2);
            }
            List<Integer> keylist = new ArrayList<Integer>();
            for (Integer key : map.keySet()) {
                if (map.get(key) == num1 || map.get(key) == num2 || map.get(key) == num3 || map.get(key) == num4) {
                    keylist.add(key);
                }
            }
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = curImg.getRGB(x, y);
                    int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);
                    int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);
                    int b = (int) (((argb >> 0) & 0xFF) * 1.1 + 30);
                    int sum = r + g + b;
                    int sum1 = 0;
                    int sum2 = 0;
                    int sum3 = 0;
                    int sum4 = 0;
                    int sum5 = 0;
                    int sum6 = 0;
                    boolean flag = true;
                    for (int i = 1; i <= px && y + i < height && y - i > 0 && x - i > 0 && x + i < width; i++) {
                        int upargb = curImg.getRGB(x, y - i);
                        int endargb = curImg.getRGB(x, y + i);
                        int rightupargb = curImg.getRGB(x + i, y + i);
                        int leftupargb = curImg.getRGB(x - i, y + i);
                        int leftdownargb = curImg.getRGB(x - i, y - i);
                        int rightdownargb = curImg.getRGB(x + i, y - i);
                        int r1 = (int) (((upargb >> 16) & 0xFF) * 1.1 + 30);
                        int g1 = (int) (((upargb >> 8) & 0xFF) * 1.1 + 30);
                        int b1 = (int) (((upargb >> 0) & 0xFF) * 1.1 + 30);
                        sum1 = r1 + g1 + b1;
                        int r2 = (int) (((endargb >> 16) & 0xFF) * 1.1 + 30);
                        int g2 = (int) (((endargb >> 8) & 0xFF) * 1.1 + 30);
                        int b2 = (int) (((endargb >> 0) & 0xFF) * 1.1 + 30);
                        sum2 = r2 + g2 + b2;
                        int r3 = (int) (((rightupargb >> 16) & 0xFF) * 1.1 + 30);
                        int g3 = (int) (((rightupargb >> 8) & 0xFF) * 1.1 + 30);
                        int b3 = (int) (((rightupargb >> 0) & 0xFF) * 1.1 + 30);
                        sum3 = r3 + g3 + b3;
                        int r4 = (int) (((leftupargb >> 16) & 0xFF) * 1.1 + 30);
                        int g4 = (int) (((leftupargb >> 8) & 0xFF) * 1.1 + 30);
                        int b4 = (int) (((leftupargb >> 0) & 0xFF) * 1.1 + 30);
                        sum4 = r4 + g4 + b4;
                        int r5 = (int) (((leftdownargb >> 16) & 0xFF) * 1.1 + 30);
                        int g5 = (int) (((leftdownargb >> 8) & 0xFF) * 1.1 + 30);
                        int b5 = (int) (((leftdownargb >> 0) & 0xFF) * 1.1 + 30);
                        sum5 = r5 + g5 + b5;
                        int r6 = (int) (((rightdownargb >> 16) & 0xFF) * 1.1 + 30);
                        int g6 = (int) (((rightdownargb >> 8) & 0xFF) * 1.1 + 30);
                        int b6 = (int) (((rightdownargb >> 0) & 0xFF) * 1.1 + 30);
                        sum6 = r6 + g6 + b6;

                        if (keylist.contains(sum1) || keylist.contains(sum2) || keylist.contains(sum3)
                                || keylist.contains(sum4) || keylist.contains(sum5) || keylist.contains(sum6)) {
                            flag = false;
                        }
                    }
                    if (!(keylist.contains(sum)) && flag) {
                        curImg.setRGB(x, y, Color.white.getRGB());
                    }
                }
            }

        }
        return curImg;
    }

    @Test
    public void test() throws IOException {
        String filepath = "C:\\Users\\pc\\Desktop\\captcha1.jpg";
//        String fileExt = getFileExt(filepath);
//        if (!filepath.endsWith(fileExt)) {
//            filepath = filepath.substring(0, filepath.lastIndexOf(".") + 1) + fileExt;
//        }
        File imageFile = new File(filepath);

        // 图片二值化
//        BufferedImage grayImage = ImageHelper.convertImageToBinary(ImageIO.read(imageFile));
//        ImageIO.write(grayImage, "jpg", new File("C:\\Users\\pc\\Desktop/", "test2.jpg"));
        Tesseract tessreact = new Tesseract();
//        tessreact.setLanguage("num");
        tessreact.setDatapath("D:\\ocr\\tessdata");
        try {
            String result = tessreact.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedImage read = ImageIO.read(new File("C:\\Users\\pc\\Desktop\\captcha1.jpg"));
        BufferedImage bufferedImage = ImageHelper.convertImageToGrayscale(read);
        File file = new File("C:\\Users\\pc\\Desktop\\result.jpg");
        if (!file.exists()) {
            file.createNewFile();
        }
        boolean write = ImageIO.write(bufferedImage, ".jpg",file );
        System.out.println(write);
    }
}
