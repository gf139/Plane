package com.plane;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.io.*;
import java.util.Scanner;


public class MyGameFrame extends Frame{
    static Image planeImg=GameUtil.getImage("images/plane.png");
    Image bg=GameUtil.getImage("images/bg.jpg");

    public static Plane p1=new Plane(planeImg,250,250,4);

    int shell_count = 40;//子弹数量
    Shell[] shells=new Shell[shell_count];   //建立子弹对象

    Explode explode;    //建立爆炸对象

    daoju[] goods=new daoju[10];
    int good_count=3;
    int eat=0;      //吃到数量

    Date start = new Date();     //游戏开始时间
    Date end;
    long period=0;     //时长

    //不断刷新
    @Override
    public void paint(Graphics g) {
        g.drawImage(bg,0,0,constant.Game_Width,constant.Game_Height,null);
        drawTime(g);
        //画出子弹奔跑
        for(int i=0;i<shell_count;i++){
            if(shells[i].alive==true){
                shells[i].drawMyself(g);
            }
        }

        //道具出现
        for(int i=0;i<good_count;i++){
            if(goods[i].alive==true){
                goods[i].drawMyself(g);
            }
        }
        for (int i = 0; i < good_count; i++) {
            if (p1.live && goods[i].getRec().intersects(p1.getRec()) && goods[i].alive == true) {
                System.out.println("吃到了！");
                eat++;
                goods[i].alive = false;
                for (int j = 0; j < eat * 10; j++) {
                    shells[j].alive = false;          //子弹死掉
                }
                if (eat == 3) {
                    System.out.println("您成功通关!");
                }
            }
        }
        //子弹击中效果
        for(int i=eat*10;i<shell_count;i++){
            boolean peng = shells[i].getRec().intersects(p1.getRec());
            if (p1.live&&peng&&eat<3) {
                System.out.println("被击中了！");
                p1.live = false;
                explode = new Explode(p1.x, p1.y);
            }
        }
        if(explode!=null){
            explode.drawMyself(g);
        }   //击中画面

    }

//成功判断
    public void finish(Graphics g){
        Font f=g.getFont();
        Color c=g.getColor();
        if(end==null){
            end=new Date();
            period=(end.getTime()-start.getTime())/1000;
        }
        g.setFont(new Font("微软雅黑",Font.BOLD,80));
        if(!p1.live){
            g.setColor(Color.red);
        }else{
            g.setColor(Color.yellow);
            g.drawString("您已通关!",300,300);
        }
        g.drawString("最终时间:"+period+"s",300,500);
        g.drawString("再来一次请按ENTER",100,800);

    }

    //时间函数,失败
    public void drawTime(Graphics g){
        Font f=g.getFont();
        Color c=g.getColor();
        g.setColor(Color.green);
        if(p1.live) {   //死亡判断
            if(eat==3){
                finish(g);
            }else {
                p1.drawMyself(g);
                period = (System.currentTimeMillis() - start.getTime()) / 1000;
                g.drawString("坚持:" + period + "s", 485, 950);
            }
        }else{
            finish(g);
            g.setColor(c);
            g.setFont(f);
        }

    }

    //初始化窗口
    public void launchFrame(){
        this.setTitle("飞机大战--java");
        setSize(1000,1000);
        setLocation(200,200);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); //正常退出程序
            }
        });
        setVisible(true);

        new Paint().start();    //启动重置窗口的线程
        this.addKeyListener(new KeyMonitor());  //启动键盘监听
        //初始化50炮弹
        for(int i=0;i<shells.length;i++){
            shells[i]=new Shell();
        }
        for(int i=0;i<good_count;i++){
            goods[i] = new daoju();
        }
        //初始化货物道具,建立对象

    }
    //双缓冲技术防止闪烁
    private Image offScreenImage = null;
    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(constant.Game_Width,constant.Game_Height);//这是游戏窗口的宽度和高度
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);

    }
/**
 * 定义了一个重置窗口的线程类
 *定义成内部类为了方便直接使用窗口类的方法
**/
    class Paint extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();  //内部类可以直接使用外部类的成员
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

// 内部类实现键盘监听处理
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // 重新开始游戏
                resetGame();
            } else {
                // 处理其他键盘事件
                p1.Direction(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p1.misDirection(e);
        }
    }

    // 重置游戏状态
    private void resetGame() {
        // 重新设置游戏开始时间
        start = new Date();
        // 重置飞机位置和生存状态
        p1.x = 250;
        p1.y = 250;
        p1.live = true;
        // 重置爆炸效果
        explode = null;
        // 重置道具状态

        for (int i = 0; i < good_count; i++) {
            goods[i].alive = true;
            goods[i] =new daoju();
        }
        // 重置吃到数量
        eat = 0;
        // 重新初始化子弹
        for (int i = 0; i < shell_count; i++) {
            shells[i] = new Shell();
        }
        // 重绘窗口
        repaint();
    }



    public static void main(String[] args){
        do{
            MyGameFrame gameFrame = new MyGameFrame();
            gameFrame.launchFrame();
        }while(p1.again);
    }
}
