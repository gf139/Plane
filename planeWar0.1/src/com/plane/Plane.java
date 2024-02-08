package com.plane;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObeject{
    boolean left,up,down,right,sheji; //方向
    static boolean again=false;

    boolean live = true;    //活着
    public Plane(){}
    @Override
    public void drawMyself(Graphics g) {
        super.drawMyself(g);
        //飞机飞行算法
        if(live) {
            if (left) {
                if(x>20){
                    x -= speed;
                }
            }
            if (right) {
                if(x<960) {
                    x += speed;
                }
            }
            if (up) {
                if(y>40) {
                    y -= speed;
                }
            }
            if (down) {
                if(y<960) {
                    y += speed;
                }
            }
            if(sheji){
                sheji a=new sheji(this.x,this.y);
                a.drawMyself(g);
            }
        }

    }
    public void Direction(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left=true;
                break;
            case KeyEvent.VK_W:
                up=true;
                break;
            case KeyEvent.VK_S:
                down=true;
                break;
            case KeyEvent.VK_D:
                right=true;
                break;
            case KeyEvent.VK_F:
                sheji=true;
                break;
            case KeyEvent.VK_ENTER:
                again=true;
                break;
        }
    }

    public void misDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_F:
                sheji = false;
                break;
        }
    }

    public Plane(Image img, double x, double y, int speed) {
        super(img, x, y, speed);
    }

    public Plane( double x, double y) {
        this.x = x;
        this.y = y;
    }
}
