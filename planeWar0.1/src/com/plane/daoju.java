package com.plane;

import java.awt.*;

public class daoju extends GameObeject{
    //随机移动
    boolean alive;
    double degree;
    Image djImg=GameUtil.getImage("images/daoju.png");
    public daoju(){
        x=Math.random()*800;
        y=Math.random()*800;
        degree=Math.random()*Math.PI*2;
        width=40;
        height=40;
        speed=5;
        alive=true;
    }


    @Override
    public void drawMyself(Graphics g) {
        super.drawMyself(g);
        //制定移动的路径
        g.drawImage(djImg,(int)x,(int)y,width,height,null);     //一开始画出位置


        x=x+speed*Math.cos(degree);
        y=y+speed*Math.sin(degree);

        //上下边界
        if(y>constant.Game_Height-this.height||y<60){
            degree=-degree;
        }
        //左右
        if(x>constant.Game_Width-this.width||x<0){
            degree=Math.PI-degree;
        }
    }
}
