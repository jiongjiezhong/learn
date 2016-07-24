package com.wanma.web.support.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



/**
 *
 */
public final class ImageUtil {

    private static final char[] chars = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};
    private static final int SIZE = 4;
    private static final int LINES = 15;
    private static final int WIDTH = 75;
    private static final int HEIGHT = 30;
    private static final int FONT_SIZE = 20;

    public static Map<String, BufferedImage> createImage() {
        return createImage(WIDTH, HEIGHT);
    }

    public static Map<String, BufferedImage> createImage(int width, int height) {
        StringBuilder sb = new StringBuilder();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        Random random = new Random();
        //  画随机字符
        for (int i = 1; i <= SIZE; i++) {
            int r = random.nextInt(chars.length);
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            g.drawString(chars[r] + "", (i - 1) * width / SIZE, height/2+5);
            sb.append(chars[r]);// 保存符串
        }
        //绘制边框
//        g.setColor(new Color(239, 72, 26));
//        g.drawLine(0, 0, 0, width);
//        g.drawLine(0, 0, width, 0);
        // 干扰线
        for (int i = 1; i <= LINES; i++) {
            g.setColor(getRandomColor());
            g.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }
        Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
        map.put(sb.toString(), bi);
        return map;
    }

    public static Color getRandomColor() {
        Random random = new Random();
        Color color = new Color(random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
        return color;
    }
}
