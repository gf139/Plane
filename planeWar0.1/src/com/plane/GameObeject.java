package com.plane;

import java.awt.*;

/**
 * 游戏物体的根类
 * **/
public class GameObeject {
    Image img;  //图片
    double x,y; //位置
    int speed;  //速度
    int width,height;//物体宽高

    public GameObeject(){}
    public GameObeject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }
    public GameObeject(Image img, double x, double y, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public void drawMyself(Graphics g){
        g.drawImage(img,(int)x,(int)y,width,height,null);
    }

    //矩形检测
    public Rectangle getRec(){
        return new Rectangle((int)x,(int)y,width,height);
    }
}
