package com.plane;

import java.awt.*;

/**
 * 炮弹类
 */
public class Shell extends GameObeject{
    double degree;
    boolean alive;

    public Shell(){
        x=500;
        y=500;
        degree=Math.random()*Math.PI*2;
        width=10;
        height=10;
        alive=true;

        speed=5;
    }

    @Override
    public void drawMyself(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.yellow);
        g.fillOval((int)x,(int)y,width,height);
        g.setColor(c);
        //制定移动的路径

        x=x+speed*Math.cos(degree);
        y=y+speed*Math.sin(degree);

        //上下边界
        if(y>constant.Game_Height-this.height||y<40){
            degree=-degree;
        }
        //左右
        if(x>constant.Game_Width-this.width||x<0){
            degree=Math.PI-degree;
        }
    }
}
