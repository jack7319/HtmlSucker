package net.oschina.htmlsucker;

import net.sourceforge.tess4j.util.ImageHelper;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/1 14:20
 * @version: 1.0
 * @Description:
 */
public class ImageDemo {
    static String basepath = "C:/Users/pc/Desktop";

    // 二值化
    public void binaryImage() throws IOException {
        File file = new File(basepath + "/captcha1.jpg");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File(basepath + "/result1.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }

    // 灰度
    public void grayImage() throws IOException {
        File file = new File(basepath + "/result1.jpg");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File(basepath + "/result2.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }

    // 获取二值化图片的一段代码。备份
    public void getBinary() throws IOException {
        // 图片二值化
        BufferedImage image = ImageIO.read(new File(basepath + "/captcha1.jpg"));
        int w = image.getWidth();
        int h = image.getHeight();
        float[] rgb = new float[3];
        double[][] zuobiao = new double[w][h];
//        int R = 0;
//        float red = 0;
//        float green = 0;
//        float blue = 0;
        BufferedImage bi = new BufferedImage(w, h,
                BufferedImage.TYPE_BYTE_BINARY);
        ;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);
                System.out.println(pixel);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
//                red += rgb[0];
//                green += rgb[1];
//                blue += rgb[2];
//                R = (x + 1) * (y + 1);
                float avg = (rgb[0] + rgb[1] + rgb[2]) / 3;
                zuobiao[x][y] = avg;

            }
        }

        double SW = 170;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                System.out.println("zuobiao[x][y] = " + zuobiao[x][y]);
                if (zuobiao[x][y] <= SW) {
                    int max = new Color(0, 0, 0).getRGB();
                    System.out.println("max = " + max);
                    bi.setRGB(x, y, max); // max = -16777216
                } else {
                    int min = new Color(255, 255, 255).getRGB();
                    System.out.println("min = " + min);
                    bi.setRGB(x, y, min); // min = -1
                }
            }
        }

        ImageIO.write(bi, "jpg", new File(basepath + "/captcha3.jpg"));
    }

    public static void main(String[] args) {
        Color color = new Color(255, 255, 255);
        System.out.println(color.getRGB());
    }

    @Test
    public void tttt() throws IOException {

        // 图片二值化
        BufferedImage image = ImageIO.read(new File(basepath + "/captcha1.jpg"));
        int w = image.getWidth();
        int h = image.getHeight();
        float[] rgb = new float[3];
        double[][] zuobiao = new double[w][h];
//        int R = 0;
//        float red = 0;
//        float green = 0;
//        float blue = 0;
        BufferedImage bi = new BufferedImage(w, h,
                BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);
                System.out.println(pixel);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
//                red += rgb[0];
//                green += rgb[1];
//                blue += rgb[2];
//                R = (x + 1) * (y + 1);
                float avg = (rgb[0] + rgb[1] + rgb[2]) / 3; // 这里获取了图片的原色
                // 判断周围
                // 思路：
                // 第一步：找到一个点，要求是满足以下任意一个条件
                if (condition(image, x, y, w, h)) {
                    zuobiao[x][y] = 180;
                } else {
                    zuobiao[x][y] = avg;
                }

                // 条件1：上方1px并且下方1px是白块
                // 条件2：左方1px并且右方1px是白块
                //  第二步：找到之后，拿到这个点的颜色
                //  找到这条线，将这条线的颜色变成白色
//                zuobiao[x][y] = avg;

            }
        }

        double SW = 170;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                System.out.println("zuobiao[x][y] = " + zuobiao[x][y]);
                if (zuobiao[x][y] <= SW) {
                    int max = new Color(0, 0, 0).getRGB();
                    System.out.println("max = " + max);
                    bi.setRGB(x, y, max); // max = -16777216 黑色
                } else {
                    int min = new Color(255, 255, 255).getRGB();
                    System.out.println("min = " + min);
                    bi.setRGB(x, y, min); // min = -1 白色
                }
            }
        }

        ImageIO.write(bi, "jpg", new File(basepath + "/captcha3.jpg"));
    }

    public boolean condition(BufferedImage image, int x, int y, int w, int h) {
        int threshold = 2;
        if ((x - threshold < 0 ? true : isBlack(image.getRGB(x - threshold, y))) && (x + threshold >= w ? true : isBlack(image.getRGB(x + threshold, y)))) {
            return true;
        }
        if ((y - threshold < 0 ? true : isBlack(image.getRGB(x, y - threshold))) && (y + threshold >= h ? true : isBlack(image.getRGB(x, y + threshold)))) {
            return true;
        }
        return false;
    }

    public static boolean isBlack(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {
            return true;
        }
        return false;
    }

    public static boolean isWhite(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 300) {
            return true;
        }
        return false;
    }

    public static int isBlackOrWhite(int colorInt) {
        if (getColorBright(colorInt) < 30 || getColorBright(colorInt) > 730) {
            return 1;
        }
        return 0;
    }

    public static int getColorBright(int colorInt) {
        Color color = new Color(colorInt);
        return color.getRed() + color.getGreen() + color.getBlue();
    }
}