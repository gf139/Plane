package com.plane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

//游戏工具类
public class GameUtil {
    //构造器私有，放置别人创建类的对象
    private GameUtil(){};
    public static Image getImage(String path) {     //images/plane.png
        BufferedImage img = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    public static void main(String[] args){
        Image img=GameUtil.getImage("images/plane.png");
        System.out.println(img);
    }
}
