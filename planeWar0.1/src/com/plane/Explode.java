package com.plane;

import java.awt.*;

public class Explode extends Thread{
    //位置
    double x,y;

    static Image[] imgs=new Image[16];

    static int count=0;

    static {
        for(int i=0;i<imgs.length;i++){
            imgs[i]=GameUtil.getImage("images/explode/e"+(i+1)+".gif");
            imgs[i].getWidth(null);     //解决懒加载
        }
    }

    public void drawMyself(Graphics g){
        if(count<16){
            g.drawImage(imgs[count],(int)x,(int)y,null);
            count++;
        }

    }
    public Explode(){}
    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
