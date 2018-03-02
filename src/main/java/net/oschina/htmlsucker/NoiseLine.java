package net.oschina.htmlsucker;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NoiseLine {

    private int M;
    private int N;
    int a[][];
    int[] b;
    int threshold = 20;
    int offset = 11;

    public NoiseLine() {
        int x = 0;
        int y = 0;
        try {
            final BufferedImage img = ImageIO.read(new File("C:\\Users\\pc\\Desktop\\captcha1.jpg"));
            this.renderImg = img;
            final int width = img.getWidth();
            final int height = img.getHeight();
            M = height;
            N = width;
            a = new int[this.M][this.N];
            b = new int[this.M];
            System.out.println(width + ":" + height);
            for (; x < height; ++x) {
                for (y = 0; y < width; ++y) {
                    this.a[x][y] = isBlack(img.getRGB(y, x), 700);
                    //System.out.print(this.a[x][y] + " ");
                }
                //System.out.println(" ");
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage renderImg;

    public static int isBlack(int colorInt, int whiteThreshold) {
        final Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= whiteThreshold) {
            return 1;
        }
        return 0;
    }

    public void genLine(int n) {
        if (n < this.offset) {
            this.b[n] = -1;
            this.genLine(n + 1);
        }
        if (n == this.M) {
            for (int i = 0; i < this.M; i++) {
                System.out.print(this.b[i] + " ");

            }
            System.out.println("");
        }
        if (n == this.offset) {
            for (int j = 0; j < this.N; j++) {
                if (this.a[this.offset][j] == 1) {
                    this.b[this.offset] = j;
                    this.genLine(n + 1);
                }
            }
        }
        if (n > 0 && n < this.M) {
            int hasMore = 0;
            if (this.b[n - 1] > 0 && this.b[n - 1] < this.N && this.a[n][this.b[n - 1]] == 1) {
                this.b[n] = this.b[n - 1];
                hasMore = 1;
                this.genLine(n + 1);
            } else {
                if (this.b[n - 1] > 0 && this.a[n][this.b[n - 1] - 1] == 1) {
                    this.b[n] = this.b[n - 1] - 1;
                    hasMore = 1;
                    this.genLine(n + 1);
                }
                if (this.b[n - 1] < this.N - 1 && this.a[n][this.b[n - 1] + 1] == 1) {
                    this.b[n] = this.b[n - 1] + 1;
                    hasMore = 1;
                    this.genLine(n + 1);
                }
            }
            if (n - this.offset > this.threshold && hasMore == 0) {
                for (int i = 0; i < n; i++) {
                    if (this.b[i] > 0) {
                        this.renderImg.setRGB(this.b[i], i, Color.RED.getRGB());
                    }
                }
            }
        }

    }

    public void saveImg() {
        try {
            ImageIO.write(this.renderImg, "JPG", new File("C:\\Users\\pc\\Desktop\\noiseRender.jpg"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final NoiseLine line = new NoiseLine();
        line.genLine(0);
        line.saveImg();
        System.out.println("处理后图片在img/noiseRender.jpg");
    }

}
